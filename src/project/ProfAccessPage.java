package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Window;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.event.ActionListener;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;

@SuppressWarnings({ "serial", "unused" })
public class ProfAccessPage extends JFrame {
	public static String profname;
	private JPanel contentPane;
	protected Window frame;

	/**
	 * Launch the application.
	 */
	public static void main(String agrs[]) {
		ProfAccessPage frame;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					for(LookAndFeelInfo info: UIManager.getInstalledLookAndFeels())
					{
						if("Nimbus".equals(info.getName()))
						{
							UIManager.setLookAndFeel(info.getClassName());
							break;
						}
					}
					
					ProfAccessPage frame = new ProfAccessPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @param name 
	 */
	public ProfAccessPage() {
		setTitle("AMS");setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddProfessor.class.getResource("/Icons/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 200, 730, 530);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setForeground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnLogOut = new JButton("LOG OUT");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((JOptionPane.showConfirmDialog(null, "Do you want to Log Out?", "logging out", JOptionPane.YES_NO_OPTION)) == JOptionPane.YES_OPTION)
				{
					LoginPage.main(null);
					dispose();
				}
			}
		});
		btnLogOut.setForeground(new Color(255, 255, 255));
		btnLogOut.setBackground(new Color(0, 0, 0));
		btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 9));
		btnLogOut.setBounds(635, 0, 79, 36);
		contentPane.add(btnLogOut);
		
		JLabel label = new JLabel("PROFESSOR ACCESS PAGE");
		label.setBackground(SystemColor.activeCaptionText);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(SystemColor.activeCaptionText);
		label.setFont(new Font("Trebuchet MS", Font.BOLD, 50));
		label.setBounds(-6, 28, 714, 62);
		contentPane.add(label);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.DARK_GRAY);
		separator.setBackground(SystemColor.menuText);
		separator.setBounds(10, 92, 694, 13);
		contentPane.add(separator);
		
		JButton button = new JButton("TAKE NEW \r\nATTENDANCE");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TakeAttendance.main(null);
				dispose();
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("SansSerif", Font.BOLD, 19));
		button.setBackground(new Color(123, 104, 238));
		button.setBounds(52, 242, 278, 106);
		contentPane.add(button);
		
		JButton btnMakeStudentAnalysis = new JButton("MAKE STUDENT ANALYSIS");
		btnMakeStudentAnalysis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StudentAnalysis.main(null);
				dispose();
			}
		});
		btnMakeStudentAnalysis.setForeground(Color.WHITE);
		btnMakeStudentAnalysis.setFont(new Font("SansSerif", Font.BOLD, 18));
		btnMakeStudentAnalysis.setBackground(new Color(65, 105, 225));
		btnMakeStudentAnalysis.setBounds(382, 242, 278, 106);
		contentPane.add(btnMakeStudentAnalysis);
		
		JLabel lblWelcomeProfessor = new JLabel();
		lblWelcomeProfessor.setHorizontalAlignment(SwingConstants.LEFT);
		lblWelcomeProfessor.setFont(new Font("Monotype Corsiva", Font.PLAIN, 25));
		lblWelcomeProfessor.setBounds(10, 142, 426, 36);
		lblWelcomeProfessor.setText("Welcome, " + profname);
		contentPane.add(lblWelcomeProfessor);
		
		
	}
}
