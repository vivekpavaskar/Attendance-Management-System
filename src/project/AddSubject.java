package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;

@SuppressWarnings({ "unused", "serial" })
public class AddSubject extends JFrame {

	private JPanel contentPane;
	private JTextField subcode;
	private JTextField subname;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
					
					AddSubject frame = new AddSubject();
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
	public AddSubject() {
		setLocationRelativeTo(null);
		setTitle("AMS");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddProfessor.class.getResource("/Icons/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 200, 730, 530);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnback = new JButton("\u2190");
		btnback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			AdminAccessPage.main(null);
			dispose();
			}
		});
		btnback.setForeground(new Color(255, 255, 255));
		btnback.setBackground(Color.BLACK);
		btnback.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnback.setBounds(2, 2, 67, 33);
		contentPane.add(btnback);
		
		JLabel lblEnterProfessorInformation = new JLabel("ENTER SUBJECT INFORMATION");
		lblEnterProfessorInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterProfessorInformation.setForeground(Color.BLACK);
		lblEnterProfessorInformation.setFont(new Font("Trebuchet MS", Font.BOLD, 36));
		lblEnterProfessorInformation.setBounds(2, 36, 714, 43);
		contentPane.add(lblEnterProfessorInformation);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(2, 98, 702, 2);
		contentPane.add(separator);
		
		JLabel lblName = new JLabel("SUBJECT CODE");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblName.setBounds(112, 202, 235, 43);
		contentPane.add(lblName);
		
		subcode = new JTextField();
		subcode.setBackground(SystemColor.inactiveCaptionBorder);
		subcode.setFont(new Font("Courier New", Font.BOLD, 17));
		subcode.setColumns(10);
		subcode.setBounds(348, 202, 235, 43);
		contentPane.add(subcode);
		
		JLabel lblUsername = new JLabel("SUBJECT NAME");
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblUsername.setBounds(112, 258, 235, 43);
		contentPane.add(lblUsername);
		
		subname = new JTextField();
		subname.setBackground(SystemColor.inactiveCaptionBorder);
		subname.setFont(new Font("Courier New", Font.BOLD, 17));
		subname.setColumns(10);
		subname.setBounds(348, 258, 235, 43);
		contentPane.add(subname);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(2, 411, 702, 2);
		contentPane.add(separator_1);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
				if((JOptionPane.showConfirmDialog(null, "Are you sure you want to Insert this data into the Database?", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION))==JOptionPane.YES_OPTION)
				{ 
					String url="jdbc:oracle:thin:@localhost:1521:XE";
					String user="PROJECT";
					String pass="123456";
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection db=DriverManager.getConnection(url, user, pass);
						String query="insert into SUBJECT values(?,?)";
						PreparedStatement s=db.prepareStatement(query);
						String code=subcode.getText();
						String name=subname.getText();	
						s.setString(1, code);
						s.setString(2, name);
						int i=s.executeUpdate();
						if(i>0) 
						{ 
						  subcode.setText("");
						  subname.setText("");
						  JOptionPane.showMessageDialog(null, "Data inserted successfully into database"); 
						}
						else 
						{
							JOptionPane.showMessageDialog(null, "Data insertion failed","Error",JOptionPane.ERROR_MESSAGE);
						}
					
					} 
					catch (ClassNotFoundException | SQLException e1) 
					{
						JOptionPane.showMessageDialog(null, e1,"error",JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		btnRegister.setForeground(new Color(255, 255, 255));
		btnRegister.setBackground(Color.BLACK);
		btnRegister.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		btnRegister.setBounds(252, 424, 210, 56);
		contentPane.add(btnRegister);
	}
}
