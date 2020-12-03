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


/**
 * AdminLoginController is a class that controls the interactions on the admin login page
 * 
 * @author Jonathan Mejia
 * @author Zaquariah Holland
 * @author Joshua Oliveira-Martin
 * 
 * UTSA CS 3443 - Team Project
 * Fall 2020
 *
 */
public class AdminLoginController {

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
     * handles the user inputed text form boxes and verifies match with admin passwords 
     * @param event
     * @throws IOException
     */
    @FXML
    void adminLogin(ActionEvent event) throws IOException {
		String name = userName.getText();
		String pass = userPassword.getText();
		
		if(name != null && !name.isEmpty() || pass != null && !pass.isEmpty()) {
			if(userName.getText().equals("admin") && userPassword.getText().equals("password123")) {
			FXMLLoader checkoutLoader = new FXMLLoader();
				checkoutLoader.setLocation(getClass().getResource("/application/view/Administration.fxml"));
				
				Parent checkoutRoot = checkoutLoader.load();
				Scene checkoutScene = new Scene(checkoutRoot);
				checkoutScene.getStylesheets().add("application/application.css");
				AdministrationController controller = checkoutLoader.getController();
				controller.initializeAdministration();
				
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
    /**
     * handles the button click to return the user from adminlogin back to user checkout page
     * @param event
     * @throws IOException
     */
    @FXML
    void returnToCheckout(ActionEvent event) throws IOException {
    	
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

    }

}

