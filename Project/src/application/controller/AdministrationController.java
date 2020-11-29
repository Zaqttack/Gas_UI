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
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AdministrationController {
	
	public void setMainScene(MouseEvent event) throws IOException{
		
		Parent mainRoot = FXMLLoader.load(getClass().getResource("/application/view/AdminLogin.fxml"));
		Scene mainScene = new Scene(mainRoot);
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		mainStage.setScene(mainScene);
		mainStage.show();
		
	}
	
	@FXML
    private Label totalInventoryLabel;

    @FXML
    private Label dailyProfitsLabel;

    @FXML
    private ListView<?> productListView;

    @FXML
    private TextField buyQuantity;

    @FXML
    private RadioButton radioButton25;

    @FXML
    private Label weeklyTaxesLabel;

    @FXML
    private Button buyButton;

    @FXML
    private RadioButton radioButton50;

    @FXML
    private RadioButton radioButton75;

    @FXML
    private Label weeklyProfitsLabel;

    @FXML
    private Button logoffButton;

    @FXML
    private ToggleGroup markupPercent;

    @FXML
    private Label totalProfitsLabel;

    @FXML
    private TextField buyID;

    @FXML
    private Label dailyTaxesLabel;

    @FXML
    void adminLogoff(ActionEvent event) {

    }

    @FXML
    void setMainScene(ActionEvent event) {

    }

    @FXML
    void buyItem(ActionEvent event) {
    	
    }
}
