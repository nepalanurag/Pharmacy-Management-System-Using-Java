package application;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class add_drugs {
	final DatePicker datePicker = new DatePicker();
@SuppressWarnings({ "rawtypes", "unchecked" })
public add_drugs(Stage add,String db_name)
{try {
	add.setTitle("Add Drugs Form");
	VBox root=new VBox();
	Text t=new Text("Drug Name:");
	TextField tf1=new TextField();
	Text t2=new Text("Type:");
	TextField tf2=new TextField();
	Text t3=new Text("Cost Price:");
	TextField tf3=new TextField();
	Text t4=new Text("Selling Price:");
	TextField tf4=new TextField();
	Text t5=new Text("Company Name:");
	ComboBox comboBox1=new ComboBox();
	 ObservableList<Object> data1 = FXCollections.observableArrayList();
	 ResultSet resultSet1 = null;
		try {
			Statement stmt=Connect.connect().createStatement();;
			resultSet1 =  stmt.executeQuery("Select name from company");
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

	    try {
			while (resultSet1.next()) {  // loop

				String current=resultSet1.getString("name");
				data1.add(current);	
				
}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    
	Text t6=new Text("Quantity:");
	TextField tf6=new TextField();
	Button b=new Button("Submit");
	Button b1=new Button("Back");
	
	b.setOnAction(new EventHandler <ActionEvent>()
			{
		public void handle(ActionEvent ae)
		{
			String str1=tf1.getText();
			String str2=tf2.getText();
			String str3=tf3.getText();
			String str4=tf4.getText();
			String str5=(String) comboBox1.getValue();
			String str6=tf6.getText();
			Connection con=Connect.connect();
			Statement stmt=null;
			if (con!=null)
				System.out.println("Connected");
			try {
			stmt =con.createStatement();
			String query="INSERT INTO drugs(NAME,TYPE,COST_PRICE,SELLING_PRICE,COMPANY_NAME,QUANTITY)"+ "VALUES ('"+str1+"','"+str2+"','"+str3+"','"+str4+"','"+str5+"','"+str6+"')";
			stmt.executeUpdate(query);	  	
			new Show(add,"drugs");
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
	comboBox1.setItems(data1);
	
	HBox hbox=new HBox();
	hbox.setSpacing(300);
	hbox.getChildren().addAll(b,b1);
	root.getChildren().addAll(t,tf1,t2,tf2,t3,tf3,t4,tf4,t5,comboBox1,t6,tf6,hbox);
	Scene sc=new Scene(root,400,400);
	add.setScene(sc);
	add.show();
} catch(Exception e) {
	e.printStackTrace();
}
	}
}
