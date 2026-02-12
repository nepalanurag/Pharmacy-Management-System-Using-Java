package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class add_company {
public add_company(Stage add,String db_name)
{try {
	add.setTitle("Add Company Form");
	VBox root=new VBox();
	Text t=new Text("Company Name:");
	TextField tf1=new TextField();
	Text t2=new Text("Address:");
	TextField tf2=new TextField();
	Text t3=new Text("Phone:");
	TextField tf3=new TextField();
	Button b=new Button("Submit");
	Button b1=new Button("Back");
	b.setOnAction(new EventHandler <ActionEvent>()
			{
		public void handle(ActionEvent ae)
		{
			String str1=tf1.getText();
			String str2=tf2.getText();
			String str3=tf3.getText();
			System.out.println(str1);
			System.out.println(str2);
			System.out.println(str3);
			Connection con=Connect.connect();
			Statement stmt=null;
			if (con!=null)
				System.out.println("Connected");
			
			try {
				stmt =con.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String query="INSERT INTO company (NAME, ADDRESS, PHONE)"+ "VALUES ('"+str1+"','"+str2+"','"+str3+"')";
			try {
				stmt.executeUpdate(query);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	  	
			ResultSet rs = null;
			try {
				rs = stmt.executeQuery("Select * from company");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Records from the table");
			try {
				while(rs.next()) {
					try {
						System.out.println(" Company Name: "+rs.getString(2)+" Address: "+rs.getString(3));
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			new Show(add,"company");
			
			
			
		}
			});
	b1.setOnAction(new EventHandler <ActionEvent>()
	{
		public void handle(ActionEvent ae)
		{
			new HomePage(add);
		}
		});
	HBox hbox=new HBox();
	hbox.setSpacing(300);
	hbox.getChildren().addAll(b,b1);
	root.getChildren().addAll(t,tf1,t2,tf2,t3,tf3,hbox);
	Scene sc=new Scene(root,400,400);
	add.setScene(sc);
	add.show();
} catch(Exception e) {
	e.printStackTrace();
}
	}
}
