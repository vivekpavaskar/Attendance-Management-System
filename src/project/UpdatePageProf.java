package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

@SuppressWarnings({ "unused", "serial" })
public class UpdatePageProf extends JFrame {

	private JPanel contentPane;

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
					
					UpdatePageProf frame = new UpdatePageProf();
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public UpdatePageProf() {
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
				AdminAccessPage.main(null);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 25));
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(2, 2, 65, 37);
		contentPane.add(btnNewButton);
		
		JLabel label = new JLabel("UPDATE PROFESSOR INFORMATION");
		label.setBackground(new Color(0, 0, 0));
		label.setForeground(new Color(0, 0, 0));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Trebuchet MS", Font.BOLD, 40));
		label.setBounds(30, 49, 654, 50);
		contentPane.add(label);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String SelectedVar=(String) comboBox.getSelectedItem();
				switch(SelectedVar)
				{							  
					case "UPDATE PROFESSOR NAME": UpdateProfName.main(null);dispose();break; 
					case "UPDATE USERNAME":UpdateUsername.main(null); dispose();break;
					case "UPDATE PASSWORD":UpdatePassword.main(null); dispose();break;								  
					case "UPDATE PHONE":UpdatePhoneProf.main(null); dispose();break;		
					case "UPDATE SUBJECT CODE":UpdateCode.main(null);
					dispose();
					break;
				}
			}
		});
		comboBox.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		comboBox.setForeground(new Color(0, 0, 0));
		comboBox.setBackground(new Color(255, 255, 255));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"UPDATE PROFESSOR NAME", "UPDATE USERNAME", "UPDATE PASSWORD", "UPDATE PHONE", "UPDATE SUBJECT CODE"}));
		comboBox.setBounds(194, 290, 326, 50);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("SELECT THE FIELD YOU WANT TO UPDATE ");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBackground(new Color(240, 248, 255));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(64, 242, 591, 37);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 110, 716, 2);
		contentPane.add(separator);
	}
}
