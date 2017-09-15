package com.example.androidsql;  
  
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.Statement;  
import net.sourceforge.jtds.jdbc.Driver;

public class DataBaseUtil {
	
	private static Connection getSQLConnection(String ip, String user, String pwd, String db)  
    {  
        Connection con = null;  
        try  
        {  
        	System.out.println("0");
        	Class.forName("net.sourceforge.jtds.jdbc.Driver"); 
        	System.out.println("1");
            
            con = DriverManager.getConnection("jdbc:jtds:sqlserver://" + ip + ":1433/" + db + ";charset=utf8; useLOBs=false", user, pwd); 
            System.out.println("value of conn is "+con);
             
        } catch (ClassNotFoundException e)  
        {  
            e.printStackTrace(); 
            System.out.println("2");
        } catch (SQLException e)  
        {  
            e.printStackTrace();
            System.out.println("3");
        }  
        return con;  
    }  
  
    public static String testSQL()  
    {  
        String result = "时间  ---------  值-----名字\n";  
        try  
        {  
            Connection conn = getSQLConnection("**.**.**.**", "sa", "123456", "test");  
            String sql = "select top 10 * from contents";  
            Statement stmt = conn.createStatement(); 
            ResultSet rs = stmt.executeQuery(sql);  
            while (rs.next())  
            {// <code>ResultSet</code>
            	String s0 = rs.getString("date");
                String s1 = rs.getString("value");  
                String s2 = rs.getString("name");  
                result +=s0+"-"+ s1 + "  -  " + s2 + "\n";  
                System.out.println(s1 + "  -  " + s2);  
            }  
            rs.close();  
            stmt.close();  
            conn.close();  
        } catch (SQLException e)  
        {  
            e.printStackTrace();  
            result += "查询数据异常!" + e.getMessage();  
        }  
        return result;  
    }  
  
    public static void main(String[] args)  
    {  
        testSQL();  
    }  

}
