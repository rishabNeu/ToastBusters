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
import javafx.stage.Stage;

public class PlannerController implements Initializable  {

	
	@FXML
    private Button backButton;
	
	
	@FXML
	private TextArea favTxtArea;
	
//    @FXML
//    private ImageView backPlanner;

//    @FXML
//    private ComboBox<String> cbBreakfast;

//    @FXML
//    private ComboBox<String> cbDay;
//
//    @FXML
//    private ComboBox<String> cbDinner;
//
//    @FXML
//    private ComboBox<String> cbLunch;
//    
//    @FXML
//    private TableView<Planner> plannerView;

//    @FXML
//    private TableColumn<Planner, String> colBreakfast;
//
//    @FXML
//    private TableColumn<Planner, String> colDay;

//    @FXML
//    private TableColumn<Planner, String> colDinner;
//
//    @FXML
//    private TableColumn<Planner, String> colLunch;

//    @FXML
//    private ImageView updatePlan;
    
    

//    @FXML
//    void goToMainMenu(ActionEvent event) throws IOException {
//    	Scene2Controller sc = new Scene2Controller();
//    	sc.switchToScene1(event);
//    }

    @FXML
    private void backAction(ActionEvent event) throws IOException {
    	System.out.println("in back button");
    	Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
    
//    @FXML
//    void updateDayPlan(MouseEvent event) {
//    	DBConnection db = new DBConnection();
//    	String query = "UPDATE dayPlan SET day  = '" + cbDay.getValue() +
//    			"', breakfast = '" + cbBreakfast.getValue() +
//    			"', lunch = '" + cbLunch.getValue() +
//    			"', dinner = '" + cbDinner.getValue() +
//    			"' WHERE day = '" + cbDay.getValue() + "'";
//        System.out.println(query);
//        db.connectAndExecute(query, DBConnection.INSERT);
//        showPlanner();
//
//    }

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
		
		
		for(String lvs : favourites) {
//			cbBreakfast.getItems().add(lvs);
//			cbDinner.getItems().add(lvs);
//			cbLunch.getItems().add(lvs);
			favTxtArea.appendText(lvs + "\n");

	    }
//		showPlanner();
		
	}
	
//	private void showPlanner() {
//		DBConnection db = new DBConnection();
//		ObservableList<Planner> plannerList =  FXCollections.observableArrayList();
//		String query_planner = "SELECT * FROM dayPlan";
//		ResultSet rs2;
//		
//		try{
//			rs2 = db.connectAndExecute(query_planner,DBConnection.SELECT);
//			Planner planner;
//			while(rs2.next()) {
//				planner = new Planner(rs2.getInt("id"), rs2.getString("day"),
//						rs2.getString("breakfast"), rs2.getString("lunch"), rs2.getString("dinner"));
//				plannerList.add(planner);
//				cbDay.getItems().add(rs2.getString("day"));
//			}
//		}
//		catch(Exception ex) {
//			ex.printStackTrace();
//		}
//		
//		colDay.setCellValueFactory(new PropertyValueFactory<Planner, String>("day"));
//		colBreakfast.setCellValueFactory(new PropertyValueFactory<Planner, String>("breakfast"));
//		colLunch.setCellValueFactory(new PropertyValueFactory<Planner, String>("lunch"));
//    	colDinner.setCellValueFactory(new PropertyValueFactory<Planner, String>("dinner"));
//    	
//    	plannerView.setItems(plannerList);	
//		
//	}

}
