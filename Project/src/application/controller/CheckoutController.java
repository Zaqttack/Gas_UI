package application.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import application.model.Merchandise;
import application.model.StoreFront;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CheckoutController {
	public StoreFront s = new StoreFront("Gas_UI");
	List<Merchandise> merch = s.getItems();
	private int CheckoutCounter = 0;
	private double tax = 8.25;
	double cost;
	double totalTax;
	double totalTotal;

    @FXML
    private ListView<String> listOfItemsView;
    @FXML
    private Label taxLabel;
    @FXML
    private Label totalLabel;
    @FXML
    private Button adminPanelButton;
    @FXML
    private TextField addID;
    @FXML
    private TextField addQuantity;
    @FXML
    private Button addButton;
    @FXML
    private Button logOffButton;
    @FXML
    private ListView<String> listOfItemsSearch;

	public CheckoutController() throws FileNotFoundException {
		s.LoadMerchandise("data/merchandise.csv");
	}
	
	public void initializeCheckout() {
		cost = 0;
		totalTax = 0;
		totalTotal = 0;
		
		String merchandiseStuff;
		for(int i = 0; i <  merch.size(); i++) {
			merchandiseStuff = merch.get(i).getItemName() + " - $" + merch.get(i).getPrice() + " - " + merch.get(i).getCount() + " - " + merch.get(i).getID();
			listOfItemsSearch.getItems().add(i, merchandiseStuff);
		}
	}
	
	public void repopulateMerchandise() {
		
		String merchandiseStuff;
		for(int i = 0; i <  merch.size(); i++) {
			merchandiseStuff = merch.get(i).getItemName() + " - $" + merch.get(i).getPrice() + " - " + merch.get(i).getCount() + " - " + merch.get(i).getID();
			listOfItemsSearch.getItems().add(i, merchandiseStuff);
		}
	}

	@FXML
    void loginAdmin(ActionEvent event) throws IOException {
		
		FXMLLoader zoneLoader = new FXMLLoader();
		zoneLoader.setLocation(getClass().getResource("/application/view/AdminLogin.fxml"));
		
		Parent zoneRoot = zoneLoader.load();
		Scene zoneScene = new Scene(zoneRoot);
		
		Stage personnelStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		personnelStage.setScene(zoneScene);
		personnelStage.show();
    }

	@FXML
    void returnToHome(ActionEvent event) throws IOException {
		
		Parent mainRoot = FXMLLoader.load(getClass().getResource("/application/view/Login.fxml"));
		Scene mainScene = new Scene(mainRoot);
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		mainStage.setScene(mainScene);
		mainStage.show();
    }

	@FXML
    void addItem(ActionEvent event) throws FileNotFoundException {
		String ID = addID.getText();
		String quantity = addQuantity.getText();
		double iQuantity = Double.parseDouble(quantity);
		double maxCount = 0;
		String price;
		double priceValue;
		double currTax;
		
		// acquiring the item to checkout with
		String checkoutList = "";
		for(int i = 0; i < merch.size(); i++) {
			
			//only adding item if the item is found
			if(ID.equals(merch.get(i).getID()) && iQuantity > 0) {
				
				maxCount = Double.parseDouble(merch.get(i).getCount());
				if(iQuantity >= maxCount) {
					iQuantity = maxCount;
				}
				// converting a string to a double
				price = merch.get(i).getPrice();
				priceValue = Double.parseDouble(price);
				cost = ( priceValue * iQuantity );
				price = String.format("%.2f", cost);
				
				// adding item to the total value of checkout
				checkoutList = merch.get(i).getItemName() + ": $" + price;
				listOfItemsView.getItems().add(CheckoutCounter++, checkoutList);
				
				// update tax
				currTax = ( (cost * tax) / 100 );
				totalTax += currTax;
				price = String.format("%.2f", totalTax);
				taxLabel.setText("$" + price);
				
				// update total
				totalTotal += cost + currTax;
				price = String.format("%.2f", totalTotal);
				totalLabel.setText("$" + price);

				// subtract quantity from merchandise.csv
				price = String.format("%s", (int)maxCount - (int)iQuantity);
				merch.get(i).setCount(price);
				if( (maxCount - iQuantity) <= 0)
					s.removeItem(merch.get(i));
				
				listOfItemsSearch.getItems().clear();
				repopulateMerchandise();
				s.save();
				break;
			}
		}
    }
}
