
package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect {
  public static Connection connect(){
       try{
    Class.forName("com.mysql.cj.jdbc.Driver"); 
    @SuppressWarnings("unused")
	final String JdbcDriver="com.mysql.cj.jdbc.Driver";
	final String user="root";
	final String pass="";
	final String db_url="jdbc:mysql://127.0.0.1:3306/pharmacy"; 
	Connection con=DriverManager.getConnection(db_url,user,pass);
    if(con!=null)return con ;
   }catch(Exception e){
    System.out.println("Error!!!");
   }
   return null ;
}   
  public static void main(String[] args) {}
  }