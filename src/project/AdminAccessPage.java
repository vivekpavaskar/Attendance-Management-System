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
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JToggleButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

@SuppressWarnings({ "serial", "unused" })
public class AdminAccessPage extends JFrame {

	private JPanel contentPane;
	private JTextField auser;
	private JPasswordField apass;

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
					
					AdminAccessPage frame = new AdminAccessPage();
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
	public AdminAccessPage() {
		setTitle("AMS");setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddProfessor.class.getResource("/Icons/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 200, 730, 530);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//Panels here---------
		JPanel Admin_panel = new JPanel();
		Admin_panel.setLayout(null);
		Admin_panel.setBackground(SystemColor.menu);
		Admin_panel.setBounds(35, 160, 644, 318);
		Admin_panel.setVisible(false);
		contentPane.add(Admin_panel);
		
		JPanel prof_panel = new JPanel();
		prof_panel.setBackground(SystemColor.menu);
		prof_panel.setBorder(null);
		prof_panel.setBounds(10, 155, 692, 325);
		prof_panel.setVisible(false);
		contentPane.add(prof_panel);
		prof_panel.setLayout(null);
		
		JPanel Subject_Panel = new JPanel();
		Subject_Panel.setBackground(SystemColor.menu);
		Subject_Panel.setBounds(10, 156, 692, 324);
		contentPane.add(Subject_Panel);
		Subject_Panel.setVisible(false);
		Subject_Panel.setLayout(null);
		
		//end of panels---------------------------
		JLabel label = new JLabel("ADMIN USERNAME:");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("SansSerif", Font.BOLD, 20));
		label.setBounds(60, 47, 223, 39);
		Admin_panel.add(label);
		
		JLabel label_1 = new JLabel("ADMIN PASSWORD:");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("SansSerif", Font.BOLD, 20));
		label_1.setBounds(60, 133, 223, 39);
		Admin_panel.add(label_1);
		
		auser = new JTextField();
		auser.setFont(new Font("Courier New", Font.BOLD, 20));
		auser.setColumns(10);
		auser.setBounds(343, 47, 240, 39);
		Admin_panel.add(auser);
		
		apass = new JPasswordField();
		apass.setFont(new Font("Courier New", Font.BOLD, 20));
		apass.setBounds(343, 133, 240, 39);
		Admin_panel.add(apass);
		
