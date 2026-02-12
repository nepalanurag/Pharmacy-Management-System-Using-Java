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
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class new_purchase {
	int amt=0;
@SuppressWarnings({ "unchecked", "rawtypes" })
public new_purchase(Stage add,String db_name)
{try {
	add.setTitle("New Purchase Form");
	VBox root=new VBox();
	Text t=new Text("Medicine Name:");
	ComboBox comboBox=new ComboBox();
	 ObservableList<Object> data = FXCollections.observableArrayList();
	 ResultSet resultSet = null;
		try {
			Statement stmt=Connect.connect().createStatement();;
			resultSet =  stmt.executeQuery("Select name from drugs");
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

	    try {
			while (resultSet.next()) {  // loop

				String current=resultSet.getString("name");
				data.add(current);	
				
}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	Text t3=new Text("Company Name:");
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
	Text t4=new Text("Quantity:");
	TextField tf4=new TextField();
	Button b=new Button("Submit");
	Button b1=new Button("Back");
	
	b.setOnAction(new EventHandler <ActionEvent>()
			{
		public void handle(ActionEvent ae)
		{
					
			String  str1=(String) comboBox.getValue();
			
			String str3=(String) comboBox1.getValue();
			int str4=Integer.parseInt(tf4.getText());
			 int stk=0;
			 int pri=0;
			 String type=null;
			 Statement stmt = null;
			try {
				stmt = Connect.connect().createStatement();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				String query="select Type,Quantity,COST_PRICE FROM drugs where NAME='"+str1+"';";    	
				ResultSet rs = null;
				try {
					rs = stmt.executeQuery(query);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 int res = 0;
				  try {
					if (rs.next()) {
						type=rs.getString(1);
					       res = rs.getInt(2);
					       pri=rs.getInt(3);
					    }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 stk=res+str4;
			 String query2="Update drugs set quantity='"+stk+"' where NAME='"+str1+"';";
			 try {
				stmt.executeUpdate(query2);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			int amt= Integer.parseInt((tf4.getText()))*pri;
			Connection con=Connect.connect();
			Statement stmt1=null;
			if (con!=null)
				System.out.println("Connected");
			try {
			stmt1 =con.createStatement();
			String query1="INSERT INTO purchase(NAME,TYPE,COMPANY_NAME,QUANTITY,PRICE,AMOUNT)"+ "VALUES ('"+str1+"','"+type+"','"+str3+"','"+str4+"','"+pri+"','"+amt+"')";
			stmt1.executeUpdate(query1);	
			new Show(add,"purchase");
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
	comboBox.setItems(data);
	comboBox1.setItems(data1);
	HBox hbox=new HBox();
	hbox.setSpacing(300);
	hbox.getChildren().addAll(b,b1);
	root.getChildren().addAll(t,comboBox,t3,comboBox1,t4,tf4,hbox);
	Scene sc=new Scene(root,400,400);
	add.setScene(sc);
	add.show();
} catch(Exception e) {
	e.printStackTrace();
}
	}
}