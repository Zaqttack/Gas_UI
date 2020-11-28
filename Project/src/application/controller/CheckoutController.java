package application.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import application.model.StoreFront;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CheckoutController {
	public StoreFront s = new StoreFront("Gas_UI");

    @FXML
    private TextField removeItem;
    @FXML
    private TextField removeQuantity;
    @FXML
    private Button searchButton;
    @FXML
    private ListView<?> listOfItemsView;
    @FXML
    private Button removeButton;
    @FXML
    private Button adminPanelButton;
    @FXML
    private TextField addID;
    @FXML
    private TextField addQuantity;
    @FXML
    private Button addButton;
    @FXML
    private TextField searchTextField;
    @FXML
    private Button logOffButton;
    @FXML
    private ListView<?> listOfItemsSearch;

	public CheckoutController() throws FileNotFoundException {
		s.LoadMerchandise("data/merchandise.csv");
	}

    void loginAdmin(ActionEvent event) throws IOException {
		
		FXMLLoader zoneLoader = new FXMLLoader();
		zoneLoader.setLocation(getClass().getResource("/application/view/AdminLogin.fxml"));
		
		Parent zoneRoot = zoneLoader.load();
		Scene zoneScene = new Scene(zoneRoot);
		
		Stage personnelStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		personnelStage.setScene(zoneScene);
		personnelStage.show();

    }

    void returnToHome(ActionEvent event) throws IOException {
		
		Parent mainRoot = FXMLLoader.load(getClass().getResource("/application/view/Login.fxml"));
		Scene mainScene = new Scene(mainRoot);
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		mainStage.setScene(mainScene);
		mainStage.show();

    }

    void searchItem(ActionEvent event) {

    }

    void addItem(ActionEvent event) {

    }

    void removeItem(ActionEvent event) {

    }
}
