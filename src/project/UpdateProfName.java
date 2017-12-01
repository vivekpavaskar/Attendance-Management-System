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
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.SystemColor;
import java.awt.Toolkit;

@SuppressWarnings({ "unused", "serial" })
public class UpdateProfName extends JFrame {

	private JPanel contentPane;
	private JTextField pid_field;
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
					
					UpdateProfName frame = new UpdateProfName();
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
	public UpdateProfName() {
		setTitle("AMS");setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddProfessor.class.getResource("/Icons/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 200, 730, 530);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
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
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(72, 61, 139));
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 21));
		btnNewButton.setBounds(2, 2, 52, 34);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("NAME INFORMATION UPDATE");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.BOLD, 35));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(93, 36, 527, 59);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(6, 93, 702, 2);
		contentPane.add(separator);
		
		JButton btnUpdateName = new JButton("UPDATE NAME");
		btnUpdateName.addActionListener(new ActionListener() {				
				public void actionPerformed(ActionEvent e) {
					String url="jdbc:oracle:thin:@localhost:1521:XE";
					String user="PROJECT";
					String pass="123456";
					try {
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection db=DriverManager.getConnection(url, user, pass);
						String query="UPDATE PROF SET PNAME=? WHERE PID=?";
						PreparedStatement s=db.prepareStatement(query);	
						String name=name_field.getText();
						String pid=pid_field.getText();
						s.setString(1, name);
						s.setString(2, pid);
						int i=s.executeUpdate();
						if(i>0)
						{	
							pid_field.setText("");
							name_field.setText("");
							JOptionPane.showMessageDialog(null, "Name successfully updated.");  }
						else {
							pid_field.setText("");
							name_field.setText("");
							JOptionPane.showMessageDialog(null, "invalid input", null, JOptionPane.ERROR_MESSAGE);
						}
						
					} catch (ClassNotFoundException | SQLException e1) {
						JOptionPane.showMessageDialog(null, e1, "Exception",JOptionPane.ERROR_MESSAGE);
					}

			}
		});
		btnUpdateName.setForeground(Color.WHITE);
		btnUpdateName.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
		btnUpdateName.setBackground(new Color(72, 61, 139));
		btnUpdateName.setBounds(253, 396, 220, 84);
		contentPane.add(btnUpdateName);
		
		JLabel lblNewLabel_1 = new JLabel("ENTER PID OF THE PROFESSOR");
		lblNewLabel_1.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(6, 148, 345, 25);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblWhoseNameIs = new JLabel("WHOSE NAME IS TO BE UPDATED:");
		lblWhoseNameIs.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblWhoseNameIs.setHorizontalAlignment(SwingConstants.CENTER);
		lblWhoseNameIs.setBounds(6, 172, 345, 25);
		contentPane.add(lblWhoseNameIs);
		
		pid_field = new JTextField();
		pid_field.setBackground(SystemColor.text);
		pid_field.setFont(new Font("Courier New", Font.BOLD, 25));
		pid_field.setBounds(347, 148, 326, 49);
		contentPane.add(pid_field);
		pid_field.setColumns(10);
		
		JLabel lblEnterName = new JLabel("ENTER NAME:");
		lblEnterName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEnterName.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		lblEnterName.setBounds(6, 261, 336, 25);
		contentPane.add(lblEnterName);
		
		name_field = new JTextField();
		name_field.setBackground(SystemColor.text);
		name_field.setFont(new Font("Courier New", Font.BOLD, 20));
		name_field.setColumns(10);
		name_field.setBounds(347, 257, 326, 49);
		contentPane.add(name_field);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(2, 370, 702, 2);
		contentPane.add(separator_1);
	}
}
