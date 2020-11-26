package application.controller;

import java.awt.Button;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CheckoutController {
	
	
	@FXML
	private Button admini;
	

	public void handle(ActionEvent event) throws IOException
	{

	}
	
public void setMainScene(MouseEvent event) throws IOException{
		
		Parent mainRoot = FXMLLoader.load(getClass().getResource("/application/view/Login.fxml"));
		Scene mainScene = new Scene(mainRoot);
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		mainStage.setScene(mainScene);
		mainStage.show();
		
	}
	

@FXML
public void admin(MouseEvent event)throws IOException
{
	
	
	FXMLLoader zoneLoader = new FXMLLoader();
	zoneLoader.setLocation(getClass().getResource("/application/view/Administation.fxml"));
	
	Parent zoneRoot = zoneLoader.load();
	Scene zoneScene = new Scene(zoneRoot);
	
	SalesController zoneController = zoneLoader.getController();
	
	Stage personnelStage = (Stage)((Node)event.getSource()).getScene().getWindow();
	personnelStage.setScene(zoneScene);
	personnelStage.show();
}
	
	
}
