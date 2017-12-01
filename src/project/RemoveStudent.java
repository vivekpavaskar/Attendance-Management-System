package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.Font;
import javax.swing.JSeparator;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;

@SuppressWarnings({ "unused", "serial" })
public class RemoveStudent extends JFrame {

	private JPanel contentPane;
	private JTextField roll_entered;
	private JTable table;

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
					
					RemoveStudent frame = new RemoveStudent();
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
	public RemoveStudent() {
		setTitle("AMS");setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddProfessor.class.getResource("/Icons/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 200, 730, 530);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRemoveAStudent = new JLabel("REMOVE A STUDENT ");
		lblRemoveAStudent.setForeground(Color.BLACK);
		lblRemoveAStudent.setFont(new Font("Trebuchet MS", Font.BOLD, 40));
		lblRemoveAStudent.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemoveAStudent.setBounds(0, 31, 714, 62);
		contentPane.add(lblRemoveAStudent);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.GRAY);
		separator.setBackground(Color.BLACK);
		separator.setBounds(10, 104, 694, 2);
		contentPane.add(separator);
		
		JLabel lblEnterTheRoll = new JLabel("ENTER THE ROLL NUMBER");
		lblEnterTheRoll.setForeground(Color.BLACK);
		lblEnterTheRoll.setHorizontalAlignment(SwingConstants.LEFT);
		lblEnterTheRoll.setFont(new Font("Century Gothic", Font.BOLD, 25));
		lblEnterTheRoll.setBounds(34, 117, 422, 26);
		contentPane.add(lblEnterTheRoll);
		
		roll_entered = new JTextField();
		roll_entered.setBackground(SystemColor.text);
		roll_entered.setForeground(Color.black);
		roll_entered.setFont(new Font("Courier New", Font.BOLD, 30));
		roll_entered.setHorizontalAlignment(SwingConstants.CENTER);
		roll_entered.setBounds(442, 139, 86, 42);
		contentPane.add(roll_entered);
		roll_entered.setColumns(10);
		
		JLabel lblOfTheStudent = new JLabel("OF THE STUDENT TO BE DELETED:");
		lblOfTheStudent.setForeground(Color.BLACK);
		lblOfTheStudent.setHorizontalAlignment(SwingConstants.LEFT);
		lblOfTheStudent.setFont(new Font("Century Gothic", Font.BOLD, 25));
		lblOfTheStudent.setBounds(30, 147, 382, 26);
		contentPane.add(lblOfTheStudent);
		
		JButton btnRemove = new JButton("REMOVE");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this student's records? THIS ACTION IS NOT REVOKABLE.", "Confirmation", JOptionPane.YES_NO_OPTION))==JOptionPane.YES_OPTION)
				{
					String url="jdbc:oracle:thin:@localhost:1521:XE";
					String user="PROJECT";
					String pass="123456";
					
						try {
							Class.forName("oracle.jdbc.driver.OracleDriver");
							Connection db=DriverManager.getConnection(url, user, pass);							
							String roll=roll_entered.getText();
							String query2="DELETE ATTENDS WHERE ROLL=?";
							PreparedStatement s2=db.prepareStatement(query2);
							s2.setString(1, roll);
							int res2=s2.executeUpdate();
							String query="delete STUDENT where roll="+roll;
							Statement s=db.createStatement();
							ResultSet res=s.executeQuery(query);
							if(res.next() && res2>0)
							{	roll_entered.setText("");
								JOptionPane.showMessageDialog(null, "roll number "+roll+"'s data deleted");
							}
							else
							{
								roll_entered.setText("");
								JOptionPane.showMessageDialog(null, "There is no data for roll number entered in database.","error",JOptionPane.ERROR_MESSAGE);
							}
					} catch (ClassNotFoundException | SQLException e1) {
							JOptionPane.showMessageDialog(null, e1);
						}
						
						
						
				}
			}
		});
		btnRemove.setFont(new Font("SansSerif", Font.BOLD, 30));
		btnRemove.setBackground(Color.BLACK);
		btnRemove.setForeground(new Color(255, 255, 255));
		btnRemove.setBounds(265, 398, 191, 73);
		contentPane.add(btnRemove);
		
		JButton btnback = new JButton("\u2190");
		btnback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminAccessPage.main(null);
				dispose();
			}
		});
		btnback.setBackground(Color.BLACK);
		btnback.setForeground(Color.WHITE);
		btnback.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnback.setBounds(2, 2, 53, 31);
		contentPane.add(btnback);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.GRAY);
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(10, 346, 694, 2);
		contentPane.add(separator_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 241, 708, 56);
		scrollPane.setVisible(false);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton button = new JButton("CHECK DETAILS");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane.setVisible(true);
				String url="jdbc:oracle:thin:@localhost:1521:XE";
				String user="PROJECT";
				String pass="123456";
				
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection db=DriverManager.getConnection(url, user, pass);							
						String ROLL=roll_entered.getText();
						String query="SELECT * FROM STUDENT WHERE ROLL=?";
						PreparedStatement s=db.prepareStatement(query);
						s.setString(1, ROLL);
						ResultSet res=s.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(res));
					}catch(Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
			}
		});
		button.setFont(new Font("SansSerif", Font.BOLD, 12));
		button.setBounds(558, 139, 123, 42);
		contentPane.add(button);
		
		
	}
}
