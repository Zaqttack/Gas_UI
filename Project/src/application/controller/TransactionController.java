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
import javafx.stage.Stage;

public class TransactionController {

    @FXML
    private Label currentLabel;

    @FXML
    private Button finishButton;
    
    public void initializeTransaction() {
    	finishButton.setVisible(false);
    	currentLabel.setText("Completing Transaction.");
    }

    // method that runs on window open
    public TransactionController() throws InterruptedException {
    	for(int i = 0; i < 10; i++) {
    		Thread.sleep(150);
    		currentLabel.setText("Completing Transaction.");
    		Thread.sleep(150);
//    		currentLabel.setText("Completing Transaction..");
    		Thread.sleep(150);
//    		currentLabel.setText("Completing Transaction...");
    	}
//    	finishButton.setVisible(true);
    }
    
    @FXML
    void finishTransaction(ActionEvent event) throws IOException {

		FXMLLoader checkoutLoader = new FXMLLoader();
		checkoutLoader.setLocation(getClass().getResource("/application/view/Checkout.fxml"));
		
		Parent checkoutRoot = checkoutLoader.load();
		Scene checkoutScene = new Scene(checkoutRoot);
		
		CheckoutController controller = checkoutLoader.getController();
		controller.initializeCheckout();
		
		Stage personnelStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		personnelStage.setScene(checkoutScene);
		personnelStage.show();
    }
}
