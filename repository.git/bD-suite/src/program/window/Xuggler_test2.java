
package program.window;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xuggle.xuggler.Global;
import com.xuggle.xuggler.IAudioSamples;
import com.xuggle.xuggler.ICodec;
import com.xuggle.xuggler.IContainer;
import com.xuggle.xuggler.IPacket;
import com.xuggle.xuggler.IPixelFormat;
import com.xuggle.xuggler.IStream;
import com.xuggle.xuggler.IStreamCoder;
import com.xuggle.xuggler.IVideoPicture;
import com.xuggle.xuggler.IVideoResampler;
import com.xuggle.xuggler.Utils;
import com.xuggle.xuggler.demos.VideoImage;

/**
 * Takes a media kontainer,decodes it,finds the first stream
 * decodes the stream,and then plays audio and video.
 */
public class Xuggler_test2 extends JFrame
{

  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
/**
   * mLine is for the sound
   */
  private static SourceDataLine mLine;
  private static final Logger logger=LoggerFactory.getLogger(Obj.class);


  /**
   * mScreen is the window for the video
   * 
   */
  private static VideoImage mScreen = null;

  private static long mSystemVideoClockStartTime;

  private static long mFirstVideoTimestampInStream;
  
  /**
   * Opens the file and plays the sound as fast as it can.
   *  
   * 
   */
  @SuppressWarnings("deprecation")
  public static void runv(String[] args)
  {
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		
		final JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
	  JFileChooser chooser = new JFileChooser();
	  chooser.showOpenDialog(frame);
	  String string1=chooser.getSelectedFile().getPath();//mora biti getPath,to je bilo rješenje problema 
    if (string1.length() <= 0)
      throw new IllegalArgumentException("must pass in a filename as the first argument");
    
    String filename = string1;
    logger.info("Video opening attempt.");
    // Cjecks if the file is supported.
    if (!IVideoResampler.isSupported(IVideoResampler.Feature.FEATURE_COLORSPACECONVERSION))
      throw new RuntimeException("you must install the GPL version of Xuggler (with IVideoResampler support) for this demo to work");
    
    // Creates and opens the container object
    IContainer container = IContainer.make();
   
    if (container.open(filename, IContainer.Type.READ, null) < 0)
      throw new IllegalArgumentException("could not open file: " + filename);
    
    // Checks on how many streams were found
    int numStreams = container.getNumStreams();
    
    // goes trough all the streams
    int videoStreamId = -1;
    IStreamCoder videoCoder = null;
    int audioStreamId = -1;
    IStreamCoder audioCoder = null;
    for(int i = 0; i < numStreams; i++)
    {
      // Finds the streamed object
      IStream stream = container.getStream(i);
      // Finds the first decoder
      IStreamCoder coder = stream.getStreamCoder();
      
      if (videoStreamId == -1 && coder.getCodecType() == ICodec.Type.CODEC_TYPE_VIDEO)
      {
        videoStreamId = i;
        videoCoder = coder;
      }
      else if (audioStreamId == -1 && coder.getCodecType() == ICodec.Type.CODEC_TYPE_AUDIO)
      {
        audioStreamId = i;
        audioCoder = coder;
      }
    }
    if (videoStreamId == -1 && audioStreamId == -1)
      throw new RuntimeException("could not find audio or video stream in container: "+filename);
    
    /*
     * Checks if there is a video stream in the file.IF there is one it starts the decoder.
     */
    IVideoResampler resampler = null;
    if (videoCoder != null)
    {
      if(videoCoder.open() < 0)
        throw new RuntimeException("could not open audio decoder for container: "+filename);
    
      if (videoCoder.getPixelType() != IPixelFormat.Type.BGR24)
      {
        //checks and converts the format
        resampler = IVideoResampler.make(videoCoder.getWidth(), videoCoder.getHeight(), IPixelFormat.Type.BGR24,
            videoCoder.getWidth(), videoCoder.getHeight(), videoCoder.getPixelType());
        if (resampler == null)
          throw new RuntimeException("could not create color space resampler for: " + filename);
      }
      /*
       * draws the window
       */
      openJavaVideo();
    }
    if (audioCoder != null)
    {
      if (audioCoder.open() < 0)
        throw new RuntimeException("could not open audio decoder for container: "+filename);
      
      /*
       * Readies the Java sound system
       */
      try
      {
        openJavaSound(audioCoder);
      }
      catch (LineUnavailableException ex)
      {
        throw new RuntimeException("unable to open sound device on your system when playing back container: "+filename);
      }
    }
    
    
    /*
     * Goes trough the container and checks it
     */
    
    IPacket packet = IPacket.make();
    mFirstVideoTimestampInStream = Global.NO_PTS;
    mSystemVideoClockStartTime = 0;
    while(container.readNextPacket(packet) >= 0)
    {
      /*
       * Checks if it belongs into our stream
       */
      if (packet.getStreamIndex() == videoStreamId)
      {
        /*
         * Alocates an image so it can take data from Xuggler
         */
        IVideoPicture picture = IVideoPicture.make(videoCoder.getPixelType(),
            videoCoder.getWidth(), videoCoder.getHeight());
        
        /*
         * Decodes and checks the video for errors
         * 
         */
        int bytesDecoded = videoCoder.decodeVideo(picture, packet, 0);
        if (bytesDecoded < 0)
          throw new RuntimeException("got error decoding audio in: " + filename);

        /*
         * Checks the image for integrity
         */
        if (picture.isComplete())
        {
          IVideoPicture newPic = picture;
          /*
           * Converting
           */
          if (resampler != null)
          {
            // Resampling
            newPic = IVideoPicture.make(resampler.getOutputPixelFormat(), picture.getWidth(), picture.getHeight());
            if (resampler.resample(newPic, picture) < 0)
              throw new RuntimeException("could not resample video from: " + filename);
          }
          if (newPic.getPixelType() != IPixelFormat.Type.BGR24)
            throw new RuntimeException("could not decode video as BGR 24 bit data in: " + filename);

          long delay = millisecondsUntilTimeToDisplay(newPic);
          // stops if there is no video stream
          try
          {
            if (delay > 0)
              Thread.sleep(delay);
          }
          catch (InterruptedException e)
          {
            return;
          }

          // Shows the video

          mScreen.setImage(Utils.videoPictureToImage(newPic));
          
        }
      }
      else if (packet.getStreamIndex() == audioStreamId)
      {
        /*
         Alocates tthe samples
         */
        IAudioSamples samples = IAudioSamples.make(1024, audioCoder.getChannels());
        
        /*
         Catches the samples
         */
        int offset = 0;
        
        /*
         * Works until all the data is processed
         */
        while(offset < packet.getSize())
        {
          int bytesDecoded = audioCoder.decodeAudio(samples, packet, offset);
          if (bytesDecoded < 0)
            throw new RuntimeException("got error decoding audio in: " + filename);
          offset += bytesDecoded;
          /*
			Checks the sample for integrity
           */
          if (samples.isComplete())
          {
            playJavaSound(samples);
          }
        }
      }
      else
      {
        /*
         * Not part of the video stream,so let's just ignore it for now.
         */
        do {} while(false);
      }
      /*
  	mScreen.addWindowListener(new WindowAdapter() {

	    @Override
	    public void windowClosing(WindowEvent e) {
	    	mScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    	((Window) mLine).dispose();

	    }
	});
	A failed attempt at solving the closing bug.
*/
	
    }
    /*
     * Actually this is usually done by the garbage collector.
     */
    if (videoCoder != null)
    {
      videoCoder.close();
      videoCoder = null;
    }
    if (audioCoder != null)
    {
      audioCoder.close();
      audioCoder = null;
    }
    if (container !=null)
    {
      container.close();
      container = null;
    }
    
    closeJavaVideo();
    closeJavaSound();
    
    
  }

