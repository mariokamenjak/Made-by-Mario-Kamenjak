package program.window;

import java.awt.EventQueue;

import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import java.awt.Color;

public class Credits {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void runCred(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Credits window = new Credits();
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
	public Credits() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[]", "[][][][][][][][][][][]"));
		
		JLabel lblNewLabel = new JLabel("Full name:Blue dragon software suite");
		lblNewLabel.setForeground(Color.WHITE);
		frame.getContentPane().add(lblNewLabel, "cell 0 0");
		
		JLabel lblVersion = new JLabel("Version:0.7777");
		lblVersion.setForeground(Color.WHITE);
		frame.getContentPane().add(lblVersion, "cell 0 1");
		
		JLabel lblWebsitetbd = new JLabel("Website:http://bdsoftware.co.nf/");
		lblWebsitetbd.setForeground(Color.WHITE);
		frame.getContentPane().add(lblWebsitetbd, "cell 0 2");
		
		JLabel lblLicenseTypegnuGpl = new JLabel("License type:GNU GPL v2");
		lblLicenseTypegnuGpl.setForeground(Color.WHITE);
		frame.getContentPane().add(lblLicenseTypegnuGpl, "cell 0 3");
		
		JLabel lblAuthormarioKamenjakAka = new JLabel("Author:Mario Kamenjak a.k.a. Blue dragon");
		lblAuthormarioKamenjakAka.setForeground(Color.WHITE);
		frame.getContentPane().add(lblAuthormarioKamenjakAka, "cell 0 4");
		
		JLabel lblLinuxTestermarioKamenjak = new JLabel("Linux tester:Mario Kamenjak");
		lblLinuxTestermarioKamenjak.setForeground(Color.WHITE);
		frame.getContentPane().add(lblLinuxTestermarioKamenjak, "cell 0 5");
		
		JLabel lblWindowsTesterivanKneevi = new JLabel("Windows tester:Ivan Knežević");
		lblWindowsTesterivanKneevi.setForeground(Color.WHITE);
		frame.getContentPane().add(lblWindowsTesterivanKneevi, "cell 0 6");
		
		JLabel lblSpecialThanksTo = new JLabel("Special thanks to:");
		lblSpecialThanksTo.setForeground(Color.WHITE);
		frame.getContentPane().add(lblSpecialThanksTo, "cell 0 7");
		
		JLabel lblAleksandarRadovan = new JLabel("Aleksandar Radovan");
		lblAleksandarRadovan.setForeground(Color.WHITE);
		frame.getContentPane().add(lblAleksandarRadovan, "cell 0 8");
		
		JLabel lblMirkoSmilevski = new JLabel("Mirko Smilevski");
		lblMirkoSmilevski.setForeground(Color.WHITE);
		frame.getContentPane().add(lblMirkoSmilevski, "cell 0 9");
		
		JLabel lblThePolytechnicOf = new JLabel("The polytechnic of Zagreb teaching staff!");
		lblThePolytechnicOf.setForeground(Color.WHITE);
		frame.getContentPane().add(lblThePolytechnicOf, "cell 0 10");
	}

}
