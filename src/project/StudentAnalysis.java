package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.proteanit.sql.DbUtils;

import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.Icon;
import javax.swing.JSeparator;

@SuppressWarnings({ "serial", "unused" })
public class StudentAnalysis extends JFrame {
	public static String profname;
	private JPanel contentPane;
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JTextField roll_field;
	private JTable StuTable;
	private JTable subtable;

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
					
					StudentAnalysis frame = new StudentAnalysis();
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public StudentAnalysis() {
		setTitle("AMS");setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddProfessor.class.getResource("/Icons/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 100, 730, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(248, 248, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		String subs[]=new String[100];
		int i=0;
		
		JLabel lblStudentAttendanceAnalysis = new JLabel("STUDENT ATTENDANCE ANALYSIS");
		lblStudentAttendanceAnalysis.setFont(new Font("Trebuchet MS", Font.BOLD, 35));
		lblStudentAttendanceAnalysis.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentAttendanceAnalysis.setBounds(66, 29, 617, 44);
		contentPane.add(lblStudentAttendanceAnalysis);
		
		JButton button = new JButton("\u2190");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProfAccessPage.profname=profname;
				ProfAccessPage.main(null);
				dispose();
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("SansSerif", Font.BOLD, 20));
		button.setBackground(SystemColor.inactiveCaptionText);
		button.setBounds(6, 6, 59, 33);
		contentPane.add(button);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(SystemColor.control);
		tabbedPane.setBounds(6, 85, 702, 406);
		contentPane.add(tabbedPane);
		
		JPanel LessThanTab = new JPanel();
		LessThanTab.setBackground(SystemColor.menu);
		tabbedPane.addTab("Less Than X%", (Icon) null, LessThanTab, null);
		LessThanTab.setLayout(null);
		
		JLabel lab1 = new JLabel("SHOW ALL STUDENTS  WITH ATTENDANCE PERCENTAGE LESS THAN:");
		lab1.setHorizontalAlignment(SwingConstants.CENTER);
		lab1.setFont(new Font("SansSerif", Font.BOLD, 12));
		lab1.setBounds(-5, 3, 440, 28);
		LessThanTab.add(lab1);
		
		JLabel lab3 = new JLabel("%");
		lab3.setHorizontalAlignment(SwingConstants.CENTER);
		lab3.setFont(new Font("SansSerif", Font.BOLD, 20));
		lab3.setBounds(494, 3, 17, 28);
		LessThanTab.add(lab3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			}
		});
		comboBox.setFont(new Font("Arial", Font.BOLD, 20));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"100", "95", "90", "85", "80", "75", "70", "65", "60", "55", "50", "45", "40", "35", "30", "25", "20", "15", "10", "5"}));
		comboBox.setBounds(423, 3, 66, 28);
		LessThanTab.add(comboBox);
		
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 96, 697, 280);
		scrollPane.setVisible(false);
		LessThanTab.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setEnabled(false);
		
		JLabel lblForSubject = new JLabel("FOR SUBJECT:");
		lblForSubject.setHorizontalAlignment(SwingConstants.RIGHT);
		lblForSubject.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblForSubject.setBounds(-153, 34, 254, 28);
		LessThanTab.add(lblForSubject);
		JComboBox comboBox_1 = new JComboBox();
		try {
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			String user="PROJECT";
			String pass="123456";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection db=DriverManager.getConnection(url, user, pass);
			String query="SELECT SUBCODE FROM SUBJECT";
			PreparedStatement s=db.prepareStatement(query);
			ResultSet res=s.executeQuery();
			while(res.next())
			{			
				subs[i]=res.getString(1);
				i++;
			}
		}catch (ClassNotFoundException | SQLException e2) {
			JOptionPane.showMessageDialog(null, e2,null,JOptionPane.ERROR_MESSAGE);
		}
		comboBox_1.setModel(new DefaultComboBoxModel(subs));
		comboBox_1.setFont(new Font("Arial", Font.BOLD, 20));
		comboBox_1.setBounds(106, 34, 142, 28);
		LessThanTab.add(comboBox_1);
		
		JButton btnSelect2 = new JButton("SHOW");
		btnSelect2.setFont(new Font("SansSerif", Font.BOLD, 15));
		btnSelect2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					scrollPane.setVisible(true);
					String perc=(String) comboBox.getSelectedItem();
					String choice=(String) comboBox_1.getSelectedItem();
					String url="jdbc:oracle:thin:@localhost:1521:XE";
					String user="PROJECT";
					String pass="123456";
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection db=DriverManager.getConnection(url, user, pass);
					String query="SELECT ATTENDS.ROLL,STUDENT.SNAME,PERCENT_ATTENDED,STUDENT.PHONE,STUDENT.PARENT_PHONE FROM ATTENDS,STUDENT where PERCENT_ATTENDED<? AND ATTENDS.ROLL=STUDENT.ROLL AND ATTENDS.SUBCODE=?";
					PreparedStatement s=db.prepareStatement(query);
					s.setString(1, perc);
					s.setString(2, (String) comboBox_1.getSelectedItem());
					ResultSet rs=s.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));
					table.setVisible(true);
					
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		btnSelect2.setBounds(602, 65, 90, 28);
		LessThanTab.add(btnSelect2);
		
		JPanel MoreThanTab = new JPanel();
		MoreThanTab.setBackground(SystemColor.menu);
		tabbedPane.addTab("More Than X%", null, MoreThanTab, null);
		MoreThanTab.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(5, 94, 697, 279);
		scrollPane_1.setVisible(false);
		MoreThanTab.add(scrollPane_1);
				
		JLabel lblShowAllStudents = new JLabel("SHOW ALL STUDENTS  WITH ATTENDANCE PERCENTAGE MORE THAN:");
		lblShowAllStudents.setHorizontalAlignment(SwingConstants.CENTER);
		lblShowAllStudents.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblShowAllStudents.setBounds(5, 4, 440, 28);
		MoreThanTab.add(lblShowAllStudents);
		
		JLabel label_1 = new JLabel("FOR SUBJECT:");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		label_1.setBounds(25, 36, 96, 28);
		MoreThanTab.add(label_1);
		
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(subs));
		comboBox_2.setFont(new Font("Arial", Font.BOLD, 20));
		comboBox_2.setBounds(131, 37, 142, 28);
		MoreThanTab.add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55", "60", "65", "70", "75", "80", "85", "90", "95"}));
		comboBox_3.setFont(new Font("Arial", Font.BOLD, 20));
		comboBox_3.setBounds(433, 4, 55, 28);
		MoreThanTab.add(comboBox_3);
		
		JLabel label_2 = new JLabel("%");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("SansSerif", Font.BOLD, 20));
		label_2.setBounds(497, 4, 17, 28);
		MoreThanTab.add(label_2);
		
		JLabel subname2 = new JLabel("");
		subname2.setHorizontalAlignment(SwingConstants.CENTER);
		subname2.setFont(new Font("SansSerif", Font.BOLD, 15));
		subname2.setBounds(10, 61, 686, 22);
		MoreThanTab.add(subname2);
		
		JButton button_2 = new JButton("SHOW");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					scrollPane_1.setVisible(true);
					String perc=(String) comboBox_3.getSelectedItem();
					String choice=(String) comboBox_2.getSelectedItem();
					String url="jdbc:oracle:thin:@localhost:1521:XE";
					String user="PROJECT";
					String pass="123456";
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection db=DriverManager.getConnection(url, user, pass);
					String query="SELECT ATTENDS.ROLL,STUDENT.SNAME,PERCENT_ATTENDED,STUDENT.PHONE,STUDENT.PARENT_PHONE FROM ATTENDS,STUDENT where PERCENT_ATTENDED>? AND ATTENDS.ROLL=STUDENT.ROLL AND ATTENDS.SUBCODE=?";
					PreparedStatement s=db.prepareStatement(query);
					s.setString(1, perc);
					s.setString(2, choice);
					ResultSet rs=s.executeQuery();
					table_1.setModel(DbUtils.resultSetToTableModel(rs));
					table_1.setVisible(true);
					
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		button_2.setFont(new Font("SansSerif", Font.BOLD, 15));
		button_2.setBounds(600, 61, 90, 28);
		MoreThanTab.add(button_2);
		
		
		
		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);
		
		JPanel Between = new JPanel();
		Between.setBackground(SystemColor.menu);
		tabbedPane.addTab("Between X% and Y%", null, Between, null);
		Between.setLayout(null);
		
		JLabel label = new JLabel("SHOW ALL STUDENTS  WITH ATTENDANCE PERCENTAGE MORE THAN:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("SansSerif", Font.BOLD, 12));
		label.setBounds(8, 8, 394, 28);
		Between.add(label);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setFont(new Font("Arial", Font.BOLD, 20));
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55", "60", "65", "70", "75", "80", "85", "90", "95"}));
		comboBox_4.setBounds(410, 8, 55, 28);
		Between.add(comboBox_4);
		
		JLabel label_3 = new JLabel("%");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("SansSerif", Font.BOLD, 20));
		label_3.setBounds(473, 8, 17, 28);
		Between.add(label_3);
		
		JLabel lblAndLessThan = new JLabel("AND LESS THAN");
		lblAndLessThan.setHorizontalAlignment(SwingConstants.CENTER);
		lblAndLessThan.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblAndLessThan.setBounds(498, 8, 91, 28);
		Between.add(lblAndLessThan);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"100", "95", "90", "85", "80", "75", "70", "65", "60", "55", "50", "45", "40", "35", "30", "25", "20", "15", "10", "5"}));
		comboBox_5.setFont(new Font("Arial", Font.BOLD, 20));
		comboBox_5.setBounds(597, 8, 66, 28);
		Between.add(comboBox_5);
		
		JLabel label_4 = new JLabel("%");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("SansSerif", Font.BOLD, 20));
		label_4.setBounds(671, 8, 17, 28);
		Between.add(label_4);
		
		JLabel label_5 = new JLabel("FOR SUBJECT:");
		label_5.setHorizontalAlignment(SwingConstants.RIGHT);
		label_5.setFont(new Font("SansSerif", Font.BOLD, 12));
		label_5.setBounds(8, 36, 91, 28);
		Between.add(label_5);
		
		JComboBox comboBox_6 = new JComboBox();
		comboBox_6.setModel(new DefaultComboBoxModel(subs));
		comboBox_6.setFont(new Font("Arial", Font.BOLD, 20));
		comboBox_6.setBounds(111, 36, 133, 28);
		Between.add(comboBox_6);
		
		JLabel LABEL3 = new JLabel("");
		LABEL3.setHorizontalAlignment(SwingConstants.CENTER);
		LABEL3.setFont(new Font("SansSerif", Font.BOLD, 15));
		LABEL3.setBounds(8, 90, 681, 22);
		Between.add(LABEL3);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(6, 112, 686, 266);
		scrollPane_2.setVisible(false);
		Between.add(scrollPane_2);
		
		JButton BUTTON3 = new JButton("SHOW");
		BUTTON3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					scrollPane_2.setVisible(true);
					String perc1=(String) comboBox_4.getSelectedItem();
					String perc2=(String) comboBox_5.getSelectedItem();
					String choice=(String) comboBox_6.getSelectedItem();
					String url="jdbc:oracle:thin:@localhost:1521:XE";
					String user="PROJECT";
					String pass="123456";
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection db=DriverManager.getConnection(url, user, pass);
					String query="SELECT ATTENDS.ROLL,STUDENT.SNAME,PERCENT_ATTENDED,STUDENT.PHONE,STUDENT.PARENT_PHONE FROM ATTENDS,STUDENT where PERCENT_ATTENDED>? AND PERCENT_ATTENDED<? AND ATTENDS.ROLL=STUDENT.ROLL AND ATTENDS.SUBCODE=?";
					PreparedStatement s=db.prepareStatement(query);
					s.setString(1, perc1);
					s.setString(2, perc2);
					s.setString(3, (String) comboBox_6.getSelectedItem());
					ResultSet rs=s.executeQuery();
					table_2.setModel(DbUtils.resultSetToTableModel(rs));
					table_2.setVisible(true);
					
					
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
			}
		});
		BUTTON3.setFont(new Font("SansSerif", Font.BOLD, 15));
		BUTTON3.setBounds(587, 79, 90, 28);
		Between.add(BUTTON3);
		
		
		
		table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(SystemColor.menu);
		tabbedPane.addTab("For A Specific Student", null, panel_2, null);
		panel_2.setLayout(null);
		
		roll_field = new JTextField();
		roll_field.setHorizontalAlignment(SwingConstants.CENTER);
		roll_field.setFont(new Font("Courier New", Font.BOLD, 20));
		roll_field.setBounds(476, 11, 74, 28);
		panel_2.add(roll_field);
		roll_field.setColumns(10);
		
		JLabel lblStudentDetails = new JLabel("STUDENT DETAILS:");
		lblStudentDetails.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblStudentDetails.setBounds(6, 88, 153, 34);
		panel_2.add(lblStudentDetails);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(0, 124, 702, 50);
		scrollPane_3.setVisible(false);
		panel_2.add(scrollPane_3);
		
		StuTable = new JTable();
		
		scrollPane_3.setViewportView(StuTable);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(0, 206, 702, 364);
		scrollPane_4.setVisible(false);
		panel_2.add(scrollPane_4);
		
		subtable = new JTable();
		
		scrollPane_4.setViewportView(subtable);
		
		JLabel lblMeDetails = new JLabel("SUBJECT ATTENDANCE DETAILS:");
		lblMeDetails.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblMeDetails.setBounds(6, 176, 253, 28);
		panel_2.add(lblMeDetails);
		
		JButton btnFetchData = new JButton("FETCH DATA \u2193\r\n");
		btnFetchData.setForeground(new Color(255, 255, 255));
		btnFetchData.setBackground(new Color(65, 105, 225));
		btnFetchData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					scrollPane_3.setVisible(true);
					scrollPane_4.setVisible(true);
					String url="jdbc:oracle:thin:@localhost:1521:XE";
					String user="PROJECT";
					String pass="123456";
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection db=DriverManager.getConnection(url, user, pass);
					String q1="SELECT * FROM STUDENT WHERE ROLL=?";
					PreparedStatement s=db.prepareStatement(q1);
					s.setString(1, roll_field.getText());
					ResultSet res=s.executeQuery();
					StuTable.setModel(DbUtils.resultSetToTableModel(res));
					
					q1="SELECT A.SUBCODE,S.SUBNAME,A.DAYS_ATTENDED,A.TOTAL_DAYS,A.PERCENT_ATTENDED FROM ATTENDS A,SUBJECT S WHERE ROLL=? AND A.SUBCODE=S.SUBCODE";
				    s=db.prepareStatement(q1);
					s.setString(1, roll_field.getText());
					res=s.executeQuery();
					subtable.setModel(DbUtils.resultSetToTableModel(res));
															
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null, e1,"",JOptionPane.ERROR_MESSAGE);
				} 
				
			}
		});
		btnFetchData.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnFetchData.setBounds(214, 54, 118, 36);
		panel_2.add(btnFetchData);
		
		JLabel lblEnterRollNumber = new JLabel("ENTER  ROLL NUMBER OF THE STUDENT:");
		lblEnterRollNumber.setHorizontalAlignment(SwingConstants.LEFT);
		lblEnterRollNumber.setFont(new Font("SansSerif", Font.BOLD, 15));
		lblEnterRollNumber.setBounds(151, 2, 313, 46);
		panel_2.add(lblEnterRollNumber);
		
		JButton btnClearData = new JButton("CLEAR DATA\r\n");
		btnClearData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel dtm = (DefaultTableModel) StuTable.getModel();
				dtm.setRowCount(0);
				DefaultTableModel dtm2 = (DefaultTableModel) subtable.getModel();
				dtm2.setRowCount(0);
			}
		});
		btnClearData.setForeground(Color.WHITE);
		btnClearData.setFont(new Font("SansSerif", Font.BOLD, 12));
		btnClearData.setBackground(new Color(72, 61, 139));
		btnClearData.setBounds(376, 54, 118, 36);
		panel_2.add(btnClearData);
	}
}
