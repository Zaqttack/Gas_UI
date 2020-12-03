package application.controller;
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
 
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import application.model.AdminFront;
import application.model.Merchandise;
import application.model.Products;
import application.model.Sales;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class AdministrationController {
	public AdminFront admin = new AdminFront("AminOrigin");
	public StoreFront store = new StoreFront("Gas_UI");
	List<Products> product = admin.getItems();
	List<Sales> sales = admin.getSales();
	List<Merchandise> merch = store.getItems();
	Double totalSales = 0.0;
	int totalItems;

    @FXML
    private Button logoffButton;
    @FXML
    private Label dailyProfitsLabel;
    @FXML
    private Label dailyTaxesLabel;
    @FXML
    private Label weeklyProfitsLabel;
    @FXML
    private Label weeklyTaxesLabel;
	@FXML
    private Label totalInventoryLabel;
    @FXML
    private Label totalProfitsLabel;
    @FXML
    private ListView<String> productListView;
    @FXML
    private TextField buyQuantity;
    @FXML
    private TextField buyID;
    @FXML
    private RadioButton radioButton25;
    @FXML
    private RadioButton radioButton50;
    @FXML
    private RadioButton radioButton75;
    @FXML
    private ToggleGroup markupPercent;
    @FXML
    private Button buyButton;
    @FXML
    private Label boughtLabel;
    /**	
     * loads the data into the admin user from the data CSV files
     * @throws FileNotFoundException
     */
	public AdministrationController() throws FileNotFoundException {
		admin.LoadProducts("data/products.csv");
		admin.LoadSales("data/sales.csv");
		store.LoadMerchandise("data/merchandise.csv");
	}
    /**
     * Initializes all the data on the admin page
     * 
     */
    public void initializeAdministration() {
    	int i = 0;
    	totalItems = 0;
    	
    	totalSales += Double.parseDouble(sales.get(0).getDailySales());
    	totalSales += Double.parseDouble(sales.get(0).getDailyTaxes());
    	totalSales += Double.parseDouble(sales.get(0).getWeeklySales());
    	totalSales += Double.parseDouble(sales.get(0).getWeeklyTaxes());

    	dailyProfitsLabel.setText("$" + sales.get(0).getDailySales());
    	dailyTaxesLabel.setText("$" + sales.get(0).getDailyTaxes());
    	weeklyProfitsLabel.setText("$" + sales.get(0).getWeeklySales());
    	weeklyTaxesLabel.setText("$" + sales.get(0).getWeeklyTaxes());
    	totalProfitsLabel.setText("$" + String.valueOf(totalSales));
    	
    	for(i = 0; i < merch.size(); i++) {
    		totalItems += Integer.parseInt(merch.get(i).getCount());
    	}
    	totalInventoryLabel.setText(String.valueOf(totalItems));
		
		String productStuff;
		for(i = 0; i <  product.size(); i++) {
			productStuff = product.get(i).getProductName() + " - $" + product.get(i).getProductPrice() + " - " + product.get(i).getProductID();
			productListView.getItems().add(i, productStuff);
		}
    }
	/**
	 * handles a mouse click resolved onto a button to return to admin login screen
	 * @param event
	 * @throws IOException
	 */
    @FXML
    void returnToAdminLogin(ActionEvent event) throws IOException {
		
		Parent mainRoot = FXMLLoader.load(getClass().getResource("/application/view/AdminLogin.fxml"));
		Scene mainScene = new Scene(mainRoot);
		mainScene.getStylesheets().add("application/application.css");
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		mainStage.setScene(mainScene);
		mainStage.show();
    }
    /**
     * the admin can purchace and add items to inventory with the proposed markup from item ID (on click of button pulls info)
     * @param event
     * @throws FileNotFoundException
     */
    @FXML
    void buyItem(ActionEvent event) throws FileNotFoundException {
    	String ID = buyID.getText();
    	String quantity = buyQuantity.getText();
    	Double value = 0.0;
    	Merchandise m = null;
    	String price = "";

		if(ID != null && !ID.isEmpty() || quantity != null && !quantity.isEmpty()) {
			// determine what kind of mark up to add to the product
			for(int i = 0; i < product.size(); i++) {
        		if(product.get(i).getProductID().equals(ID)) {
        			
                	// convert strings -> doubles
                	value = Double.parseDouble(product.get(i).getProductPrice());
                	
            		if(radioButton25.isSelected()) {
            			value += ( value * .25 );
            			price = String.format("%.2f", value);
                	}
            		else if(radioButton50.isSelected()) {
            			value += ( value * .50 );
            			price = String.format("%.2f", value);
                	}
            		else if(radioButton75.isSelected()) {
            			value += ( value * .75 );
            			price = String.format("%.2f", value);
                	}
            		m = new Merchandise(product.get(i).getProductName(), price, product.get(i).getProductID(), quantity);
            		boughtLabel.setText("Successfuly purchased " + quantity + " " + product.get(i).getProductName() + "'s");
            		break;
        		}
    		}
	    	store.addItem(m);
	    	store.save();
    	}
    	
    	radioButton25.setSelected(true);
    	buyID.clear();
    	buyQuantity.clear();
    }
    
}
