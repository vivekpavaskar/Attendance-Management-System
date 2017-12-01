package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
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
import java.awt.event.ActionEvent;

@SuppressWarnings({ "serial", "unused" })
public class LoginPage extends JFrame {

	private JPanel contentPane;
	protected Window frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		LoginPage frame;
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
					
					LoginPage frame = new LoginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginPage() {
		setTitle("AMS");setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddProfessor.class.getResource("/Icons/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 200, 730, 530);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLoginPage = new JLabel("Attendance Management System");
		lblLoginPage.setFont(new Font("Monotype Corsiva", Font.PLAIN, 50));
		lblLoginPage.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginPage.setBounds(45, 11, 623, 53);
		contentPane.add(lblLoginPage);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setBounds(10, 78, 694, 2);
		contentPane.add(separator);
		
		JButton btnNewButton = new JButton(" LOGIN AS\r\n PROFESSOR");
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {			
				ProfLogin p = new ProfLogin();
				p.main(null);
				dispose();
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBackground(new Color(123, 104, 238));
		btnNewButton.setBounds(98, 218, 209, 122);
		contentPane.add(btnNewButton);
		
		JButton btnLoginAsAdmin = new JButton("LOGIN AS ADMIN");
		btnLoginAsAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminLogin.main(null);
				dispose();
			}
		});
		btnLoginAsAdmin.setForeground(new Color(255, 255, 255));
		btnLoginAsAdmin.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnLoginAsAdmin.setBackground(new Color(65, 105, 225));
		btnLoginAsAdmin.setBounds(405, 218, 209, 122);
		contentPane.add(btnLoginAsAdmin);
		
		JLabel lblCreatedBySushant = new JLabel("\u00A9 Created by Sushant Kulkarni and Vivek Pavaskar");
		lblCreatedBySushant.setFont(new Font("Monotype Corsiva", Font.ITALIC, 15));
		lblCreatedBySushant.setHorizontalAlignment(SwingConstants.CENTER);
		lblCreatedBySushant.setBounds(370, 449, 344, 31);
		contentPane.add(lblCreatedBySushant);
		
		JLabel lblAMiniProject = new JLabel("A MINI PROJECT VERSION");
		lblAMiniProject.setBounds(555, 61, 149, 16);
		contentPane.add(lblAMiniProject);
		
		
	}
}
