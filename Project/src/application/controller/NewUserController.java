package application.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * AdminController is a class that controls the interactions on the admin page
 * 
 * @author Jonathan Mejia
 * @author Zaquariah Holland
 * @author Joshua Oliveira-Martin
 * 
 * UTSA CS 3443 - Team Project
 * Fall 2020
 *
 */
public class NewUserController {

    @FXML
    private TextField userFullName;

    @FXML
    private TextField userEmail;

    @FXML
    private TextField userPhone;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField userPass;
    
    @FXML
    private Label updateLabel;
    
    /**
     * handles button click to pull inputted fields and create a user
     * @param event
     * @throws IOException
     */
	public void createNewUser(MouseEvent event) throws IOException {
    	String newUserName = userName.getText();
    	String newUserPass = userPass.getText();
		
		if(userFullName.getText().isEmpty() || userEmail.getText().isEmpty() || userPhone.getText().isEmpty() || userName.getText().isEmpty() || userPass.getText().isEmpty()) {
			updateLabel.setText("Fields cannot be left blank");
		} else {
			FileWriter fstream = new FileWriter("data/users.csv", true);
			BufferedWriter out = new BufferedWriter(fstream);
			
			out.write(newUserName + "," + newUserPass);
			out.newLine();
			
			out.close();
			
			Parent mainRoot = FXMLLoader.load(getClass().getResource("/application/view/Login.fxml"));
			Scene mainScene = new Scene(mainRoot);
			mainScene.getStylesheets().add("application/application.css");
			Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
			mainStage.setScene(mainScene);
			mainStage.show();
		}
		
	}
	/**
	 * resolves a button click to return to user login screen "main" 
	 * @param event
	 * @throws IOException
	 */
	public void setMainScene(MouseEvent event)throws IOException {
		
		FXMLLoader loginLoader = new FXMLLoader();
		loginLoader.setLocation(getClass().getResource("/application/view/Login.fxml"));
		
		Parent loginRoot = loginLoader.load();
		Scene loginScene = new Scene(loginRoot);
		loginScene.getStylesheets().add("application/application.css");
		Stage loginStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		loginStage.setScene(loginScene);
		loginStage.show();
	}
}
