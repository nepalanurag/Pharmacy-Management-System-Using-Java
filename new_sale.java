package application;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class new_sale {
	int amt=0;
@SuppressWarnings({ "unchecked", "rawtypes" })
public new_sale(Stage add,String db_name)
{
	
	try {
	add.setTitle("New Sale Form");
	VBox root=new VBox();
	Text t=new Text("Employee Name:");
	Text t1=new Text(Login.usrName);
	Text t2=new Text("Medicine Name:");
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
	Text t4=new Text("Quantity:");
	TextField tf4=new TextField();
	Button b=new Button("Submit");
	Button b1=new Button("Back");
	Label lblmessage=new Label();

	b.setOnAction(new EventHandler <ActionEvent>()
			{
		public void handle(ActionEvent ae)
		{ int pri=0;
			String str2=(String)comboBox.getValue();
			int str4=Integer.parseInt(tf4.getText());
			 int stk=0;
			 String type=null;
			 Connection conn = Connect.connect();
				String query="select Type,Quantity,SELLING_PRICE FROM drugs where NAME=?";
				ResultSet rs = null;
				try {
					PreparedStatement ps = conn.prepareStatement(query);
					ps.setString(1, str2);
					rs = ps.executeQuery();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 int res = 0;
				  try {
					if (rs.next()) {
						 type = rs.getString(1);
					       res = rs.getInt(2);
					       pri=rs.getInt(3);
					       	
					    }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				  if(res<str4) {
					  lblmessage.setText("Error!!!Stock not available");
					  lblmessage.setTextFill(Color.RED);
				  }
				  else {
			 stk=res-str4;
			 String query2="Update drugs set quantity=? where NAME=?";
			 try {
				PreparedStatement ps2 = conn.prepareStatement(query2);
				ps2.setInt(1, stk);
				ps2.setString(2, str2);
				ps2.executeUpdate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			int str5=pri;
			int amt= Integer.parseInt((tf4.getText()))* str5;
			lblmessage.setText("Amount to be paid:"+amt);
			lblmessage.setTextFill(Color.BLUE);
			Connection con=Connect.connect();
			if (con!=null)
				System.out.println("Connected");
		
			String query1="INSERT INTO history_sales(USER_NAME,NAME,TYPE,QUANTITY,PRICE,AMOUNT,DATE,TIME) VALUES (?,?,?,?,?,?,?,?)";
			try {
				PreparedStatement stmt1 = con.prepareStatement(query1);
				stmt1.setString(1, Login.usrName);
				stmt1.setString(2, str2);
				stmt1.setString(3, type);
				stmt1.setInt(4, str4);
				stmt1.setInt(5, str5);
				stmt1.setInt(6, amt);
				stmt1.setString(7, LocalDate.now().toString());
				stmt1.setString(8, LocalTime.now().toString());
				stmt1.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	 
			
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
	Button show_sales=new Button("Show Sales");
	show_sales.setOnAction(new EventHandler <ActionEvent>()
	{
		public void handle(ActionEvent ae)
		{
			new Show(add,"history_sales");
		}
		});
	comboBox.setItems(data);
	

	HBox hbox=new HBox();
	hbox.setSpacing(300);
	hbox.getChildren().addAll(b,b1);
	root.getChildren().addAll(t,t1,t2,comboBox,t4,tf4,hbox,lblmessage,show_sales);
	Scene sc=new Scene(root,400,400);
	add.setScene(sc);
	add.show();
} catch(Exception e) {
	e.printStackTrace();
}
	}
}