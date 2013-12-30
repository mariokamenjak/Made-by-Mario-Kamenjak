package program.window;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BdNotes {

	private JFrame frame;
	private static final Logger logger=LoggerFactory.getLogger(Obj.class);
	/**
	 * Launch the application.
	 */
	public static void BdNotesrun(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BdNotes window = new BdNotes();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}//starts notes
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BdNotes() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		logger.info("Notes opened!");
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setTitle("bD notes");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		//GUI elemnts
		JTextArea textArea = new JTextArea();
		tabbedPane.addTab("Note 1", null, textArea, null);
		
		JTextArea textArea_1 = new JTextArea();
		tabbedPane.addTab("Note 2", null, textArea_1, null);
		
		JTextArea textArea_2 = new JTextArea();
		tabbedPane.addTab("Note 3", null, textArea_2, null);
		
		JTextArea textArea_3 = new JTextArea();
		tabbedPane.addTab("Note 4", null, textArea_3, null);
		
		JTextArea textArea_4 = new JTextArea();
		tabbedPane.addTab("Note 5", null, textArea_4, null);
		//Panels & tabs...
	}

}
