package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

@SuppressWarnings({ "unused", "serial" })
public class UsnUpdate extends JFrame {

	private JPanel contentPane;
	private JTextField usn_field;
	private JTextField roll_field;

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
					
					UsnUpdate frame = new UsnUpdate();
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
	public UsnUpdate() {
		setTitle("AMS");setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddProfessor.class.getResource("/Icons/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 200, 730, 530);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnUpdateUsn = new JButton("UPDATE USN");
		btnUpdateUsn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url="jdbc:oracle:thin:@localhost:1521:XE";
				String user="PROJECT";
				String pass="123456";
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection db=DriverManager.getConnection(url, user, pass);
					String query="UPDATE STUDENT SET USN=? WHERE ROLL=?";
					PreparedStatement s=db.prepareStatement(query);	
					String usn=usn_field.getText();
					String roll=roll_field.getText();
					s.setString(1, usn);
					s.setString(2, roll);
					int i=s.executeUpdate();
					if(i>0) {	
						usn_field.setText("");
						roll_field.setText("");
						JOptionPane.showMessageDialog(null, "USN successfully updated.");  }
					else {
						JOptionPane.showMessageDialog(null, "invalid input", null, JOptionPane.ERROR_MESSAGE);
					}
					
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null, e1, "Exception",JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnUpdateUsn.setForeground(new Color(255, 255, 255));
		btnUpdateUsn.setBackground(new Color(65, 105, 225));
		btnUpdateUsn.setFont(new Font("Arial", Font.BOLD, 25));
		btnUpdateUsn.setBounds(247, 396, 220, 84);
		contentPane.add(btnUpdateUsn);
		
		JLabel lblEnterNewUsn = new JLabel("ENTER NEW USN:");
		lblEnterNewUsn.setForeground(new Color(0, 0, 0));
		lblEnterNewUsn.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblEnterNewUsn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEnterNewUsn.setBounds(5, 282, 416, 41);
		contentPane.add(lblEnterNewUsn);
		
		usn_field = new JTextField();
		usn_field.setBackground(Color.WHITE);
		usn_field.setHorizontalAlignment(SwingConstants.CENTER);
		usn_field.setFont(new Font("Courier New", Font.BOLD, 25));
		usn_field.setBounds(454, 271, 215, 54);
		contentPane.add(usn_field);
		usn_field.setColumns(10);
		
		JLabel lblUsnUpdate = new JLabel("USN INFORMATION UPDATE");
		lblUsnUpdate.setForeground(new Color(0, 0, 0));
		lblUsnUpdate.setFont(new Font("Trebuchet MS", Font.PLAIN, 40));
		lblUsnUpdate.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsnUpdate.setBounds(93, 29, 528, 54);
		contentPane.add(lblUsnUpdate);
		
		roll_field = new JTextField();
		roll_field.setBackground(Color.WHITE);
		roll_field.setHorizontalAlignment(SwingConstants.CENTER);
		roll_field.setFont(new Font("Courier New", Font.BOLD, 25));
		roll_field.setColumns(10);
		roll_field.setBounds(454, 152, 215, 54);
		contentPane.add(roll_field);
		
		JLabel label = new JLabel("ENTER THE ROLL NUMBER OF THE STUDENT");
		label.setForeground(new Color(0, 0, 0));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		label.setBounds(5, 152, 437, 21);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("WHOSE USN IS TO BE UPDATED:");
		label_1.setForeground(new Color(0, 0, 0));
		label_1.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		label_1.setBounds(127, 184, 315, 21);
		contentPane.add(label_1);
		
		JButton btnback = new JButton("\u2190");
		btnback.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdatePage.main(null);
				dispose();
			}
		});
		btnback.setForeground(new Color(255, 255, 255));
		btnback.setBackground(new Color(65, 105, 225));
		btnback.setFont(new Font("SansSerif", Font.BOLD, 25));
		btnback.setBounds(2, 2, 59, 34);
		contentPane.add(btnback);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 93, 649, 15);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(20, 370, 649, 15);
		contentPane.add(separator_1);
	}
}
