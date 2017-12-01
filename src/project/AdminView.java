package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import java.awt.Rectangle;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings({ "unused", "serial" })
public class AdminView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;

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
					
					AdminView frame = new AdminView();
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
	public AdminView() {
		setTitle("AMS");setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddProfessor.class.getResource("/Icons/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 200, 730, 530);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("VIEW DATABASE ENTRIES");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(99, 18, 515, 45);
		contentPane.add(lblNewLabel);

		JSeparator separator = new JSeparator();
		separator.setBounds(6, 64, 702, 2);
		contentPane.add(separator);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(7, 70, 700, 415);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(230, 230, 250));
		tabbedPane.addTab("PROFESSOR", null, panel, null);
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 700, 385);
		scrollPane.setVisible(false);
		panel.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);


		JButton btnGetData = new JButton("GET DATA");
		btnGetData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnGetData.setForeground(Color.WHITE);
				btnGetData.setFont(new Font("Century Gothic", Font.BOLD, 20));
				btnGetData.setBackground(new Color(106, 90, 205));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnGetData.setForeground(new Color(255, 255, 255));
				btnGetData.setBackground(new Color(65, 105, 225));
				btnGetData.setFont(new Font("Century Gothic", Font.BOLD, 20));
			}
		});
		
		btnGetData.setVisible(false);
		scrollPane.setVisible(true);
		String url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="project";
		String pass="123456";
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection db=DriverManager.getConnection(url, user, pass);
			String query="select * from PROF";
			PreparedStatement statement=db.prepareStatement(query);					
			ResultSet set=statement.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(set));
			int height=table.getRowCount() * 16 + 28;
			if(height<385)
			{	
				int LessBy=385-height;
				LessBy=LessBy/2;
				scrollPane.setBounds(0,LessBy, 700, height);
			}
			db.close();

		} catch (ClassNotFoundException | SQLException e1)
		{
			JOptionPane.showMessageDialog(null, e1, "Exception", JOptionPane.ERROR_MESSAGE);
		}

		
		btnGetData.setBounds(287, 147, 126, 90);
		btnGetData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnGetData.setForeground(new Color(255, 255, 255));
		btnGetData.setBackground(new Color(65, 105, 225));
		btnGetData.setFont(new Font("Century Gothic", Font.BOLD, 20));
		panel.add(btnGetData);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(230, 230, 250));
		tabbedPane.addTab("STUDENT", null, panel_1, null);
		panel_1.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 700, 385);
		scrollPane_1.setVisible(false);
		panel_1.add(scrollPane_1);

		table_1 = new JTable();
		scrollPane_1.setViewportView(table_1);

		JButton button = new JButton("GET DATA");
		button.setBounds(287, 147, 126, 90);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		button.setVisible(false);
		scrollPane_1.setVisible(true);
		url="jdbc:oracle:thin:@localhost:1521:XE";
		user="project";
		pass="123456";
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection db=DriverManager.getConnection(url, user, pass);
			String query="select * from STUDENT_VIEW";
			PreparedStatement statement=db.prepareStatement(query);					
			ResultSet set=statement.executeQuery();
			table_1.setModel(DbUtils.resultSetToTableModel(set));
			int height=table_1.getRowCount() * 16 + 28;
			if(height<385)
			{	
				int LessBy=385-height;
				LessBy=LessBy/2;
				scrollPane_1.setBounds(0,LessBy, 700, height);

			}

		} catch (ClassNotFoundException | SQLException e1)
		{
			JOptionPane.showMessageDialog(null, e1, "Exception", JOptionPane.ERROR_MESSAGE);
		}

		
		
		panel_1.setLayout(null);
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Century Gothic", Font.BOLD, 20));
		button.setBackground(new Color(106, 90, 205));
		panel_1.add(button);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(230, 230, 250));
		tabbedPane.addTab("SUBJECT", null, panel_2, null);
		panel_2.setLayout(null);
		panel_2.setLayout(null);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 0, 700, 385);
		scrollPane_2.setVisible(false);
		panel_2.add(scrollPane_2);

		JTable table_2 = new JTable();
		scrollPane_2.setViewportView(table_2);

		JButton button_1 = new JButton("GET DATA");
		
		button_1.setVisible(false);
		scrollPane_2.setVisible(true);
		 url="jdbc:oracle:thin:@localhost:1521:XE";
		 user="project";
		 pass="123456";
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection db=DriverManager.getConnection(url, user, pass);
			String query="select * from SUBJECT";
			PreparedStatement statement=db.prepareStatement(query);					
			ResultSet set=statement.executeQuery();
			table_2.setModel(DbUtils.resultSetToTableModel(set));
			int height=table_2.getRowCount() * 16 + 28;
			if(height<385)
			{	
				int LessBy=385-height;
				LessBy=LessBy/2;
				scrollPane_2.setBounds(0,LessBy, 700, height);

			}

		} catch (ClassNotFoundException | SQLException e1)
		{
			JOptionPane.showMessageDialog(null, e1, "Exception", JOptionPane.ERROR_MESSAGE);
		}
		
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Century Gothic", Font.BOLD, 20));
		button_1.setBackground(new Color(47, 79, 79));
		button_1.setBounds(287, 147, 126, 90);
		panel_2.add(button_1);


		JButton button_2 = new JButton("\u2190");
		button_2.setForeground(new Color(255, 255, 255));
		button_2.setBackground(new Color(70, 130, 180));
		button_2.setFont(new Font("SansSerif", Font.BOLD, 20));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminAccessPage.main(null);
				dispose();
			}
		});
		button_2.setBounds(0, 0, 52, 30);
		contentPane.add(button_2);


	}
}
