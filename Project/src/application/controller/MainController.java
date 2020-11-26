package application.controller;

import java.awt.TextField;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainController {

	@FXML
	private Button submit;
	@FXML
	private Button newUser;
	@FXML
	private TextField userName;
	@FXML
	private TextField password;
	
	public void handle(ActionEvent event) throws IOException
	{

	}
	
	@FXML
	public void checkout(MouseEvent event)throws IOException
	{
		
		
		
		FXMLLoader zoneLoader = new FXMLLoader();
		zoneLoader.setLocation(getClass().getResource("/Checkout.fxml"));
		
		Parent zoneRoot = zoneLoader.load();
		Scene zoneScene = new Scene(zoneRoot);
		
		CheckoutController zoneController = zoneLoader.getController();
		
		
		Stage personnelStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		personnelStage.setScene(zoneScene);
		personnelStage.show();
		
	}
	
	@FXML
	public void newUserControl(MouseEvent event)throws IOException
	{
		
		String zoneCode = newUser.getText();
		
		FXMLLoader zoneLoader = new FXMLLoader();
		zoneLoader.setLocation(getClass().getResource("/Newuser.fxml"));
		
		Parent zoneRoot = zoneLoader.load();
		Scene zoneScene = new Scene(zoneRoot);
		
		NewUserController zoneController = zoneLoader.getController();
		
		
		Stage personnelStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		personnelStage.setScene(zoneScene);
		personnelStage.show();
		
	}
	
}
