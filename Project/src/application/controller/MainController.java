package application.controller;

import java.io.IOException;

import application.model.User;
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

/**
 * MainController is a class that controls the interactions on the main login page
 * 
 * @author Jonathan Mejia
 * @author Zaquariah Holland
 * @author Joshua Oliveira-Martin
 * 
 * UTSA CS 3443 - Team Project
 * Fall 2020
 *
 */
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
    
    /**
     * resolves a button click to handle a user login from the inputted information
     * @param event
     * @throws IOException
     */
	public void logUserIn(ActionEvent event)throws IOException {
		String name = userName.getText();
		String pass = userPassword.getText();
		Boolean isUser = false;
		
		User u = new User(name, pass);
		isUser = u.validate(name, pass);
		
		if( isUser == true ) {
			FXMLLoader checkoutLoader = new FXMLLoader();
			checkoutLoader.setLocation(getClass().getResource("/application/view/Checkout.fxml"));
			
			Parent checkoutRoot = checkoutLoader.load();
			Scene checkoutScene = new Scene(checkoutRoot);
			checkoutScene.getStylesheets().add("application/application.css");
			CheckoutController controller = checkoutLoader.getController();
			controller.initializeCheckout();
			
			Stage personnelStage = (Stage)((Node)event.getSource()).getScene().getWindow();
			personnelStage.setScene(checkoutScene);
			personnelStage.show();
		} else {
			updateLabel.setText("Incorrect Information");
		}
	}
	
	/**
	 * handles button click to change views from login page to register user page
	 * @param event
	 * @throws IOException
	 */
	public void registerNewUser(ActionEvent event)throws IOException {
		
		FXMLLoader registerLoader = new FXMLLoader();
		registerLoader.setLocation(getClass().getResource("/application/view/Newuser.fxml"));
		
		Parent registerRoot = registerLoader.load();
		Scene registerScene = new Scene(registerRoot);
		Stage personnelStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		registerScene.getStylesheets().add("application/application.css");
		personnelStage.setScene(registerScene);
		personnelStage.show();
		
	}
	
}