		JButton button = new JButton("CREATE ADMIN");
		button.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection db= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "PROJECT","123456");
					String query="INSERT INTO ADMIN VALUES(?,?)";
					String user=auser.getText();
					String pass=apass.getText();
					PreparedStatement s=db.prepareStatement(query);
					s.setString(1, user);
					s.setString(2, pass);
					int i=s.executeUpdate();
					if(i>0)
					{	auser.setText(null);
						apass.setText(null);
						JOptionPane.showMessageDialog(null, "Admin Created.");
					}
					else
						JOptionPane.showMessageDialog(null, "Unable to Create Admin!","",JOptionPane.ERROR_MESSAGE);
					
					
				} catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null, e1,"Exception",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("SansSerif", Font.BOLD, 20));
		button.setBackground(new Color(178, 34, 34));
		button.setBounds(233, 219, 177, 51);
		Admin_panel.add(button);
		
		JLabel lblAdminAccess = new JLabel("ADMIN ACCESS");
		lblAdminAccess.setFont(new Font("Trebuchet MS", Font.BOLD, 50));
		lblAdminAccess.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdminAccess.setBounds(173, 11, 368, 50);
		contentPane.add(lblAdminAccess);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 0));
		separator.setBounds(10, 71, 694, 2);
		contentPane.add(separator);
		
		JPanel student_panel = new JPanel();
		student_panel.setBorder(null);
		student_panel.setBackground(SystemColor.menu);
		student_panel.setBounds(10, 155, 692, 325);
		student_panel.setVisible(false);
		contentPane.add(student_panel);
		student_panel.setLayout(null);
		
		JButton btnAddNewStudent = new JButton("ADD NEW STUDENT");
		btnAddNewStudent.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAddNewStudent.setForeground(new Color(255, 255, 255));
		btnAddNewStudent.setBackground(new Color(107, 142, 35));
		btnAddNewStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddStudent.main(null);
				dispose();
			}
		});
		btnAddNewStudent.setBounds(247, 34, 197, 65);
		student_panel.add(btnAddNewStudent);
		
		JButton btnRemoveStudent = new JButton("REMOVE STUDENT");
		btnRemoveStudent.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRemoveStudent.setForeground(new Color(255, 255, 255));
		btnRemoveStudent.setBackground(new Color(204, 0, 0));
		btnRemoveStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveStudent.main(null);
				dispose();
			}
		});
		btnRemoveStudent.setBounds(247, 131, 197, 65);
		student_panel.add(btnRemoveStudent);
		
		JButton btnUpdateStudent = new JButton("UPDATE STUDENT");
		btnUpdateStudent.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpdateStudent.setForeground(new Color(255, 255, 255));
		btnUpdateStudent.setBackground(new Color(204, 102, 0));
		btnUpdateStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdatePage.main(null);
				dispose();
			}
		});
		btnUpdateStudent.setBounds(247, 228, 197, 65);
		student_panel.add(btnUpdateStudent);
		
		JButton btnEditProfessors = new JButton("EDIT PROFESSORS");
		btnEditProfessors.setBackground(new Color(72, 61, 139));
		btnEditProfessors.setForeground(new Color(255, 255, 255));
		btnEditProfessors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prof_panel.setVisible(true);
				student_panel.setVisible(false);
				Subject_Panel.setVisible(false);
				Admin_panel.setVisible(false);
			}
		});
		btnEditProfessors.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEditProfessors.setBounds(9, 85, 167, 50);
		contentPane.add(btnEditProfessors);
		
		JButton btnEditStudents = new JButton("EDIT STUDENTS");
		btnEditStudents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				student_panel.setVisible(true);
				prof_panel.setVisible(false);
				Subject_Panel.setVisible(false);
				Admin_panel.setVisible(false);
			
						}
		});
		btnEditStudents.setBackground(new Color(72, 61, 139));
		btnEditStudents.setForeground(new Color(255, 255, 255));
		btnEditStudents.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEditStudents.setBounds(537, 85, 167, 50);
		contentPane.add(btnEditStudents);
		
		JButton btnLogOut = new JButton("LOG OUT");
		btnLogOut.setForeground(new Color(255, 255, 255));
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if((JOptionPane.showConfirmDialog(null, "Do you want to Log Out?", "logging out", JOptionPane.YES_NO_OPTION)) == JOptionPane.YES_OPTION)
				{ LoginPage.main(null);	dispose(); }
			}
		});
		btnLogOut.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogOut.setBackground(new Color(47, 79, 79));
		btnLogOut.setBounds(611, 0, 103, 31);
		contentPane.add(btnLogOut);
		
		
		JButton btnViewData = new JButton("VIEW DATA");
		btnViewData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminView.main(null);
				dispose();
			}
		});
		btnViewData.setForeground(Color.WHITE);
		btnViewData.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnViewData.setBackground(new Color(72, 61, 139));
		btnViewData.setBounds(185, 85, 167, 50);
		contentPane.add(btnViewData);
		
		JButton btnAddSubject = new JButton("EDIT SUBJECTS");
		btnAddSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				student_panel.setVisible(false);
				prof_panel.setVisible(false);
				Subject_Panel.setVisible(true);
				Admin_panel.setVisible(false);
			}
		});
		btnAddSubject.setForeground(Color.WHITE);
		btnAddSubject.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnAddSubject.setBackground(new Color(72, 61, 139));
		btnAddSubject.setBounds(361, 85, 167, 50);
		contentPane.add(btnAddSubject);
		
		JButton btnAddAdmin = new JButton("ADD ADMIN");
		btnAddAdmin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Admin_panel.setVisible(true);
				student_panel.setVisible(false);
				prof_panel.setVisible(false);
				Subject_Panel.setVisible(false);
			}
		});
		btnAddAdmin.setForeground(new Color(255, 255, 255));
		btnAddAdmin.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnAddAdmin.setBackground(new Color(47, 79, 79));
		btnAddAdmin.setBounds(0, 0, 103, 31);
		contentPane.add(btnAddAdmin);
		
		
		
		JButton btnAddNewSubject = new JButton("ADD NEW SUBJECT");
		btnAddNewSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddSubject.main(null);
				dispose();
			}
		});
		btnAddNewSubject.setForeground(Color.WHITE);
		btnAddNewSubject.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAddNewSubject.setBackground(new Color(107, 142, 35));
		btnAddNewSubject.setBounds(238, 65, 216, 65);
		Subject_Panel.add(btnAddNewSubject);
		
		JButton btnRemoveSubject = new JButton("REMOVE SUBJECT");
		btnRemoveSubject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveSubject.main(null);
				dispose();
			}
		});
		btnRemoveSubject.setForeground(Color.WHITE);
		btnRemoveSubject.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRemoveSubject.setBackground(new Color(204, 0, 0));
		btnRemoveSubject.setBounds(238, 195, 216, 65);
		Subject_Panel.add(btnRemoveSubject);
		
		
		
		JButton btnAddNewProfessor = new JButton("ADD NEW PROFESSOR");
		btnAddNewProfessor.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnAddNewProfessor.setForeground(new Color(255, 255, 255));
		btnAddNewProfessor.setBackground(new Color(107, 142, 35));
		btnAddNewProfessor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddProfessor.main(null);
				dispose();
			}
		});
		btnAddNewProfessor.setBounds(238, 36, 216, 65);
		prof_panel.add(btnAddNewProfessor);
		
		JButton btnRemoveProfessor = new JButton("REMOVE PROFESSOR");
		btnRemoveProfessor.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnRemoveProfessor.setForeground(new Color(255, 255, 255));
		btnRemoveProfessor.setBackground(new Color(204, 0, 0));
		btnRemoveProfessor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RemoveProf.main(null);
				dispose();
			}
		});
		btnRemoveProfessor.setBounds(238, 125, 216, 65);
		prof_panel.add(btnRemoveProfessor);
		
		JButton btnUpdateProfessor = new JButton("UPDATE PROFESSOR");
		btnUpdateProfessor.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnUpdateProfessor.setForeground(new Color(255, 255, 255));
		btnUpdateProfessor.setBackground(new Color(204, 102, 0));
		btnUpdateProfessor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpdatePageProf.main(null); dispose();
			}
		});
		btnUpdateProfessor.setBounds(238, 217, 216, 65);
		prof_panel.add(btnUpdateProfessor);
	}
}
