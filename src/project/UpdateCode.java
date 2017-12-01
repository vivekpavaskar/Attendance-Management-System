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
import java.awt.SystemColor;

@SuppressWarnings({ "unused", "serial" })
public class UpdateCode extends JFrame {

	private JPanel contentPane;
	private JTextField roll_field;
	private JTextField name_field;

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
					
					UpdateCode frame = new UpdateCode();
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
	public UpdateCode() {
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
				UpdatePageProf.main(null);
				dispose();
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 240));
		btnNewButton.setBackground(new Color(0, 0, 0));
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 30));
		btnNewButton.setBounds(6, 6, 63, 34);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("SUBJECT CODE UPDATE");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 35));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(91, 18, 527, 78);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(6, 94, 702, 2);
		contentPane.add(separator);
		
		JButton btnUpdateName = new JButton("UPDATE SUBJECT CODE");
		btnUpdateName.addActionListener(new ActionListener() {				
				public void actionPerformed(ActionEvent e) {
					String url="jdbc:oracle:thin:@localhost:1521:XE";
					String user="PROJECT";
					String pass="123456";
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection db=DriverManager.getConnection(url, user, pass);
						String query="UPDATE TEACHES SET SUBCODE=? WHERE PID=?";
						PreparedStatement s=db.prepareStatement(query);	
						String subcode=name_field.getText();
						String pid=roll_field.getText();
						s.setString(1, subcode);
						s.setString(2, pid);
						int i=s.executeUpdate();
						if(i>0) 
						{
							roll_field.setText("");
							name_field.setText("");
							JOptionPane.showMessageDialog(null, "Subject Code successfully updated.");  
						}
						else {
							roll_field.setText("");
							name_field.setText("");
							JOptionPane.showMessageDialog(null, "invalid input", null, JOptionPane.ERROR_MESSAGE);
						}
						
					} catch (ClassNotFoundException | SQLException e1) {
						JOptionPane.showMessageDialog(null, e1, "Exception",JOptionPane.ERROR_MESSAGE);
					}

			}
		});
		btnUpdateName.setForeground(new Color(255, 255, 240));
		btnUpdateName.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		btnUpdateName.setBackground(new Color(0, 0, 0));
		btnUpdateName.setBounds(192, 377, 330, 84);
		contentPane.add(btnUpdateName);
		
		JLabel lblNewLabel_1 = new JLabel("ENTER PID OF THE PROFESSOR:");
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.BOLD, 19));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(61, 159, 373, 46);
		contentPane.add(lblNewLabel_1);
		
		roll_field = new JTextField();
		roll_field.setBackground(SystemColor.menu);
		roll_field.setFont(new Font("Courier New", Font.BOLD, 25));
		roll_field.setBounds(495, 159, 157, 46);
		contentPane.add(roll_field);
		roll_field.setColumns(10);
		
		JLabel lblEnterName = new JLabel("CHANGE THE CODE OF THE SUBJECT HE/SHE TEACHES TO:");
		lblEnterName.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterName.setFont(new Font("Trebuchet MS", Font.BOLD, 17));
		lblEnterName.setBounds(6, 220, 481, 59);
		contentPane.add(lblEnterName);
		
		name_field = new JTextField();
		name_field.setBackground(SystemColor.menu);
		name_field.setFont(new Font("Courier New", Font.BOLD, 25));
		name_field.setColumns(10);
		name_field.setBounds(495, 226, 157, 46);
		contentPane.add(name_field);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(0, 0, 0));
		separator_1.setBounds(2, 350, 702, 2);
		contentPane.add(separator_1);
	}
}
