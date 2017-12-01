package project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JSeparator;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Color;

@SuppressWarnings({ "serial", "unused" })
public class InitSchema extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;

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
				
				InitSchema frame = new InitSchema();
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
public InitSchema() {
	setTitle("AMS");setLocationRelativeTo(null);
setIconImage(Toolkit.getDefaultToolkit().getImage(AddProfessor.class.getResource("/Icons/icon.png")));
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
setBounds(600, 200, 730, 530);
contentPane = new JPanel();
contentPane.setBackground(SystemColor.menu);
contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
setContentPane(contentPane);

JLabel lblUsername = new JLabel("DBA USERNAME:");
lblUsername.setFont(new Font("SansSerif", Font.BOLD, 20));
lblUsername.setHorizontalAlignment(SwingConstants.LEFT);
lblUsername.setBounds(110, 137, 201, 34);

JLabel lblPassword = new JLabel("SYSTEM PASSWORD:");
lblPassword.setFont(new Font("SansSerif", Font.BOLD, 20));
lblPassword.setBounds(110, 194, 217, 34);

JLabel lblAdminUsername = new JLabel("ENTER ADMIN USERNAME:");
lblAdminUsername.setFont(new Font("SansSerif", Font.BOLD, 20));
lblAdminUsername.setBounds(110, 276, 261, 34);

JLabel lblAdminPassword = new JLabel("ENTER ADMIN PASSWORD:\r\n");
lblAdminPassword.setFont(new Font("SansSerif", Font.BOLD, 20));
lblAdminPassword.setBounds(110, 333, 269, 34);

JLabel lblSystem = new JLabel("SYSTEM");
lblSystem.setHorizontalAlignment(SwingConstants.LEFT);
lblSystem.setFont(new Font("Courier New", Font.BOLD | Font.ITALIC, 20));
lblSystem.setBounds(385, 137, 137, 34);

textField = new JTextField();
textField.setFont(new Font("Courier New", Font.BOLD, 15));
textField.setBackground(SystemColor.text);
textField.setBounds(385, 276, 217, 34);
textField.setColumns(10);

JLabel lblInitialSetupFor = new JLabel("Initializing the AMS...");
lblInitialSetupFor.setHorizontalAlignment(SwingConstants.CENTER);
lblInitialSetupFor.setFont(new Font("Monotype Corsiva", Font.PLAIN, 55));
lblInitialSetupFor.setBounds(84, 23, 546, 66);

JButton btnCreateAdmin = new JButton("CREATE ADMIN");
btnCreateAdmin.setForeground(new Color(255, 255, 255));
btnCreateAdmin.setBackground(new Color(65, 105, 225));
btnCreateAdmin.setFont(new Font("Tahoma", Font.BOLD, 20));
btnCreateAdmin.setBounds(252, 404, 209, 70);
btnCreateAdmin.addActionListener(new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {

		@SuppressWarnings("deprecation")
		String pass = passwordField.getText();
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection db = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", pass);
			String query = "create user PROJECT identified by 123456";
			Statement s = db.createStatement();
			ResultSet r = s.executeQuery(query);
			query = "grant connect,resource to PROJECT";
			s = db.createStatement();
			r = s.executeQuery(query);
			query = "grant create view to PROJECT";
			s = db.createStatement();
			r = s.executeQuery(query);
			query = "grant all privileges to PROJECT";
			s = db.createStatement();
			r = s.executeQuery(query);
			db.close();

		} catch (ClassNotFoundException | SQLException e1) {
			JOptionPane.showMessageDialog(null, "Schema not created\n(or)\nSchema already exists");
			e1.printStackTrace();
		}

try {
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection db1 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "PROJECT","123456");
			
	String query = "CREATE TABLE ADMIN(USERNAME VARCHAR2(20),PASSWORD VARCHAR2(20))"; //TESTED OK
	Statement s = db1.createStatement();
	boolean r = s.execute(query);

	query = "CREATE TABLE PROF(PID VARCHAR(10),PNAME VARCHAR(50),USERNAME VARCHAR(20),PASSWORD VARCHAR(20),PHONE VARCHAR(10), PRIMARY KEY (PID) ENABLE)";
	s = db1.createStatement();
	r = s.execute(query); //TESTED OK

	query = "CREATE TABLE STUDENT(ROLL VARCHAR(10),USN VARCHAR(10),SNAME VARCHAR(50),ADDRESS VARCHAR(50),PHONE VARCHAR(10),PARENT_PHONE VARCHAR(10),PRIMARY KEY (ROLL) ENABLE)";
	s = db1.createStatement();
	r = s.execute(query);//TESTED OK

	query = "CREATE TABLE SUBJECT(SUBCODE VARCHAR(10),SUBNAME VARCHAR(50),PRIMARY KEY (SUBCODE) ENABLE)";
	s = db1.createStatement();
	r = s.execute(query);//TESTED OK

	query = "CREATE TABLE  TEACHES (PID VARCHAR(10),SUBCODE VARCHAR(10),SEMESTER VARCHAR2(10),PRIMARY KEY(PID,SUBCODE) ENABLE)";
	s = db1.createStatement();
	r = s.execute(query);//TESTED OK

	query = "ALTER TABLE TEACHES ADD FOREIGN KEY (PID) REFERENCES PROF(PID) ENABLE";
	s = db1.createStatement();
	r = s.execute(query);//TESTED OK

	query = "ALTER TABLE TEACHES ADD FOREIGN KEY (SUBCODE) REFERENCES SUBJECT (SUBCODE) ENABLE";
	s = db1.createStatement();
	r = s.execute(query);//TESTED OK

	query = "CREATE TABLE  ATTENDS (ROLL VARCHAR(10),SUBCODE VARCHAR(10),DAYS_ATTENDED NUMBER(5,0),TOTAL_DAYS NUMBER(5,0),PERCENT_ATTENDED NUMBER(5,2),PRIMARY KEY(ROLL,SUBCODE) ENABLE)";
	s = db1.createStatement();
	r = s.execute(query);//TESTED OK

	query = "ALTER TABLE ATTENDS ADD FOREIGN KEY (ROLL) REFERENCES STUDENT(ROLL) ENABLE";
	s = db1.createStatement();
	r = s.execute(query);//TESTED OK

	query = "ALTER TABLE ATTENDS ADD FOREIGN KEY (SUBCODE) REFERENCES SUBJECT (SUBCODE) ENABLE";
	s = db1.createStatement();
	r = s.execute(query);//TESTED OK
	
	query = "CREATE VIEW STUDENT_VIEW AS SELECT * FROM STUDENT";
	s = db1.createStatement();
	r = s.execute(query);//TESTED OK

	String auser = textField.getText();
	@SuppressWarnings("deprecation")
	String apass = passwordField_1.getText();
	query = "insert into ADMIN values('" + auser + "','" + apass + "')";
	s = db1.createStatement();
	r = s.execute(query);

	db1.close();
	dispose();
	new LoginPage().setVisible(true);
	JOptionPane.showMessageDialog(null, "Admin Successfully Created. \nUse this Admin Login to Add Students, Subjects, Professors or other Admins.\nADD DETAILS ABOUT THE SUBJECT BEFORE ADDING PROFESSORS OR STUDENTS.");

		} catch (ClassNotFoundException | SQLException e1) {
			JOptionPane.showMessageDialog(null, "admin not created");
			}

			}
		});

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Courier New", Font.BOLD, 15));
		passwordField.setBackground(SystemColor.text);
		passwordField.setBounds(385, 194, 217, 34);

		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("Courier New", Font.BOLD, 15));
		passwordField_1.setBackground(SystemColor.text);
		passwordField_1.setBounds(385, 333, 217, 34);
		contentPane.setLayout(null);
		contentPane.add(lblAdminPassword);
		contentPane.add(lblPassword);
		contentPane.add(lblUsername);
		contentPane.add(lblAdminUsername);
		contentPane.add(passwordField_1);
		contentPane.add(lblSystem);
		contentPane.add(textField);
		contentPane.add(passwordField);
		contentPane.add(lblInitialSetupFor);
		contentPane.add(btnCreateAdmin);
		JSeparator separator = new JSeparator();
		separator.setBounds(27, 112, 659, 2);
		contentPane.add(separator);
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(27, 251, 659, 2);
		contentPane.add(separator_1);
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(27, 390, 659, 2);
		contentPane.add(separator_2);
	}
}
