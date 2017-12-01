package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//import javax.swing.UIManager;
//import javax.swing.UIManager.LookAndFeelInfo;

public class START{	
	public static void main(String[] args) {
		String url="jdbc:oracle:thin:@localhost:1521:XE";
		String user="PROJECT";
		String pass="123456";
		Connection db=null;
		
//		try 
//		{
//			for(LookAndFeelInfo info: UIManager.getInstalledLookAndFeels())
//			{
//				if("Nimbus".equals(info.getName()))
//				{
//					UIManager.setLookAndFeel(info.getClassName());
//					break;
//				}
//			}
//		}catch(Exception e)
//		{
//			e.printStackTrace();
//		}
		
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			db=DriverManager.getConnection(url, user, pass);
			if(db!=null)
			{
				LoginPage.main(null);
			}
		}catch(ClassNotFoundException|SQLException e1)
		{
			InitSchema.main(null);
		}
	}

}