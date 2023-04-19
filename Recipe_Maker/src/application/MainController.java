package application;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController implements Initializable {
	@FXML
	TextField username;
	@FXML
	PasswordField password;
	@FXML
	Button btnLogin;

	// Event Listener on Button[#btnLogin].onAction
	@FXML
	public void checkLogin(ActionEvent event) throws IOException {
		String userid = username.getText();
		String passwrd = password.getText();

		String query = "SELECT USERNAME FROM users WHERE username ='"+userid+"' AND password='"+sha256(passwrd)+"';";
		DBConnection db = new DBConnection();
		ResultSet rs;
		
		try {
    		rs = db.connectAndExecute(query,DBConnection.SELECT);

    		if(rs.next()) {
				Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
				Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
				//we get the source of event and cast it to a node
				Scene scene = new Scene(root);
				scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
				stage.setScene(scene);
				stage.show();
			}else {
				Alert a = new Alert(AlertType.ERROR);
				a.setContentText("Incorrect Credentials");
				a.show();
			}	
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
	
	}
	
	public static String sha256(final String base) {
	    try{
	        MessageDigest digest = MessageDigest.getInstance("SHA-256");
	        byte[] hash = digest.digest(base.getBytes(StandardCharsets.UTF_8));
	        // For converting byte array into signum representation   
	        BigInteger sigNum = new BigInteger( 1, hash ) ;   
	        // For converting message digest into hex value   
	        StringBuilder hexString = new StringBuilder( sigNum.toString( 16 ) ) ;   
	        // Pad with leading zeros  
	        while ( hexString.length() < 32 )   
	        {   
	            hexString.insert( 0,  " 0 " ) ;   
	        }   
	        return hexString.toString( ) ;   
	    } catch(Exception ex){
	       throw new RuntimeException(ex);
	    }
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	

}
