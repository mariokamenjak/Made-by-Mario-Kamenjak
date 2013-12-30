package program.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Image {
	private static int xPosition = 30, yPosition = 30;
	private JFrame frame;
	private static final Logger logger=LoggerFactory.getLogger(Obj.class);
	final JDesktopPane desktopPane = new JDesktopPane();
	//calling some classes
	/**
	 * Launch the application.
	 */
	public static void Imagerun(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			//Opens the image viewer
			public void run() {
				try {
					Image window = new Image();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}//Starts the window
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Image() {
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
		//Frame settings
		JButton btnOdaberiteSliku = new JButton("Choose image file");
		btnOdaberiteSliku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser imageFileChooser = new JFileChooser();
				imageFileChooser.showOpenDialog(frame);
				File selectedFile = imageFileChooser.getSelectedFile();
				if (selectedFile != null) {
					JInternalFrame imageFrame = new JInternalFrame(
							selectedFile.getName(), true, true, true, true);
					logger.info("Image file opened: "+ selectedFile.getName());
					imageFrame.setTitle("Showing " + selectedFile.getName());
					imageFrame.getContentPane().add(new JLabel(new
							ImageIcon(selectedFile.getPath())));
					imageFrame.setSize(200, 200);
					imageFrame.setLocation(xPosition, yPosition);
					xPosition += 50;
					yPosition += 50;
					desktopPane.add(imageFrame);
					imageFrame.setVisible(true);
					//Shows the image
				}


			}
		});
		frame.getContentPane().add(btnOdaberiteSliku, BorderLayout.NORTH);
	}

}
