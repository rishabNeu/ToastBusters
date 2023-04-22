package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class FavouriteController implements Initializable  {

	
	@FXML
    private Button backButton;
	
	@FXML
	private WebView idrecipewebview;
	
	@FXML
	private TextArea favTxtArea;
	
	@FXML 
	private ComboBox<String> favCombo;
	
	@FXML
	private Button favSelect;
	
    @FXML
    private void backAction(ActionEvent event) throws IOException {
    	System.out.println("in back button");
    	Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
    
    @FXML
    private void favAction(ActionEvent event) throws IOException {
    	System.out.println("in Fav Action");
		
		DBConnection db = new DBConnection();

    	
    	String favRecipeName = favCombo.getValue();
		
    	//String query = "SELECT DISTINCT mealname FROM favourites where mealname ;";
		String query = "SELECT mealname, link FROM favourites WHERE mealname = '" + favRecipeName + "'";
    	System.out.println(query);
    	
    	//getting clicked fav item
    	ResultSet rs;
    	String youtubeLink = "";
		
		try {
    		rs = db.connectAndExecute(query,DBConnection.SELECT);

    		while(rs.next()) {
    			
    			youtubeLink = rs.getString("link");
    		
    		}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
		
    	System.out.println("in fav to check link value : " + youtubeLink);
    	
         WebEngine webEngine = idrecipewebview.getEngine();
        webEngine.load(youtubeLink);
        //idrecipewebview.setPrefSize(640, 390);

    }
    


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		HashSet<String> favourites= new HashSet<>();
		DBConnection db = new DBConnection();
		String query = "SELECT DISTINCT mealname FROM favourites;";
		ResultSet rs;
		
		try {
    		rs = db.connectAndExecute(query,DBConnection.SELECT);

    		while(rs.next()) {
    			favourites.add(rs.getString("mealname"));
    		}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
		
		
		for(String fav : favourites) {
			favCombo.getItems().add(fav);
			favTxtArea.appendText(fav + "\n");

	    }

		
	}
	


}
