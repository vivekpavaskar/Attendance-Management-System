package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.SystemColor;

@SuppressWarnings({ "serial", "unused" })
public class AdminLogin extends JFrame {

	private JPanel contentPane;
	private JTextField user_field;
	private JPasswordField pass_field;

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
					
					AdminLogin frame = new AdminLogin();
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
	public AdminLogin() {
		setTitle("AMS");setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddProfessor.class.getResource("/Icons/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 200, 730, 530);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(65, 105, 225));
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProfessorLogin = new JLabel("ADMIN LOGIN");
		lblProfessorLogin.setForeground(Color.BLACK);
		lblProfessorLogin.setFont(new Font("Trebuchet MS", Font.BOLD, 45));
		lblProfessorLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblProfessorLogin.setBounds(160, 27, 393, 47);
		contentPane.add(lblProfessorLogin);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setBounds(10, 75, 694, 2);
		contentPane.add(separator);
		
		JLabel lblEnterUsername = new JLabel("ENTER USERNAME:");
		lblEnterUsername.setForeground(Color.BLACK);
		lblEnterUsername.setFont(new Font("SansSerif", Font.BOLD, 24));
		lblEnterUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterUsername.setBounds(60, 165, 254, 33);
		contentPane.add(lblEnterUsername);
		
		JLabel lblEnterPassword = new JLabel("ENTER PASSWORD:");
		lblEnterPassword.setForeground(Color.BLACK);
		lblEnterPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterPassword.setFont(new Font("SansSerif", Font.BOLD, 24));
		lblEnterPassword.setBounds(60, 300, 254, 31);
		contentPane.add(lblEnterPassword);
		
		JButton btnLogIn = new JButton("LOG IN");
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String url="jdbc:oracle:thin:@localhost:1521:XE";
				String user="PROJECT";
				String pass="123456";
				try 
				{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection db=DriverManager.getConnection(url, user, pass);
					String name=user_field.getText();
					@SuppressWarnings("deprecation")
					String passwd=pass_field.getText();
					String query="select * from ADMIN where USERNAME=? and PASSWORD=?";
					PreparedStatement statement=db.prepareStatement(query);
					statement.setString(1,name);
					statement.setString(2,passwd);
					
					ResultSet set=statement.executeQuery();
					if(set.next())
					{
						JOptionPane.showMessageDialog(null, "login successful");
						AdminAccessPage.main(null);
						dispose();
					}
					else 
					{
						JOptionPane.showMessageDialog(null, "Invalid Username or Password!","Authentication Failed",JOptionPane.ERROR_MESSAGE);
						
					}

				} catch (ClassNotFoundException | SQLException e1)
				{
					e1.printStackTrace();
				}
				
			}
		});
		btnLogIn.setBackground(new Color(65, 105, 225));
		btnLogIn.setForeground(Color.WHITE);
		btnLogIn.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		btnLogIn.setBounds(280, 425, 153, 55);
		contentPane.add(btnLogIn);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(10, 412, 694, 2);
		contentPane.add(separator_1);
		
		JButton btnBack = new JButton("\u2190");
		btnBack.addActionListener(new ActionListener() {
			@SuppressWarnings("static-access")
			public void actionPerformed(ActionEvent e) {
				LoginPage p = new LoginPage();
				p.main(null);
				dispose();
			}
		});
		btnBack.setBackground(new Color(70, 130, 180));
		btnBack.setForeground(Color.WHITE);
		btnBack.setFont(new Font("SansSerif", Font.BOLD, 20));
		btnBack.setBounds(2, 2, 52, 33);
		contentPane.add(btnBack);
		
		user_field = new JTextField();
		user_field.setFont(new Font("Courier New", Font.BOLD, 20));
		user_field.setColumns(10);
		user_field.setBounds(374, 158, 279, 47);
		contentPane.add(user_field);
		
		pass_field = new JPasswordField();
		pass_field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					String url="jdbc:oracle:thin:@localhost:1521:XE";
					String user="PROJECT";
					String pass="123456";
					try 
					{
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection db=DriverManager.getConnection(url, user, pass);
						String name=user_field.getText();
						@SuppressWarnings("deprecation")
						String passwd=pass_field.getText();
						String query="select * from ADMIN where USERNAME=? and PASSWORD=?";
						PreparedStatement statement=db.prepareStatement(query);
						statement.setString(1,name);
						statement.setString(2,passwd);
						
						ResultSet set=statement.executeQuery();
						if(set.next())
						{
							JOptionPane.showMessageDialog(null, "login successful");
							AdminAccessPage.main(null);
							dispose();
						}
						else 
						{
							JOptionPane.showMessageDialog(null, "Invalid Username or Password!","Authentication Failed",JOptionPane.ERROR_MESSAGE);
							
						}

					} catch (ClassNotFoundException | SQLException e1)
					{
						e1.printStackTrace();
					}
				}
			}
		});
		pass_field.setFont(new Font("Courier New", Font.BOLD, 20));
		pass_field.setBounds(374, 292, 279, 47);
		contentPane.add(pass_field);
		
		
	}
}
