package project;
import java.sql.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;

@SuppressWarnings({ "serial", "unused" })
public class AddStudent extends JFrame {

	private JPanel contentPane;
	private JTextField usn_field;
	private JTextField roll_field;
	private JTextField name_field;
	private JTextField phone_field;
	private JTextField parent_phone_field;

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
					
					AddStudent frame = new AddStudent();
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
	public AddStudent() {
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
		
		String subs[]=new String[100];
		
		
		JLabel lblStudentForm = new JLabel("ENTER STUDENT INFORMATION");
		lblStudentForm.setForeground(Color.BLACK);
		lblStudentForm.setFont(new Font("Trebuchet MS", Font.BOLD, 36));
		lblStudentForm.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentForm.setBounds(10, 31, 714, 43);
		contentPane.add(lblStudentForm);
		
		usn_field = new JTextField();
		usn_field.setBackground(SystemColor.text);
		usn_field.setFont(new Font("Courier New", Font.BOLD, 20));
		usn_field.setBounds(312, 143, 222, 29);
		contentPane.add(usn_field);
		usn_field.setColumns(10);
		
		roll_field = new JTextField();
		roll_field.setBackground(SystemColor.text);
		roll_field.setFont(new Font("Courier New", Font.BOLD, 20));
		roll_field.setToolTipText("");
		roll_field.setColumns(10);
		roll_field.setBounds(312, 98, 222, 29);
		contentPane.add(roll_field);
		
		name_field = new JTextField();
		name_field.setBackground(SystemColor.text);
		name_field.setFont(new Font("Courier New", Font.BOLD, 20));
		name_field.setColumns(10);
		name_field.setBounds(312, 188, 222, 29);
		contentPane.add(name_field);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(312, 228, 222, 76);
		contentPane.add(scrollPane);
		
		JTextArea address_field = new JTextArea();
		scrollPane.setViewportView(address_field);
		address_field.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, true));
		address_field.setBackground(SystemColor.text);
		address_field.setFont(new Font("Courier New", Font.BOLD, 20));
		address_field.setLineWrap(true);
		
		phone_field = new JTextField();
		phone_field.setBackground(SystemColor.text);
		phone_field.setFont(new Font("Courier New", Font.BOLD, 20));
		phone_field.setColumns(10);
		phone_field.setBounds(312, 320, 222, 29);
		contentPane.add(phone_field);
		
		parent_phone_field = new JTextField();
		parent_phone_field.setBackground(SystemColor.text);
		parent_phone_field.setFont(new Font("Courier New", Font.BOLD, 20));
		parent_phone_field.setColumns(10);
		parent_phone_field.setBounds(312, 365, 222, 29);
		contentPane.add(parent_phone_field);
		
		JButton btnNewButton = new JButton("REGISTER");
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((JOptionPane.showConfirmDialog(null, "Are you sure you want to Insert this data into the Database?", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION))==JOptionPane.YES_OPTION)
				{ 	int i=0;
					String url="jdbc:oracle:thin:@localhost:1521:XE";
					String user="PROJECT";
					String pass="123456";
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection db=DriverManager.getConnection(url, user, pass);
						
						String query="insert into STUDENT values(?,?,?,?,?,?)";
						PreparedStatement s=db.prepareStatement(query);
						
						String roll=roll_field.getText();
						String usn=usn_field.getText();
						String name=name_field.getText();
						String address=address_field.getText();
						String ph=phone_field.getText();
						String parent_ph=parent_phone_field.getText();
						s.setString(1,roll);
						s.setString(2, usn);
						s.setString(3, name);
						s.setString(4, address);
						s.setString(5, ph);
						s.setString(6, parent_ph);
						
						int x=s.executeUpdate();
						boolean flag=false;
						s.close();
						String query21 = "SELECT SUBCODE FROM SUBJECT";
						PreparedStatement s21 = db.prepareStatement(query21);
						ResultSet res = s21.executeQuery();
						while (res.next()) {
							flag=true;
							subs[i] = res.getString("SUBCODE");
							String q1 = "insert into ATTENDS values(?,?,0,0,0)";
							PreparedStatement s1 = db.prepareStatement(q1);
							s1.setString(1, roll);
							s1.setString(2, subs[i]);
							i++;
							int i1 = s1.executeUpdate();
							
						}
						s21.close();
						if(x>0 && flag==true)
						{	usn_field.setText("");
							roll_field.setText("");
							name_field.setText("");
							address_field.setText("");
							phone_field.setText("");
							parent_phone_field.setText("");
							JOptionPane.showMessageDialog(null, "Data inserted successfully into database");
						}
						else JOptionPane.showMessageDialog(null, "could not insert data","error",JOptionPane.ERROR_MESSAGE);
						db.close();
					} catch (ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		btnNewButton.setBounds(256, 435, 202, 50);
		contentPane.add(btnNewButton);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(105, 105, 105));
		separator.setBackground(new Color(0, 0, 0));
		separator.setBounds(10, 85, 694, 2);
		contentPane.add(separator);
		
		JLabel lblUsn = new JLabel("USN:");
		lblUsn.setForeground(Color.BLACK);
		lblUsn.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblUsn.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsn.setBounds(121, 144, 193, 29);
		contentPane.add(lblUsn);
		
		JLabel lblRollNumber = new JLabel("ROLL NUMBER:");
		lblRollNumber.setForeground(Color.BLACK);
		lblRollNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblRollNumber.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRollNumber.setBounds(121, 98, 193, 34);
		contentPane.add(lblRollNumber);
		
		JLabel lblName = new JLabel("NAME:");
		lblName.setForeground(Color.BLACK);
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblName.setBounds(121, 183, 193, 34);
		contentPane.add(lblName);
		
		JLabel lblAddress = new JLabel("ADDRESS:");
		lblAddress.setForeground(Color.BLACK);
		lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAddress.setBounds(121, 231, 193, 34);
		contentPane.add(lblAddress);
		
		JLabel lblPhone = new JLabel("PHONE:");
		lblPhone.setForeground(Color.BLACK);
		lblPhone.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhone.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblPhone.setBounds(121, 315, 193, 34);
		contentPane.add(lblPhone);
		
		JLabel lblParentsPhone = new JLabel("PARENT'S PHONE:");
		lblParentsPhone.setForeground(Color.BLACK);
		lblParentsPhone.setHorizontalAlignment(SwingConstants.CENTER);
		lblParentsPhone.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblParentsPhone.setBounds(121, 360, 193, 34);
		contentPane.add(lblParentsPhone);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(new Color(0, 0, 0));
		separator_1.setBounds(10, 421, 694, 2);
		contentPane.add(separator_1);
		
		JButton btnback = new JButton("\u2190");
		btnback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminAccessPage.main(null);
				dispose();
			}
		});
		btnback.setFont(new Font("SansSerif", Font.BOLD, 21));
		btnback.setBackground(Color.BLACK);
		btnback.setForeground(new Color(255, 255, 255));
		btnback.setBounds(2, 2, 59, 34);
		contentPane.add(btnback);
		
		
	}
}
