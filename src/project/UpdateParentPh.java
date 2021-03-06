package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
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
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import java.awt.SystemColor;

@SuppressWarnings({ "unused", "serial" })
public class UpdateParentPh extends JFrame {

	private JPanel contentPane;
	private JTextField roll_field;
	private JTextField phone_field;

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
					
					UpdateParentPh frame = new UpdateParentPh();
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
	public UpdateParentPh() {
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
		btnNewButton.setBackground(SystemColor.desktop);
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 25));
		btnNewButton.setBounds(2, 2, 57, 34);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("PARENT PHONE NUMBER INFORMATION UPDATE");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(9, 42, 696, 59);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 93, 702, 2);
		contentPane.add(separator);
		
		JButton btnUpdateName = new JButton("UPDATE PHONE");
		btnUpdateName.addActionListener(new ActionListener() {				
				public void actionPerformed(ActionEvent e) {
					String url="jdbc:oracle:thin:@localhost:1521:XE";
					String user="PROJECT";
					String pass="123456";
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection db=DriverManager.getConnection(url, user, pass);
						String query="UPDATE STUDENT SET PARENT_PHONE=? WHERE ROLL=?";
						PreparedStatement s=db.prepareStatement(query);	
						String phone=phone_field.getText();
						String roll=roll_field.getText();
						s.setString(1,phone);
						s.setString(2, roll);
						int i=s.executeUpdate();
						if(i>0) {	JOptionPane.showMessageDialog(null, "Phone number successfully updated.");  }
						else {
							JOptionPane.showMessageDialog(null, "invalid input", null, JOptionPane.ERROR_MESSAGE);
						}
						
					} catch (ClassNotFoundException | SQLException e1) {
						JOptionPane.showMessageDialog(null, e1, "Exception",JOptionPane.ERROR_MESSAGE);
					}

			}
		});
		btnUpdateName.setForeground(new Color(255, 255, 255));
		btnUpdateName.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		btnUpdateName.setBackground(SystemColor.desktop);
		btnUpdateName.setBounds(235, 396, 243, 84);
		contentPane.add(btnUpdateName);
		
		JLabel lblNewLabel_1 = new JLabel("ENTER ROLL NUMBER OF THE STUDENT");
		lblNewLabel_1.setForeground(new Color(0, 0, 0));
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(117, 144, 400, 24);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblWhoseNameIs = new JLabel("WHOSE PARENT'S PHONE NUMBER IS TO BE UPDATED:");
		lblWhoseNameIs.setForeground(new Color(0, 0, 0));
		lblWhoseNameIs.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblWhoseNameIs.setHorizontalAlignment(SwingConstants.CENTER);
		lblWhoseNameIs.setBounds(20, 174, 513, 14);
		contentPane.add(lblWhoseNameIs);
		
		roll_field = new JTextField();
		roll_field.setHorizontalAlignment(SwingConstants.CENTER);
		roll_field.setForeground(new Color(0, 0, 0));
		roll_field.setFont(new Font("Courier New", Font.BOLD, 20));
		roll_field.setBounds(553, 144, 139, 48);
		contentPane.add(roll_field);
		roll_field.setColumns(10);
		
		JLabel lblEnterName = new JLabel("ENTER NEW PHONE NUMBER:");
		lblEnterName.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterName.setForeground(new Color(0, 0, 0));
		lblEnterName.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblEnterName.setBounds(31, 269, 371, 34);
		contentPane.add(lblEnterName);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(2, 370, 702, 2);
		contentPane.add(separator_1);
		
		phone_field = new JTextField();
		phone_field.setHorizontalAlignment(SwingConstants.CENTER);
		phone_field.setForeground(Color.BLACK);
		phone_field.setFont(new Font("Courier New", Font.BOLD, 20));
		phone_field.setColumns(10);
		phone_field.setBounds(381, 263, 300, 48);
		contentPane.add(phone_field);
		
		
	}
}
