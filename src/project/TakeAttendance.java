package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import java.awt.SystemColor;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;

@SuppressWarnings({ "unused", "serial" })
public class TakeAttendance extends JFrame 
{	public static String profname;
	private JPanel contentPane;
	public static String pass_data;
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
					
					TakeAttendance frame = new TakeAttendance(pass_data);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public TakeAttendance(String pass_data)
	{
		setTitle("AMS");
		//setLocationRelativeTo(null);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AddProfessor.class.getResource("/Icons/icon.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 100, 730, 600);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane jsp = new JScrollPane();
		jsp.setBounds(20,150,677,351);
		contentPane.add(jsp);
		
		final JTable table = new JTable();
		jsp.setViewportView(table);
	    //THE MODEL OF OUR TABLE
	    DefaultTableModel model=new DefaultTableModel()
	    {
		      public Class<?> getColumnClass(int column)
		      {
		        switch(column)
		        {
		        case 0:  return String.class;
		        case 1:  return String.class;
		        case 2:  return String.class;
		        case 3:  return String.class;
		        case 4:  return String.class;
		        case 5:  return Boolean.class;        
		        default: return String.class;
		        }
		      }
	    };
	    
	    JLabel subname = new JLabel("");
	    subname.setHorizontalAlignment(SwingConstants.CENTER);
	    subname.setFont(new Font("SansSerif", Font.BOLD, 17));
	    subname.setBounds(73, 78, 573, 60);
	    contentPane.add(subname);
	    
	    JCheckBox SelectAll = new JCheckBox("SELECT ALL");
	    SelectAll.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		if(SelectAll.isSelected())
		    	{	for(int i=0; i<table.getRowCount();i++)
		    		{
		    			model.setValueAt(true         , i, 5);
		    		}
	    		}
	    		else for(int i=0; i<table.getRowCount();i++)
	    		{
	    			model.setValueAt(false         , i, 5);
	    		}
	    	}
	    });
	    SelectAll.setFont(new Font("SansSerif", Font.BOLD, 12));
	    SelectAll.setBounds(597, 117, 104, 31);
	    SelectAll.setVisible(true);
	    contentPane.add(SelectAll);
	    
	    //ASSIGN THE MODEL TO TABLE

	    table.setModel(model);
	    model.addColumn("ROLL");
	    model.addColumn("SUBJECT CODE");
	    model.addColumn("DAYS ATTENDED");
	    model.addColumn("TOTAL DAYS");
	    model.addColumn("ATTENDANCE %");
	    model.addColumn("MARK PRESENT");
	    
	    //----------------------------------------------------
	    
	    try {
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			String user="PROJECT";
			String pass="123456";
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection db=DriverManager.getConnection(url, user, pass);
			
			String subcode=null;
			
			String queryy="SELECT SUBCODE FROM TEACHES WHERE PID=?";
			PreparedStatement ss=db.prepareStatement(queryy);
			ss.setString(1, pass_data);
			ResultSet res=ss.executeQuery();
			while(res.next())
			{			
				subcode = res.getString("SUBCODE");
			}
			
			String query="SELECT * FROM ATTENDS WHERE SUBCODE=?";
			PreparedStatement s=db.prepareStatement(query);
			s.setString(1, subcode);
		    ResultSet rs=s.executeQuery();
			
		    String querryy="SELECT SUBNAME FROM SUBJECT WHERE SUBCODE=?";
			PreparedStatement sss=db.prepareStatement(querryy);
			sss.setString(1, subcode);
			ResultSet rees=sss.executeQuery();
			while(rees.next())
			{			
				subname.setText("SUBJECT: "+rees.getString("SUBNAME"));
			}
		    
		    int i=0;
		    //THE ROW
		    while(rs.next())
		    {
		      model.addRow(new Object[0]);
		      model.setValueAt(rs.getString(1), i, 0);
		      model.setValueAt(rs.getString(2), i, 1);
		      model.setValueAt(rs.getString(3), i, 2);
		      model.setValueAt(rs.getString(4), i, 3);
		      model.setValueAt(rs.getString(5), i, 4);
		      model.setValueAt(false          , i, 5);
		      i++;
		    }
			
			table.setVisible(true);
		} catch (ClassNotFoundException | SQLException e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e1, "error", JOptionPane.ERROR_MESSAGE);
		} 

	    
	    //----------------------------------------------------


	    //OBTAIN SELECTED ROW
	    JButton btn=new JButton("SUBMIT ATTENDANCE");
	    btn.setBounds(409,510,197,42);
	    btn.setForeground(new Color(255, 255, 255));
	    btn.setBackground(new Color(65, 105, 225));
	    btn.setFont(new Font("SansSerif", Font.BOLD, 15));
	    btn.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent arg0) 
	    {
			if((JOptionPane.showConfirmDialog(null, "Are you sure you want to Submit the attendance?\nThe Database will be updated and the action is NOT REVOKABLE. ", "Confirmation", JOptionPane.YES_NO_OPTION)) == JOptionPane.YES_OPTION)
			{
    		try {	
    				//Here everybody gets a +1 to total days
    				int z=0;
    				
					for(int i=0;i<table.getRowCount();i++)
						{
						if(model.getValueAt(i, 5) ==Boolean.TRUE)
						z=1;
						}
    				if (z==1) {
						String url = "jdbc:oracle:thin:@localhost:1521:XE";
						String user = "PROJECT";
						String pass = "123456";
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection db = DriverManager.getConnection(url, user, pass);
						
						String subcode=null;
						String queryy="SELECT SUBCODE FROM TEACHES WHERE PID=?";
						PreparedStatement ss=db.prepareStatement(queryy);
						ss.setString(1, pass_data);
						ResultSet res=ss.executeQuery();
						while(res.next())
						{			
							subcode = res.getString("SUBCODE");
						}
						
						String query = "UPDATE ATTENDS SET TOTAL_DAYS=TOTAL_DAYS + 1 WHERE SUBCODE=?";
						PreparedStatement s = db.prepareStatement(query);
						s.setString(1,subcode);
						s.executeQuery();
						db.commit();
						db.close();
					}
    				else {}
       		}
    		catch (ClassNotFoundException | SQLException e)
    		{
					JOptionPane.showMessageDialog(null, "Error during updating total days: \n\n"+e, "",JOptionPane.ERROR_MESSAGE);
			} 
    		
    		int z=0;		
			for(int i=0;i<table.getRowCount();i++)
			{	
				Boolean checked=Boolean.valueOf(table.getValueAt(i,5).toString());
				String roll=table.getValueAt(i, 0).toString();
				if(checked) //Here, everybody who is checked will get a +1 to days attended
				{
					try 
					{
						String url="jdbc:oracle:thin:@localhost:1521:XE";
						String user="PROJECT";
						String pass="123456";
						Class.forName("oracle.jdbc.driver.OracleDriver");
						Connection db=DriverManager.getConnection(url, user, pass);
						
						String subcode=null;
						String queryy="SELECT SUBCODE FROM TEACHES WHERE PID=?";
						PreparedStatement ss=db.prepareStatement(queryy);
						ss.setString(1, pass_data);
						ResultSet res=ss.executeQuery();
						while(res.next())
						{			
							subcode = res.getString("SUBCODE");
						}
						
						String query="UPDATE ATTENDS SET DAYS_ATTENDED=DAYS_ATTENDED + 1 WHERE ROLL=? AND SUBCODE=?";
						PreparedStatement s = db.prepareStatement(query);
						s.setString(1, roll);
						s.setString(2,subcode);
						s.executeQuery();
						db.close();
					} 
					catch (ClassNotFoundException | SQLException e) 
					{
						JOptionPane.showMessageDialog(null, "Error during updating checked rows: \n\n"+e, "",JOptionPane.ERROR_MESSAGE);
					} 
					z=1;
				}
				
			}
						
			if (z==1)
			{
				//Here everybody's attendance percentage gets updated
				try {
					String url = "jdbc:oracle:thin:@localhost:1521:XE";
					String user = "PROJECT";
					String pass = "123456";
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection db = DriverManager.getConnection(url, user, pass);
					
					String subcode=null;
					String queryy="SELECT SUBCODE FROM TEACHES WHERE PID=?";
					PreparedStatement ss=db.prepareStatement(queryy);
					ss.setString(1, pass_data);
					ResultSet res=ss.executeQuery();
					while(res.next())
					{			
						subcode = res.getString("SUBCODE");
					}
					
					String query = "UPDATE ATTENDS SET PERCENT_ATTENDED=(DAYS_ATTENDED/TOTAL_DAYS) * 100 WHERE SUBCODE=? ";
					PreparedStatement s = db.prepareStatement(query);
					s.setString(1,subcode);
					s.executeQuery();
					JOptionPane.showMessageDialog(null, "attendance has been updated", "",
							JOptionPane.INFORMATION_MESSAGE);
					db.commit();
					db.close();
				} catch (HeadlessException | SQLException | ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null, "Error during computing attendance %: \n\n" + e, "",
							JOptionPane.ERROR_MESSAGE);
				} 
			} 
			if(z==0)
			{
				JOptionPane.showMessageDialog(null, "No students marked");
			}
			
		}
  	}
    
	        
	      
	    });
	    contentPane.add(btn);
	    
	    JLabel lblAttendance = new JLabel("ATTENDANCE");
	    lblAttendance.setHorizontalAlignment(SwingConstants.CENTER);
	    lblAttendance.setFont(new Font("Trebuchet MS", Font.BOLD, 50));
	    lblAttendance.setBounds(199, 6, 316, 66);
	    contentPane.add(lblAttendance);

	    JSeparator separator = new JSeparator();
	    separator.setBounds(20, 78, 677, 2);
	    contentPane.add(separator);
	
	    JButton btnNewButton = new JButton("\u2190");
	    btnNewButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		ProfAccessPage.profname=profname;
	    		ProfAccessPage.main(null);
	    		dispose();
	    	}
	    	
	    });
	    btnNewButton.setForeground(new Color(255, 255, 255));
	    btnNewButton.setBackground(new Color(65, 105, 225));
	    btnNewButton.setFont(new Font("SansSerif", Font.BOLD, 20));
	    btnNewButton.setBounds(2, 2, 52, 35);
	    contentPane.add(btnNewButton);
	    
	    JButton btnResetTable = new JButton("RESET TABLE");
	    btnResetTable.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		SelectAll.setSelected(false);
	    		for(int i=0; i<table.getRowCount();i++)
	    		{
	    			model.setValueAt(false         , i, 5);
	    		}
	    		model.setRowCount(0);
	    		try {
	    			String url="jdbc:oracle:thin:@localhost:1521:XE";
	    			String user="PROJECT";
	    			String pass="123456";
	    			Class.forName("oracle.jdbc.driver.OracleDriver");
	    			Connection db=DriverManager.getConnection(url, user, pass);
	    			
	    			String subcode=null;
	    			
	    			String queryy="SELECT SUBCODE FROM TEACHES WHERE PID=?";
	    			PreparedStatement ss=db.prepareStatement(queryy);
	    			ss.setString(1, pass_data);
	    			ResultSet res=ss.executeQuery();
	    			while(res.next())
	    			{			
	    				subcode = res.getString("SUBCODE");
	    			}
	    			
	    			String query="SELECT * FROM ATTENDS WHERE SUBCODE=?";
	    			PreparedStatement s=db.prepareStatement(query);
	    			s.setString(1, subcode);
	    		    ResultSet rs=s.executeQuery();
	    			
	    		    String querryy="SELECT SUBNAME FROM SUBJECT WHERE SUBCODE=?";
	    			PreparedStatement sss=db.prepareStatement(querryy);
	    			sss.setString(1, subcode);
	    			ResultSet rees=sss.executeQuery();
	    			while(rees.next())
	    			{			
	    				subname.setText("SUBJECT: "+rees.getString("SUBNAME"));
	    			}
	    		    
	    		    int i=0;
	    		    //THE ROW
	    		    while(rs.next())
	    		    {
	    		      model.addRow(new Object[0]);
	    		      model.setValueAt(rs.getString(1), i, 0);
	    		      model.setValueAt(rs.getString(2), i, 1);
	    		      model.setValueAt(rs.getString(3), i, 2);
	    		      model.setValueAt(rs.getString(4), i, 3);
	    		      model.setValueAt(rs.getString(5), i, 4);
	    		      model.setValueAt(false          , i, 5);
	    		      i++;
	    		    }
	    			
	    			table.setVisible(true);
	    		} catch (ClassNotFoundException | SQLException e1) {
	    			// TODO Auto-generated catch block
	    			JOptionPane.showMessageDialog(null, e1, "error", JOptionPane.ERROR_MESSAGE);
	    		} 
	    		
	    	}
	    		});
	    btnResetTable.setForeground(Color.WHITE);
	    btnResetTable.setFont(new Font("SansSerif", Font.BOLD, 15));
	    btnResetTable.setBackground(new Color(72, 61, 139));
	    btnResetTable.setBounds(107, 510, 197, 42);
	    contentPane.add(btnResetTable);
	    
	    
	}
}
			