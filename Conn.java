package pkg;
import java.sql.*;

public class Conn {
	
    String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=图书管理系统";
	String userName = "se";
	String userPwd = "2161654";
	  
	Connection conn;
	Statement stmt;
  	ResultSet rs;
	
    public void connDB(){
  	    try {
  		    conn=DriverManager.getConnection(dbURL,userName,userPwd);
  		    stmt=conn.createStatement();
  		    System.out.println("连接成功");
  	    }
  	    catch(Exception e) {
  		    e.printStackTrace();
  		    System.out.println("连接失败");
  	    }
    }
    
    public void closeDB() {
		try {
			rs.close();
			stmt.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
    
}
