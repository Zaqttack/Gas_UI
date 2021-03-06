package application.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import application.model.Merchandise;
import application.model.StoreFront;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * checkoutController is a class that controls the interactions on the checkout page
 * 
 * @author Jonathan Mejia
 * @author Zaquariah Holland
 * @author Joshua Oliveira-Martin
 * 
 * UTSA CS 3443 - Team Project
 * Fall 2020
 *
 */
public class CheckoutController {
	public StoreFront s = new StoreFront("Gas_UI");
	List<Merchandise> merch = s.getItems();
	private int CheckoutCounter = 0;
	private double tax = 8.25;
	double cost;
	double totalTax;
	double totalTotal;
	Alert alert = new Alert(AlertType.NONE);

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
    @FXML
    private Button completeButton;
    
    /**
     * handles loading of data from the merchandise CSV
     * @throws FileNotFoundException
     */
	public CheckoutController() throws FileNotFoundException {
		s.LoadMerchandise("data/merchandise.csv");
	}
	/**
	 * calculation to add up the "cart" and produce a  checkout view string
	 */
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
	/**
	 * string cat: to repopulate the list within merchandise
	 */
	public void repopulateMerchandise() {
		
		String merchandiseStuff;
		for(int i = 0; i <  merch.size(); i++) {
			merchandiseStuff = merch.get(i).getItemName() + " - $" + merch.get(i).getPrice() + " - " + merch.get(i).getCount() + " - " + merch.get(i).getID();
			listOfItemsSearch.getItems().add(i, merchandiseStuff);
		}
	}
	/**
	 * resolves a button click to change the view to adminLogin screen
	 * @param event
	 * @throws IOException
	 */
	@FXML
    void loginAdmin(ActionEvent event) throws IOException {
		
		FXMLLoader zoneLoader = new FXMLLoader();
		zoneLoader.setLocation(getClass().getResource("/application/view/AdminLogin.fxml"));
		
		Parent zoneRoot = zoneLoader.load();
		Scene zoneScene = new Scene(zoneRoot);
		zoneScene.getStylesheets().add("application/application.css");
		Stage personnelStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		personnelStage.setScene(zoneScene);
		personnelStage.show();
    }
	
	/**
	 * resolves a button click to return the user to a login screen
	 * @param event
	 * @throws IOException
	 */
	@FXML
    void returnToHome(ActionEvent event) throws IOException {
		
		Parent mainRoot = FXMLLoader.load(getClass().getResource("/application/view/Login.fxml"));
		Scene mainScene = new Scene(mainRoot);
		mainScene.getStylesheets().add("application/application.css");
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		mainStage.setScene(mainScene);
		mainStage.show();
    }
	/**
	 * resolves a button click to get the item ID and add it to the "cart" list
	 * @param event
	 * @throws FileNotFoundException
	 */
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
	/**
	 * resolves a button click to complete the checkout and show transation complete
	 * @param event
	 * @throws InterruptedException
	 */
    @FXML
    void completeTransaction(ActionEvent event) throws InterruptedException {
    	
    	alert.setAlertType(AlertType.INFORMATION);
    	alert.setTitle("CHECKOUT");
    	alert.setHeaderText("COMPLETING CHECKOUT");
    	alert.setContentText("To complete checkout click OK");
    	alert.setResizable(false);
    	alert.show();
		
    	Thread.sleep(1000);
    	taxLabel.setText("$0.00");
    	totalLabel.setText("$0.00");
    	listOfItemsView.getItems().clear();
    	listOfItemsSearch.getItems().clear();
    	initializeCheckout();
    }
}
