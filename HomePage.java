package application;



import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class HomePage
{

FlowPane root = new FlowPane();
Stage stage;
public HomePage(Stage home)
{
	
	try {
		BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10,50,50,50));
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(2);
        dropShadow.setOffsetY(2);
        Text text = new Text("NITTE Pharmacy");
        text.setFont(Font.font("Courier New", FontWeight.EXTRA_BOLD, 30));
        text.setEffect(dropShadow);
        
		Menu menu0=new Menu("Menu");
        
		  Menu menu01 = new Menu("Company");
	      MenuItem item01 = new MenuItem("Show");
	      MenuItem item02 = new MenuItem("Add");
	      menu01.getItems().addAll(item01, item02);
	      menu01.setStyle( " -fx-padding: 10 1 1 50;" );
	      
	      Menu menu11 = new Menu("Drugs");
	      MenuItem item11 = new MenuItem("Show");
	      MenuItem item12 = new MenuItem("Add Drugs");
	      menu11.getItems().addAll(item11, item12);
	      menu11.setStyle( " -fx-padding: 10 1 1 50;" );

	      Menu menu21 = new Menu("Sales");
	      MenuItem item21 = new MenuItem("Show");
	      MenuItem item22 = new MenuItem("New Sale");
	      menu21.getItems().addAll(item21, item22);
	      menu21.setStyle( " -fx-padding: 10 1 1 50;" );
	      
	      Menu menu31 = new Menu("Purchases");
	      MenuItem item31 = new MenuItem("Show");
	      MenuItem item32 = new MenuItem("New Purchase");
	      menu31.getItems().addAll(item31, item32);
	      menu31.setStyle( " -fx-padding: 10 1 1 50;" );

	      Menu menu41 = new Menu("Users");
	      MenuItem item41 = new MenuItem("Show");
	      MenuItem item42 = new MenuItem("Add User");
	      menu41.getItems().addAll(item41, item42);
	      menu41.setStyle( " -fx-padding: 10 1 1 50;" );
	      
	      menu0.getItems().addAll(menu01,menu11,menu21,menu31,menu41);
	      MenuBar menuBar = new MenuBar(menu0);
	      menuBar.setStyle("-fx-background-size: 1200 900;\r\n"
	      		+ "-fx-background-radius: 30;\r\n"
	      		+ "-fx-border-radius: 30;\r\n"
	      		+ "-fx-border-width:5;\r\n"
	      		+ "-fx-border-color: #FC3D44;"
	      		);
	      item01.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
	          public void handle(ActionEvent event) {
	             
	             try {
					new Show(home,"company");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	          }
	       });
	      item02.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
	          public void handle(ActionEvent event) {
	             
	             try {
					new add_company(home,"company");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	          }
	       });
	      
	     
	      item11.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
	          public void handle(ActionEvent event) {
	             
	             try {
					new Show(home,"drugs");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	          }
	       });
	      item12.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
	          public void handle(ActionEvent event) {
		             
		             try {
						new add_drugs(home,"drugs");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		          }
		       });
	      
	      item21.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
	          public void handle(ActionEvent event) {
	             
	             try {
					new Show(home,"history_sales");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	          }
	       });
	      item22.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
	          public void handle(ActionEvent event) {
	             
	             try {
					new new_sale(home,"history_sales");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	          }
	       });
	      
	      item31.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
	          public void handle(ActionEvent event) {
	             
	             try {
					new Show(home,"purchase");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	          }
	       });

	      item32.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
	          public void handle(ActionEvent event) {
	             
	             try {
					new new_purchase(home,"purchase");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	          }
	       });
	      
	      item41.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
	          public void handle(ActionEvent event) {
	             
	             try {
					new Show(home,"users");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	          }
	       });
	      item42.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
	          public void handle(ActionEvent event) {
	             
	             try {
					new add_user(home,"users");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	          }
	       });
	     
	      

	        HBox rightBox = new HBox(text);
	        rightBox.setAlignment(Pos.CENTER_RIGHT);
	 
	        Button exitBtn=new Button("Sign Out");
	        exitBtn.setStyle("-fx-background-size: 1200 900;\r\n"
		      		+ "-fx-background-radius: 30;\r\n"
		      		+ "-fx-border-radius: 30;\r\n"
		      		+ "-fx-border-width:5;\r\n"
		      		+ "-fx-border-color: #FC3D44;"
		      		);
	        exitBtn.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {
		          public void handle(ActionEvent event) {
			             
			             try {
			            	 new Login(home);
			            	 System.out.println("Signed out "+Login.usrName);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			          }
			       });
			HBox leftBox=new HBox(exitBtn);
			
	        HBox bottom = new HBox(menuBar,rightBox,leftBox);
	       
	        bottom.setSpacing(100);
	        
	       
	        Text tf1 = new Text("Anurag Nepal");
	        tf1.setFont(Font.font("Courier New", FontWeight.EXTRA_BOLD, 15));
	        tf1.setFill(Color.GREEN);
	    	Text tf2 = new Text("Avishek Rijal");
	    	tf2.setFont(Font.font("Courier New", FontWeight.EXTRA_BOLD, 15));
	    	tf2.setFill(Color.CYAN);
	    	Text tf3 = new Text("Baibhav Dhakal");
	    	tf3.setFont(Font.font("Courier New", FontWeight.EXTRA_BOLD, 15));
	    	tf3.setFill(Color.CHOCOLATE);
	    	
	    	VBox hb1=new VBox();
	        hb1.getChildren().addAll(tf1);
	        hb1.setSpacing(50);
	        
	        VBox hb2=new VBox();
	        hb2.getChildren().addAll(tf2);
	       
	        
	        VBox hb3=new VBox();
	        hb3.getChildren().addAll(tf3);
	        
	        
	        HBox vb=new HBox();
	        vb.getChildren().addAll(hb1,hb2,hb3);
	        vb.setSpacing(50);
	        HBox hab = new HBox();
	        hab.getChildren().add(vb);
	        
	        Pane filler = new Pane();
	        hab.getChildren().add(filler);
	        HBox.setHgrow(filler, Priority.ALWAYS);
	        //Adding GridPane
	        GridPane gridPane = new GridPane();
	        gridPane.setPadding(new Insets(20,20,20,20));
	        Image image = new Image(getClass().getResource( "image.jpeg").toExternalForm());
	        ImageView pic = new ImageView();
	        pic.setFitWidth(550);
	        pic.setFitHeight(500);
	        pic.setImage(image);
	        gridPane.add(pic, 0, 0);
	       
	        gridPane.setHgap(10);
	        gridPane.setVgap(10);
	        
	        bp.setTop(bottom);
	        bp.setCenter(gridPane);
	        bp.setBottom(hab);
	        bp.setBackground(null);;
	        Scene scene = new Scene(bp,700,600);
	        scene.setFill(new LinearGradient(
	       	        0, 0, 1, 1, true,                      //sizing
	       	        CycleMethod.NO_CYCLE,                  //cycling
	       	        new Stop(0, Color.web("#bebec2")),new Stop(1, Color.web("#94f288")),new Stop(1, Color.web("#b027a0")),new Stop(0, Color.web("#b027a0")),     //colors
	       	        new Stop(1, Color.web("#964545")))
	       	);
	        home.setScene(scene);  
	        home.setTitle("HOME");
			home.show();

    } catch(Exception e) {
        e.printStackTrace();
    }
	
}
}