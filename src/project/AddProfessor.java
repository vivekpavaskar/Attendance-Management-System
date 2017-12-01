package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
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
import java.security.GeneralSecurityException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;

@SuppressWarnings({ "unused", "serial" })
public class AddProfessor extends JFrame {

	private JPanel contentPane;
	private JTextField pid_field;
	private JTextField name_field;
	private JTextField user_field;
	private JTextField phone_field;
	private JPasswordField pass_field;
	private JTextField subcode_field;
	private JTextField sem_field;

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
					
					AddProfessor frame = new AddProfessor();
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
	public AddProfessor() {
		setLocationRelativeTo(null);
		setTitle("AMS");
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddProfessor.class.getResource("/Icons/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 200, 730, 530);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnback = new JButton("\u2190");
		btnback.setBounds(2, 2, 65, 33);
		btnback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			AdminAccessPage.main(null);
			dispose();
			}
		});
		contentPane.setLayout(null);
		btnback.setForeground(new Color(255, 255, 255));
		btnback.setBackground(new Color(0, 128, 128));
		btnback.setFont(new Font("SansSerif", Font.BOLD, 20));
		contentPane.add(btnback);
		
		JLabel lblEnterProfessorInformation = new JLabel("ENTER PROFESSOR INFORMATION");
		lblEnterProfessorInformation.setBounds(52, 3, 609, 43);
		lblEnterProfessorInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterProfessorInformation.setForeground(Color.BLACK);
		lblEnterProfessorInformation.setFont(new Font("Trebuchet MS", Font.BOLD, 36));
		contentPane.add(lblEnterProfessorInformation);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(12, 49, 702, 2);
		contentPane.add(separator);
		
		JLabel lblProfessorId = new JLabel("PROFESSOR ID");
		lblProfessorId.setBounds(77, 79, 306, 43);
		lblProfessorId.setHorizontalAlignment(SwingConstants.CENTER);
		lblProfessorId.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		contentPane.add(lblProfessorId);
		
		pid_field = new JTextField();
		pid_field.setBounds(384, 79, 235, 43);
		pid_field.setBackground(SystemColor.text);
		pid_field.setFont(new Font("Courier New", Font.BOLD, 17));
		contentPane.add(pid_field);
		pid_field.setColumns(10);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setBounds(77, 125, 306, 43);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		contentPane.add(lblName);
		
		name_field = new JTextField();
		name_field.setBounds(384, 125, 235, 43);
		name_field.setBackground(SystemColor.text);
		name_field.setFont(new Font("Courier New", Font.BOLD, 17));
		name_field.setColumns(10);
		contentPane.add(name_field);
		
		JLabel lblUsername = new JLabel("USERNAME");
		lblUsername.setBounds(77, 171, 306, 43);
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		contentPane.add(lblUsername);
		
		user_field = new JTextField();
		user_field.setBounds(384, 171, 235, 43);
		user_field.setBackground(SystemColor.text);
		user_field.setFont(new Font("Courier New", Font.BOLD, 17));
		user_field.setColumns(10);
		contentPane.add(user_field);
		
		JLabel lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(77, 217, 306, 43);
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		contentPane.add(lblPassword);
		
		JLabel lblPhoneNumber = new JLabel("PHONE NUMBER");
		lblPhoneNumber.setBounds(77, 263, 306, 43);
		lblPhoneNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhoneNumber.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		contentPane.add(lblPhoneNumber);
		
		phone_field = new JTextField();
		phone_field.setBounds(384, 263, 235, 43);
		phone_field.setBackground(SystemColor.text);
		phone_field.setFont(new Font("Courier New", Font.BOLD, 17));
		phone_field.setColumns(10);
		contentPane.add(phone_field);
		
		pass_field = new JPasswordField();
		pass_field.setBounds(384, 217, 235, 43);
		pass_field.setBackground(SystemColor.text);
		pass_field.setFont(new Font("Courier New", Font.BOLD, 17));
		contentPane.add(pass_field);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(2, 422, 702, 2);
		contentPane.add(separator_1);
		
		JButton btnRegister = new JButton("REGISTER");
		btnRegister.setBounds(252, 427, 210, 56);
		btnRegister.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				if((JOptionPane.showConfirmDialog(null, "Are you sure you want to Insert this data into the Database?", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION))==JOptionPane.YES_OPTION)
				{ 
					String url="jdbc:oracle:thin:@localhost:1521:XE";
					String user="PROJECT";
					String pass="123456";
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection db=DriverManager.getConnection(url, user, pass);
						String query="insert into PROF values(?,?,?,?,?)";
						
						PreparedStatement s=db.prepareStatement(query);
						
						String pid=pid_field.getText();
						String name=name_field.getText();
						String user1=user_field.getText();	
						String pass1=pass_field.getText();
						String phone=phone_field.getText();
						String subcode=subcode_field.getText();
						String sem=sem_field.getText();
						
						s.setString(1,pid);
						s.setString(2, name);
						s.setString(3, user1);
						s.setString(4, pass1);
						s.setString(5, phone);
						
						String query3="SELECT * FROM SUBJECT WHERE SUBCODE=?";						
						PreparedStatement s3=db.prepareStatement(query3);
						s3.setString(1,subcode);
						ResultSet ress=s3.executeQuery();
						int i=-1;
						int i2=-1;
						if(ress.next())
						{	
							String query2="insert into TEACHES values(?,?,?)";						
							PreparedStatement s2=db.prepareStatement(query2);
							s2.setString(1,pid);
							s2.setString(2, subcode);
							s2.setString(3,sem);
							
							 i=s.executeUpdate();
							 i2=s2.executeUpdate();
						}
						else { }
						
						if(i>0 && i2>0) 
						{ 							
							pid_field.setText("");
						    name_field.setText("");
							user_field.setText("");	
							pass_field.setText("");
							phone_field.setText("");
							subcode_field.setText("");
							sem_field.setText("");
							JOptionPane.showMessageDialog(null, "Data inserted successfully into database");
						}
						else {
							JOptionPane.showMessageDialog(null, "Data insertion failed","Error",JOptionPane.ERROR_MESSAGE);
						}
						
					} catch (ClassNotFoundException | SQLException e1) {
						JOptionPane.showMessageDialog(null, e1,"error",JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		btnRegister.setForeground(new Color(255, 255, 255));
		btnRegister.setBackground(new Color(0, 128, 128));
		btnRegister.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		contentPane.add(btnRegister);
		
		JLabel lblSubjectTaught = new JLabel("CODE OF THE SUBJECT TAUGHT");
		lblSubjectTaught.setBounds(77, 309, 306, 43);
		lblSubjectTaught.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubjectTaught.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		contentPane.add(lblSubjectTaught);
		
		subcode_field = new JTextField();
		subcode_field.setBounds(384, 309, 235, 43);
		subcode_field.setFont(new Font("Courier New", Font.BOLD, 17));
		subcode_field.setColumns(10);
		subcode_field.setBackground(SystemColor.text);
		contentPane.add(subcode_field);
		
		JLabel lblSemester = new JLabel("SEMESTER\r\n");
		lblSemester.setBounds(77, 355, 306, 43);
		lblSemester.setHorizontalAlignment(SwingConstants.CENTER);
		lblSemester.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		contentPane.add(lblSemester);
		
		sem_field = new JTextField();
		sem_field.setBounds(384, 355, 235, 43);
		sem_field.setFont(new Font("Courier New", Font.BOLD, 17));
		sem_field.setColumns(10);
		sem_field.setBackground(SystemColor.text);
		contentPane.add(sem_field);
	}
}
