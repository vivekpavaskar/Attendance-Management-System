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
public class RemoveProf extends JFrame {

	private JPanel contentPane;
	private JTextField pid_entered;
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
					
					RemoveProf frame = new RemoveProf();
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
	public RemoveProf() {
		setTitle("AMS");setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddProfessor.class.getResource("/Icons/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 200, 730, 530);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEnterTheRoll = new JLabel("ENTER THE PROFESSOR ID");
		lblEnterTheRoll.setForeground(SystemColor.windowText);
		lblEnterTheRoll.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterTheRoll.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblEnterTheRoll.setBounds(111, 113, 249, 43);
		contentPane.add(lblEnterTheRoll);
		
		pid_entered = new JTextField();
		pid_entered.setBackground(SystemColor.menu);
		pid_entered.setFont(new Font("Courier New", Font.BOLD, 40));
		pid_entered.setHorizontalAlignment(SwingConstants.CENTER);
		pid_entered.setBounds(372, 113, 86, 43);
		contentPane.add(pid_entered);
		pid_entered.setColumns(10);
		
		JLabel lblRemoveAProfessor = new JLabel("REMOVE A PROFESSOR");
		lblRemoveAProfessor.setHorizontalAlignment(SwingConstants.CENTER);
		lblRemoveAProfessor.setForeground(Color.BLACK);
		lblRemoveAProfessor.setFont(new Font("Trebuchet MS", Font.BOLD, 40));
		lblRemoveAProfessor.setBounds(0, 30, 714, 43);
		contentPane.add(lblRemoveAProfessor);
		
		JButton btnRemove = new JButton("REMOVE");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this Professor's records from the database?\nTHIS ACTION IS NOT REVOKABLE.", "Confirmation", JOptionPane.YES_NO_OPTION))==JOptionPane.YES_OPTION)
				{
					String url="jdbc:oracle:thin:@localhost:1521:XE";
					String user="PROJECT";
					String pass="123456";
					
						try {
							Class.forName("oracle.jdbc.driver.OracleDriver");
							Connection db=DriverManager.getConnection(url, user, pass);							
							String pid=pid_entered.getText();
							String query="delete PROF where PID=?";
							String query2="delete TEACHES where PID=?";
							PreparedStatement s2=db.prepareStatement(query2);
							PreparedStatement s=db.prepareStatement(query);
							s.setString(1, pid);
							s2.setString(1, pid);
							int res2=s2.executeUpdate();
							int res=s.executeUpdate();
							if(res>0 && res2>0)
							{
								pid_entered.setText("");
								JOptionPane.showMessageDialog(null, "Data of the professor with PID:"+pid+" has been deleted.");
							}
							else
							{
								pid_entered.setText("");
								JOptionPane.showMessageDialog(null, "There is no data for PID number entered in database.","error",JOptionPane.ERROR_MESSAGE);
							}
					} catch (ClassNotFoundException | SQLException e1) {
							JOptionPane.showMessageDialog(null, e1);
						}
						
						
						
				}
			}
		});
		btnRemove.setForeground(Color.WHITE);
		btnRemove.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		btnRemove.setBackground(new Color(0, 128, 128));
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
		button.setBackground(new Color(0, 128, 128));
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
		scrollPane.setBounds(6, 220, 698, 50);
		scrollPane.setVisible(false);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton button_1 = new JButton("CHECK DETAILS");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				scrollPane.setVisible(true);
				String url="jdbc:oracle:thin:@localhost:1521:XE";
				String user="PROJECT";
				String pass="123456";
				
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection db=DriverManager.getConnection(url, user, pass);							
						String pid=pid_entered.getText();
						String query="SELECT * FROM PROF WHERE PID=?";
						PreparedStatement s=db.prepareStatement(query);
						s.setString(1, pid);
						ResultSet res=s.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(res));
					}catch(Exception e1) {
						JOptionPane.showMessageDialog(null, e1);
					}
			}
		});
		button_1.setFont(new Font("SansSerif", Font.BOLD, 12));
		button_1.setBounds(470, 113, 123, 43);
		contentPane.add(button_1);
		
		
	}
}