  private static long millisecondsUntilTimeToDisplay(IVideoPicture picture)
  {
    /**
     Tries to synchronise the frame with the system time.
     */
    long millisecondsToSleep = 0;
    if (mFirstVideoTimestampInStream == Global.NO_PTS)
    {
      // First run
      mFirstVideoTimestampInStream = picture.getTimeStamp();
      // takes the start time fro better synchronisation
      mSystemVideoClockStartTime = System.currentTimeMillis();
      millisecondsToSleep = 0;
    } else {
      long systemClockCurrentTime = System.currentTimeMillis();
      long millisecondsClockTimeSinceStartofVideo = systemClockCurrentTime - mSystemVideoClockStartTime;
      // calculates the time spent since the start of the video
      long millisecondsStreamTimeSinceStartOfVideo = (picture.getTimeStamp() - mFirstVideoTimestampInStream)/1000;
      final long millisecondsTolerance = 50; // tolerance
      millisecondsToSleep = (millisecondsStreamTimeSinceStartOfVideo -
          (millisecondsClockTimeSinceStartofVideo+millisecondsTolerance));
    }
    return millisecondsToSleep;
  }

  /**
   * Opens the swing window
   */
  private static void openJavaVideo()
  {
    mScreen = new VideoImage();
  }

  /**
   * Closes the swing window.But with problems. :(
   */
  
  private static void closeJavaVideo()
  {
	  mScreen.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	  closeJavaSound();
  }
	
  /*
  private static void closeJavaVideo()
  {
    Xuggler_test2.dispose();
  }
  Another failed attempt at fixing the closing bug.
*/

  private static void openJavaSound(IStreamCoder aAudioCoder) throws LineUnavailableException
  {
    AudioFormat audioFormat = new AudioFormat(aAudioCoder.getSampleRate(),
        (int)IAudioSamples.findSampleBitDepth(aAudioCoder.getSampleFormat()),
        aAudioCoder.getChannels(),
        true,
        false);
    DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
    mLine = (SourceDataLine) AudioSystem.getLine(info);
    /**
     * if it succeeds it opens the mLine
     */
    mLine.open(audioFormat);
    /**
     * starts the mLine
     */
    mLine.start();
    
    
  }

  private static void playJavaSound(IAudioSamples aSamples)
  {
    /**
     * Puts samples into mLine
     */
    byte[] rawBytes = aSamples.getData().getByteArray(0, aSamples.getSize());
    mLine.write(rawBytes, 0, aSamples.getSize());
  }

  private static void closeJavaSound()
  {
    if (mLine != null)
    {
     
       /* waits for audio playback to stop.
       */
      mLine.drain();
      /*
       * Closes mLine
       */
      mLine.close();
      mLine=null;
    }
  }
}