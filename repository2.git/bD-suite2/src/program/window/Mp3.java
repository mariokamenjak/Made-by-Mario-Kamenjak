package program.window;

import jaco.mp3.player.MP3Player;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Mp3 {
	private static int xPosition = 30, yPosition = 30;
	private JFrame frame;
	private static final Logger logger=LoggerFactory.getLogger(Obj.class);
	final JDesktopPane desktopPane = new JDesktopPane();
	//varijable potrebne za rad mp3 playera
	/**
	 * Pokreće aplikaciju
	 */
	public static void Mp3run(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mp3 window = new Mp3();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Mp3() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		final JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(desktopPane, BorderLayout.CENTER);
		//GUI configuration
		JButton btnOdaberitePjesmu = new JButton("Choose mp3 file");
		btnOdaberitePjesmu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser mp3FileChooser = new JFileChooser();
				mp3FileChooser.showOpenDialog(frame);
				File selectedFile = mp3FileChooser.getSelectedFile();
				//button and the file chooser
				if (selectedFile != null) {
					JInternalFrame mp3Frame =
							new JInternalFrame(selectedFile.getName(), true, true, true, true);
					logger.info("Mp3 file opened: "+ selectedFile.getName());
					mp3Frame.setTitle("MP3 player");
					mp3Frame.getContentPane().add(new JLabel("Playing " + selectedFile.getName()));
					mp3Frame.setSize(200, 50);
					mp3Frame.setLocation(xPosition, yPosition);
					xPosition += 100;
					yPosition += 25;
					desktopPane.add(mp3Frame);
					mp3Frame.setVisible(true);
					//mp3 frame configuration
					final MP3Player player = new MP3Player(selectedFile);
					player.play();
					mp3Frame.addInternalFrameListener(new InternalFrameAdapter() {
						public void internalFrameClosing(InternalFrameEvent e) {
							player.stop();//starts the playback
						}
					});//mp3 player object
				}


			}
		});
		frame.getContentPane().add(btnOdaberitePjesmu, BorderLayout.NORTH);
	}

}
