package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Scene2Controller implements Initializable {
	@FXML
	private TextField tfGrocery;
	@FXML
	private TextField tfQuantity;
	@FXML
	private DatePicker tfUseBy;
	@FXML
	private TableView<Groceries> tvGrocery;
	@FXML
	private TableColumn<Groceries, Integer> colid;
	@FXML
	private TableColumn<Groceries, String> colGrocery;
	@FXML
	private TableColumn<Groceries, String> colQuantity;
	@FXML
	private TableColumn<Groceries, Date> colUseBy;

    @FXML
    private Button backButton;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnInsert;

    @FXML
    private Button btnUpdate;

	
	
	// Event Listener on Button.onAction
	 @FXML void switchToScene1(ActionEvent event) throws IOException {
	    	Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
			Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	    }
    
    public ObservableList<Groceries> getGroceriesList(){
    	ObservableList<Groceries> groceryList = FXCollections.observableArrayList();
    	DBConnection db = new DBConnection();
    	String query = "SELECT * FROM groceries";
    	ResultSet rs;
    	
    	try {
    		rs = db.connectAndExecute(query, DBConnection.SELECT);
    		Groceries groceries;
    		while(rs.next()) {
    			groceries = new Groceries(rs.getInt("id"),rs.getString("grocery_name"),rs.getString("quantity"),rs.getDate("useby"));
    			groceryList.add(groceries);
    		}
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
    	return groceryList;
    }
    
    public void showGroceries() {
    	ObservableList<Groceries> list = getGroceriesList();
    	
    	colid.setCellValueFactory(new PropertyValueFactory<Groceries, Integer>("id"));
    	colGrocery.setCellValueFactory(new PropertyValueFactory<Groceries, String>("groceryName"));
    	colQuantity.setCellValueFactory(new PropertyValueFactory<Groceries, String>("quantity"));
    	colUseBy.setCellValueFactory(new PropertyValueFactory<Groceries, Date>("useBy"));
    	
    	tvGrocery.setItems(list);
    }
    
    private void insertRecord() {
    	DBConnection db = new DBConnection();
    	String query = "INSERT INTO groceries (grocery_name,quantity, useby) VALUES (" + "'" + tfGrocery.getText() + "','" + tfQuantity.getText() + "','" + tfUseBy.getValue() + "')";
    	System.out.println(query);
    	db.connectAndExecute(query, DBConnection.INSERT);
    	showGroceries();
    }
    private void updateRecord(){
    	DBConnection db = new DBConnection();
        String query = "UPDATE groceries SET grocery_name  = '" + tfGrocery.getText() + "', quantity = '" + tfQuantity.getText() + "', useby = '" +
        		tfUseBy.getValue() + "' WHERE grocery_name = '" + tfGrocery.getText() + "'";
        System.out.println(query);
        db.connectAndExecute(query, DBConnection.INSERT);
        showGroceries();
        tfGrocery.setText("");
        tfQuantity.setText("");
    }
    
    private void deleteButton(){
    	DBConnection db = new DBConnection();
        String query = "DELETE FROM groceries WHERE grocery_name = '" + tfGrocery.getText() + "'";
        db.connectAndExecute(query, DBConnection.DELETE);
        showGroceries();
        tfGrocery.setText("");
        tfQuantity.setText("");
    }
	
	// Event Listener on Button[#btnInsert].onAction
	@FXML
	public void handleInsert(ActionEvent event) {
		insertRecord();
	}
	// Event Listener on Button[#btnDelete].onAction
	@FXML
	public void handleDelete(ActionEvent event) {
		deleteButton();
	}
	// Event Listener on Button[#btnUpdate].onAction
	@FXML
	public void handleUpdate(ActionEvent event) {
		updateRecord();
	}
	
	@FXML
	void rowClicked(MouseEvent event) {
		Groceries clickedGroceries = tvGrocery.getSelectionModel().getSelectedItem();
		tfGrocery.setText(String.valueOf(clickedGroceries.getGroceryName()));
		tfQuantity.setText(String.valueOf(clickedGroceries.getQuantity()));
		}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		showGroceries();
	}
}
