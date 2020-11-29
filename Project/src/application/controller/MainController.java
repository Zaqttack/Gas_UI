package application.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainController {

    @FXML
    private Button userLogin;
    @FXML
    private PasswordField userPassword;
    @FXML
    private Label updateLabel;
    @FXML
    private Button newUser;
    @FXML
    private TextField userName;

	public void logUserIn(ActionEvent event)throws IOException {
		String name = userName.getText();
		String pass = userPassword.getText();
		
		if(name != null && !name.isEmpty() || pass != null && !pass.isEmpty()) {
			if(userName.getText().equals("zaqHolland") && userPassword.getText().equals("password")) {
				FXMLLoader checkoutLoader = new FXMLLoader();
				checkoutLoader.setLocation(getClass().getResource("/application/view/Checkout.fxml"));
				
				Parent checkoutRoot = checkoutLoader.load();
				Scene checkoutScene = new Scene(checkoutRoot);
				
				CheckoutController controller = checkoutLoader.getController();
				controller.initializeCheckout();
				
				Stage personnelStage = (Stage)((Node)event.getSource()).getScene().getWindow();
				personnelStage.setScene(checkoutScene);
				personnelStage.show();
			} else {
				updateLabel.setText("Incorrect Infromation");
			}
			
		} else {
			updateLabel.setText("Incorrect Infromation");
		}
	}
	
	public void registerNewUser(ActionEvent event)throws IOException {
		
		FXMLLoader registerLoader = new FXMLLoader();
		registerLoader.setLocation(getClass().getResource("/application/view/Newuser.fxml"));
		
		Parent registerRoot = registerLoader.load();
		Scene registerScene = new Scene(registerRoot);
		
		Stage personnelStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		personnelStage.setScene(registerScene);
		personnelStage.show();
		
	}
	
}
