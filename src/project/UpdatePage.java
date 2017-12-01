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
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.SystemColor;
import java.awt.Toolkit;

@SuppressWarnings({ "unused", "serial" })
public class UpdatePage extends JFrame {

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
					
					UpdatePage frame = new UpdatePage();
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
	public UpdatePage() {
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
		btnNewButton.setBackground(new Color(47, 79, 79));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(0, 0, 58, 37);
		contentPane.add(btnNewButton);
		
		JLabel label = new JLabel("UPDATE STUDENT INFORMATION");
		label.setForeground(new Color(0, 0, 0));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Trebuchet MS", Font.BOLD, 40));
		label.setBounds(47, 49, 619, 50);
		contentPane.add(label);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String SelectedVar=(String) comboBox.getSelectedItem();
				switch(SelectedVar)
				{
				case "UPDATE USN":	 UsnUpdate.main(null); 
									 dispose();
									 break;
									  
				case "UPDATE NAME": UpdateName.main(null);
									dispose();
									break;
										
				case "UPDATE ADDRESS":UpdateAddress.main(null);
									dispose();
									break;
									  
				case "UPDATE PHONE":UpdatePhone.main(null);
									dispose();
									break;									
				case "UPDATE PARENT'S PHONE": UpdateParentPh.main(null);
									dispose();
									break;
											 
				}
			}
		});
		comboBox.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
		comboBox.setForeground(new Color(0, 0, 0));
		comboBox.setBackground(new Color(230, 230, 250));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"UPDATE USN", "UPDATE NAME", "UPDATE ADDRESS", "UPDATE PHONE", "UPDATE PARENT'S PHONE"}));
		comboBox.setBounds(186, 288, 341, 50);
		contentPane.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("SELECT THE FIELD YOU WANT TO UPDATE ");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(29, 197, 666, 37);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 110, 716, 2);
		contentPane.add(separator);
	}
}
