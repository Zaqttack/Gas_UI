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

public class AdminLogin {

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

    @FXML
    void adminLogin(ActionEvent event) throws IOException {
		String name = userName.getText();
		String pass = userPassword.getText();
		
		if(name != null && !name.isEmpty() || pass != null && !pass.isEmpty()) {
			if(userName.getText().equals("admin") && userPassword.getText().equals("password123")) {
			FXMLLoader checkoutLoader = new FXMLLoader();
				checkoutLoader.setLocation(getClass().getResource("/application/view/Administation.fxml"));
				
				Parent checkoutRoot = checkoutLoader.load();
				Scene checkoutScene = new Scene(checkoutRoot);
				
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

    @FXML
    void returnToCheckout(ActionEvent event) throws IOException {
		
		Parent mainRoot = FXMLLoader.load(getClass().getResource("/application/view/Checkout.fxml"));
		Scene mainScene = new Scene(mainRoot);
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		mainStage.setScene(mainScene);
		mainStage.show();

    }

}

