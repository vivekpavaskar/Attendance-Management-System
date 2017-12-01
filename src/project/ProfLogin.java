package project;

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
import java.awt.SystemColor;


@SuppressWarnings({ "serial" })
public class ProfLogin extends JFrame {

        
	private JPanel contentPane;
	private JPasswordField pass_field;
	private JTextField user_field;

	/**
	 * Launch the application.
     * @param args
	 */
        public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
                        @Override
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
					
					ProfLogin frame = new ProfLogin();
					frame.setVisible(true);
				} catch (Exception e) {
                                    JOptionPane.showMessageDialog(null, e, "error!", JOptionPane.ERROR_MESSAGE);
                                
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ProfLogin() {
		setTitle("AMS");setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddProfessor.class.getResource("/Icons/icon.png")));
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(600, 200, 730, 530);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProfessorLogin = new JLabel("PROFESSOR LOGIN");
		lblProfessorLogin.setForeground(Color.BLACK);
		lblProfessorLogin.setFont(new Font("Trebuchet MS", Font.BOLD, 45));
		lblProfessorLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblProfessorLogin.setBounds(10, 27, 714, 47);
		contentPane.add(lblProfessorLogin);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setBounds(10, 75, 694, 2);
		contentPane.add(separator);
		
		JLabel lblEnterUsername = new JLabel("ENTER USERNAME:");
		lblEnterUsername.setForeground(Color.BLACK);
		lblEnterUsername.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblEnterUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterUsername.setBounds(60, 157, 254, 33);
		contentPane.add(lblEnterUsername);
		
		JLabel lblEnterPassword = new JLabel("ENTER PASSWORD:");
		lblEnterPassword.setForeground(Color.BLACK);
		lblEnterPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterPassword.setFont(new Font("SansSerif", Font.BOLD, 25));
		lblEnterPassword.setBounds(60, 294, 254, 31);
		contentPane.add(lblEnterPassword);
		
		pass_field = new JPasswordField();
		pass_field.setBackground(SystemColor.text);
		pass_field.setFont(new Font("Courier New", Font.BOLD, 20));
		pass_field.setBounds(374, 286, 279, 47);
		contentPane.add(pass_field);
		
		JButton btnLogIn = new JButton("LOG IN");
		btnLogIn.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				String url="jdbc:oracle:thin:@localhost:1521:XE";
				String user="project";
				String pass="123456";
				try 
				{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection db=DriverManager.getConnection(url, user, pass);
					String name=user_field.getText();
					String passwd=pass_field.getText();
					String query="select * from prof where USERNAME=? AND PASSWORD=?";
					PreparedStatement statement=db.prepareStatement(query);
					statement.setString(1,name);
					statement.setString(2,passwd);
					
					ResultSet set=statement.executeQuery();
					if(set.next())
					{	
						String pass_data = (String) set.getString("PID");
						TakeAttendance.pass_data=pass_data;
						String name_pass= (String) set.getString("PNAME");
						ProfAccessPage.profname=name_pass;
						StudentAnalysis.profname=name_pass;
						TakeAttendance.profname=name_pass;
						JOptionPane.showMessageDialog(null, "login successful");
						ProfAccessPage.main(null);
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
		btnLogIn.setFont(new Font("Trebuchet MS", Font.BOLD, 25));
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
		btnBack.setBounds(0, 0, 57, 33);
		contentPane.add(btnBack);
		
		user_field = new JTextField();
		user_field.setBackground(SystemColor.text);
		user_field.setFont(new Font("Courier New", Font.BOLD, 20));
		user_field.setColumns(10);
		user_field.setBounds(374, 150, 279, 47);
		contentPane.add(user_field);
		
		
	}
}
