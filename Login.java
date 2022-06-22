package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login  {
	
	public static  String usrName;

	public Login(Stage login) {
		try {
			login.setTitle("Pharmacy Login");
			login.getIcons().add(new Image(getClass().getResource( "icon1.png").toExternalForm()));
	        BorderPane bp = new BorderPane();
	        bp.setPadding(new Insets(10,50,50,50));
	         
	        //Adding HBox
	        HBox hb = new HBox();
	        hb.setPadding(new Insets(30,30,30,30));
	         
	        //Adding GridPane
	        GridPane gridPane = new GridPane();
	        gridPane.setPadding(new Insets(20,20,20,20));
	        gridPane.setHgap(10);
	        gridPane.setVgap(10);
	         
	       //Implementing Nodes for GridPane
	        Label lblUserName = new Label("Username");
	        final TextField txtUserName = new TextField();
	        Label lblPassword = new Label("Password");
	        final PasswordField pf = new PasswordField();
	        Button btnLogin = new Button("Login");
	        final Label lblMessage = new Label();
	         
	        //Adding Nodes to GridPane layout
	        gridPane.add(lblUserName, 0, 0);
	        gridPane.add(txtUserName, 1, 0);
	        gridPane.add(lblPassword, 0, 1);
	        gridPane.add(pf, 1, 1);
	        gridPane.add(btnLogin, 1, 2);
	        gridPane.add(lblMessage, 1, 4);
	         
	         
	        //DropShadow effect 
	        DropShadow dropShadow = new DropShadow();
	        dropShadow.setOffsetX(2);
	        dropShadow.setOffsetY(2);
	         
	        //Adding text and DropShadow effect to it
	        Text text = new Text("NITTE Pharmacy");
	        text.setFont(Font.font("Courier New", FontWeight.EXTRA_BOLD, 30));
	        text.setEffect(dropShadow);
	         
	        //Adding text to HBox
	        hb.getChildren().add(text);
	                           
	        //Add ID's to Nodes
	        bp.setId("bp");
	        gridPane.setId("root");
	        btnLogin.setId("btnLogin");
	        text.setId("text");
	        btnLogin.setOnAction(new EventHandler <ActionEvent>()
			{ Connection con=Connect.connect();
		public void handle(ActionEvent ae)
		{
			usrName=txtUserName.getText();
			String str2=pf.getText();
			Statement stmt=null;
			//new HomePage(login);
			if (con!=null)
				System.out.println("Connected");
			try {
			stmt =con.createStatement();
			String query="select PASSWORD FROM users where NAME='"+usrName+"';";    	
			ResultSet rs = stmt.executeQuery(query);
			 String res = null;
			  if (rs.next()) {
		           res = rs.getString(1);
		        }
			if(str2.equals(res))
			{
				   lblMessage.setText("Redirecting...");
		           lblMessage.setTextFill(Color.GREEN);
		           stmt=con.createStatement();
		           LocalDate localDate = LocalDate.now();
		           LocalTime localTime = LocalTime.now();
		           String query1="INSERT INTO login(NAME,DATE,TIME)"+" VALUES ('"+usrName+"','"+localDate.toString()+"','"+localTime.toString()+"')";
		           stmt.executeUpdate(query1);
		           new HomePage(login);
			}
			else{
		           lblMessage.setText("Incorrect Username or Password.Try Again!!");
		           lblMessage.setTextFill(Color.RED);
		          }
			
			}	
			catch(Exception e) {
				System.out.println("ERROR !");
			}
			
		}
			});
	        bp.setTop(hb);
	        bp.setCenter(gridPane);
	        Scene scene = new Scene(bp);
	        login.setScene(scene);      
			login.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
