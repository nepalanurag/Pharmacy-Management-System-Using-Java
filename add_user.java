package application;

import java.sql.Connection;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class add_user {
public add_user(Stage add,String db_name)
{try {
	add.setTitle("Add New Employee Form");
	VBox root=new VBox();
	Text t=new Text("Name:");
	TextField tf1=new TextField();
	
	DatePicker datePicker = new DatePicker();
    datePicker.setMinHeight(12.);
    datePicker.setPrefHeight(16.);
    datePicker.setMaxHeight(60.);
    HBox hBox = new HBox();
    hBox.getChildren().add(new Label("Date Of Birth:"));
    hBox.getChildren().add(datePicker);
    
	Text t3=new Text("Address:");
	TextField tf3=new TextField();
	Text t4=new Text("Phone:");
	TextField tf4=new TextField();
	Text t5=new Text("Salary:");
	TextField tf5=new TextField();
	Text t6=new Text("Password");
	PasswordField pf = new PasswordField();
	Button b=new Button("Submit");
	Button b1=new Button("Back");
	
	b.setOnAction(new EventHandler <ActionEvent>()
			{
		public void handle(ActionEvent ae)
		{
					
			String str1=tf1.getText();
			String str3=tf3.getText();
			String str4=tf4.getText();
			int str5=Integer.parseInt(tf5.getText());
			String str6=pf.getText();
			Connection con=Connect.connect();
			Statement stmt=null;
			if (con!=null)
				System.out.println("Connected");
			try {
			stmt =con.createStatement();
			String query="INSERT INTO users(NAME,DOB,ADDRESS,PHONE,SALARY,PASSWORD)"+"VALUES ('"+str1+"','"+datePicker.getValue()+"','"+str3+"','"+str4+"','"+str5+"','"+str6+"')";
			stmt.executeUpdate(query);	  	
			new Show(add,"users");
			}	
			catch(Exception e) {
				System.out.println("ERROR !");
			}
			
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
	root.getChildren().addAll(t,tf1,hBox,t3,tf3,t4,tf4,t5,tf5,t6,pf,hbox);
	Scene sc=new Scene(root,400,400);
	add.setScene(sc);
	add.show();
} catch(Exception e) {
	e.printStackTrace();
}
	}
}