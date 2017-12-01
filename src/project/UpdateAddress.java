package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.Toolkit;
import javax.swing.border.LineBorder;

@SuppressWarnings({ "unused", "serial" })
public class UpdateAddress extends JFrame {

	private JPanel contentPane;
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
					
					UpdateAddress frame = new UpdateAddress();
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
	public UpdateAddress() {
		setTitle("AMS");setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddProfessor.class.getResource("/Icons/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 200, 730, 530);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("\u2190");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdatePage.main(null);
				dispose();
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(47, 79, 79));
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 25));
		btnNewButton.setBounds(2, 2, 58, 34);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("ADDRESS INFORMATION UPDATE");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 35));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(94, 11, 527, 77);
		contentPane.add(lblNewLabel);
		
		JTextArea address_field = new JTextArea();
		address_field.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		address_field.setBackground(SystemColor.text);
		address_field.setForeground(SystemColor.activeCaptionText);
		address_field.setFont(new Font("Courier New", Font.BOLD, 20));
		address_field.setLineWrap(true);
		address_field.setBounds(400, 211, 267, 108);
		contentPane.add(address_field);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 93, 702, 2);
		contentPane.add(separator);
		
		JButton btnUpdateName = new JButton("UPDATE ADDRESS");
		btnUpdateName.addActionListener(new ActionListener() {				
				public void actionPerformed(ActionEvent e) {
					String url="jdbc:oracle:thin:@localhost:1521:XE";
					String user="PROJECT";
					String pass="123456";
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection db=DriverManager.getConnection(url, user, pass);
						String query="UPDATE STUDENT SET ADDRESS=? WHERE ROLL=?";
						PreparedStatement s=db.prepareStatement(query);	
						String address=address_field.getText();
						String roll=roll_field.getText();
						s.setString(1,address);
						s.setString(2, roll);
						int i=s.executeUpdate();
						if(i>0) 
						{	
							roll_field.setText("");
							address_field.setText("");
							JOptionPane.showMessageDialog(null, "ADDRESS successfully updated.");  
						}
						
						else 
						{
							roll_field.setText("");
							address_field.setText("");
							JOptionPane.showMessageDialog(null, "invalid input", null, JOptionPane.ERROR_MESSAGE);
						}
						
					} catch (ClassNotFoundException | SQLException e1) {
						JOptionPane.showMessageDialog(null, e1, "Exception",JOptionPane.ERROR_MESSAGE);
					}

			}
		});
		btnUpdateName.setForeground(new Color(255, 255, 255));
		btnUpdateName.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		btnUpdateName.setBackground(new Color(47, 79, 79));
		btnUpdateName.setBounds(235, 396, 243, 84);
		contentPane.add(btnUpdateName);
		
		JLabel lblNewLabel_1 = new JLabel("ENTER ROLL NUMBER OF THE STUDENT");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(45, 129, 310, 26);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblWhoseNameIs = new JLabel("WHOSE ADDRESS IS TO BE UPDATED:");
		lblWhoseNameIs.setForeground(new Color(0, 0, 0));
		lblWhoseNameIs.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
		lblWhoseNameIs.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWhoseNameIs.setBounds(45, 161, 310, 26);
		contentPane.add(lblWhoseNameIs);
		
		roll_field = new JTextField();
		roll_field.setBackground(SystemColor.text);
		roll_field.setHorizontalAlignment(SwingConstants.CENTER);
		roll_field.setForeground(SystemColor.activeCaptionText);
		roll_field.setFont(new Font("Courier New", Font.BOLD, 40));
		roll_field.setBounds(400, 129, 267, 58);
		contentPane.add(roll_field);
		roll_field.setColumns(10);
		
		JLabel lblEnterName = new JLabel("ENTER ADDRESS:");
		lblEnterName.setForeground(new Color(0, 0, 0));
		lblEnterName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEnterName.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
		lblEnterName.setBounds(88, 211, 267, 34);
		contentPane.add(lblEnterName);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(2, 370, 702, 2);
		contentPane.add(separator_1);
		
		
	}
}
