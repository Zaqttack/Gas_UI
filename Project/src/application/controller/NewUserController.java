package application.controller;

import java.awt.Button;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class NewUserController {

	@FXML
	private Button submit1;
	
	@FXML
	private Button cancel;
	
	public void setMainScene(MouseEvent event) throws IOException {
		
		Parent mainRoot = FXMLLoader.load(getClass().getResource("/application/view/Login.fxml"));
		Scene mainScene = new Scene(mainRoot);
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		mainStage.setScene(mainScene);
		mainStage.show();
		
	}

	public void checkout(MouseEvent event)throws IOException {
		
		FXMLLoader loginLoader = new FXMLLoader();
		loginLoader.setLocation(getClass().getResource("/application/view/Login.fxml"));
		
		Parent loginRoot = loginLoader.load();
		Scene loginScene = new Scene(loginRoot);
		
		Stage loginStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		loginStage.setScene(loginScene);
		loginStage.show();
	}
}
