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

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JTable;
import javax.swing.JScrollPane;

@SuppressWarnings({ "unused", "serial" })
public class RemoveSubject extends JFrame {

	private JPanel contentPane;
	private JTextField subcode_entered;
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
					
					RemoveSubject frame = new RemoveSubject();
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
	public RemoveSubject() {
		setTitle("AMS");setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddProfessor.class.getResource("/Icons/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 200, 730, 530);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterTheRoll = new JLabel("ENTER THE SUBJECT CODE");
		lblEnterTheRoll.setForeground(SystemColor.windowText);
		lblEnterTheRoll.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterTheRoll.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblEnterTheRoll.setBounds(77, 101, 249, 42);
		contentPane.add(lblEnterTheRoll);
		
		subcode_entered = new JTextField();
		subcode_entered.setBackground(SystemColor.text);
		subcode_entered.setFont(new Font("Courier New", Font.BOLD, 20));
		subcode_entered.setHorizontalAlignment(SwingConstants.CENTER);
		subcode_entered.setBounds(329, 101, 156, 42);
		contentPane.add(subcode_entered);
		subcode_entered.setColumns(10);
		
		JLabel lblRemoveAProfessor = new JLabel("REMOVE A SUBJECT");
		lblRemoveAProfessor.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemoveAProfessor.setForeground(Color.BLACK);
		lblRemoveAProfessor.setFont(new Font("Trebuchet MS", Font.BOLD, 40));
		lblRemoveAProfessor.setBounds(0, 30, 714, 43);
		contentPane.add(lblRemoveAProfessor);
		
		JButton btnRemove = new JButton("REMOVE");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this Subject from the database? THIS ACTION IS NOT REVOKABLE.", "Confirmation", JOptionPane.YES_NO_OPTION))==JOptionPane.YES_OPTION)
				{
					String url="jdbc:oracle:thin:@localhost:1521:XE";
					String user="PROJECT";
					String pass="123456";
					
						try {
							Class.forName("oracle.jdbc.driver.OracleDriver");
							Connection db=DriverManager.getConnection(url, user, pass);							
							String subcode=subcode_entered.getText();
							
							String query2="delete ATTENDS where SUBCODE=?";
							PreparedStatement s2=db.prepareStatement(query2);
							s2.setString(1, subcode);
							int res2=s2.executeUpdate();
							
							String query3="delete TEACHES where SUBCODE=?";
							PreparedStatement s3=db.prepareStatement(query3);
							s3.setString(1, subcode);
							int res3=s3.executeUpdate();
							
							String query="delete SUBJECT where SUBCODE=?";
							PreparedStatement s=db.prepareStatement(query);
							s.setString(1, subcode);
							int res=s.executeUpdate();
							
							if(res>0 && res2>=0 && res3>=0)
							{	
								subcode_entered.setText("");
								JOptionPane.showMessageDialog(null, "subject with subject code:"+subcode+" has been deleted.");
							}
							else
							{
								subcode_entered.setText("");
								JOptionPane.showMessageDialog(null, "There is no data for the subject code entered in database.","error",JOptionPane.ERROR_MESSAGE);
							}
					} catch (ClassNotFoundException | SQLException e1) {
							JOptionPane.showMessageDialog(null, e1);
						}
						
						
						
				}
			}
		});
		btnRemove.setForeground(Color.WHITE);
		btnRemove.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		btnRemove.setBackground(new Color(85, 107, 47));
		btnRemove.setBounds(254, 405, 210, 56);
		contentPane.add(btnRemove);
		
		JButton button = new JButton("\u2190");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminAccessPage.main(null);
				dispose();
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("SansSerif", Font.BOLD, 20));
		button.setBackground(new Color(85, 107, 47));
		button.setBounds(2, 2, 58, 33);
		contentPane.add(button);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.WHITE);
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(10, 359, 694, 2);
		contentPane.add(separator_1);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.WHITE);
		separator.setBackground(Color.BLACK);
		separator.setBounds(10, 85, 694, 2);
		contentPane.add(separator);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 219, 668, 56);
		scrollPane.setVisible(false);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnCheckDetails = new JButton("CHECK DETAILS");
		btnCheckDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane.setVisible(true);
				String url="jdbc:oracle:thin:@localhost:1521:XE";
				String user="PROJECT";
				String pass="123456";
				
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection db=DriverManager.getConnection(url, user, pass);							
						String subcode=subcode_entered.getText();
						String query="SELECT * FROM SUBJECT WHERE SUBCODE=?";
						PreparedStatement s=db.prepareStatement(query);
						s.setString(1, subcode);
						ResultSet res=s.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(res));
					}catch(Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
			}
		});
		btnCheckDetails.setFont(new Font("SansSerif", Font.PLAIN, 12));
		btnCheckDetails.setBounds(489, 101, 127, 42);
		contentPane.add(btnCheckDetails);
		
		
	}
}
