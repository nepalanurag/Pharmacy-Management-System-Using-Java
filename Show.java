package application;
	
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;


public class Show {
    //TABLE VIEW AND DATA
    private ObservableList<ObservableList> data;
    private TableView tableview;
    final HBox hb = new HBox();
    public Show(Stage stage,String db_name) {
        //TableView
        tableview = new TableView();

        data = FXCollections.observableArrayList();
        Connection c = Connect.connect();
        try {
           
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT * from "+db_name;
            
			//ResultSet
            ResultSet rs = c.createStatement().executeQuery(SQL);
            
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });
                tableview.getColumns().addAll(col);
            }
 
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                data.add(row);
 
            }

            tableview.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
        final Button backBtn = new Button("Back");
        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                new HomePage(stage);
            }
        });
        final Text t=new Text("ID to be deleted:");
        final TextField tf=new TextField();
        final Button delBtn = new Button("Delete");
        delBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	
            	int str1=Integer.parseInt(tf.getText());
            	try {
            		 String sql = "delete from "+db_name+" where ID=?";
            		 try (Connection conn = Connect.connect(); 
            			       PreparedStatement stmt = conn.prepareStatement(sql)){
            		      stmt.setInt(1,str1);
            		      stmt.executeUpdate();
            		      System.out.println("Record deleted successfully");
            		      new Show(stage,db_name);
            		    }
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
    		
				 
            }
        });
        final Button addBtn = new Button("Add New");
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
            	if(db_name=="company") new add_company(stage,db_name);
            	else if(db_name=="drugs") new add_drugs(stage,db_name);
            	else if(db_name=="history_sales") new new_sale(stage,db_name);
            	else if(db_name=="purchase") new new_purchase(stage,db_name);
            	else if(db_name=="users") new add_user(stage,db_name);
            	
            }
        });
        
        hb.getChildren().addAll(backBtn,t,tf,delBtn,addBtn);
        final VBox vbox = new VBox();
 
        vbox.getChildren().addAll(tableview, hb);
        
        
        Scene scene = new Scene(vbox);
        
        stage.setTitle(db_name);
        stage.setScene(scene);
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
                System.exit(0);
            }
        });
        stage.show();
    }
}
