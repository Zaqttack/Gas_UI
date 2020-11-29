package application.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import application.model.AdminFront;
import application.model.Merchandise;
import application.model.Products;
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
	public AdminFront a = new AdminFront("AminOrigin");
	public StoreFront s = new StoreFront("Gas_UI");
	List<Products> product = a.getItems();
	
	@FXML
    private Label totalInventoryLabel;
    @FXML
    private Label dailyProfitsLabel;
    @FXML
    private ListView<String> productListView;
    @FXML
    private TextField buyQuantity;
    @FXML
    private Label weeklyTaxesLabel;
    @FXML
    private Label weeklyProfitsLabel;
    @FXML
    private Button logoffButton;
    @FXML
    private Label totalProfitsLabel;
    @FXML
    private TextField buyID;
    @FXML
    private Label dailyTaxesLabel;
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
//    @FXML
//    private Button boughtLabel;

	public AdministrationController() throws FileNotFoundException {
		a.LoadProducts("data/products.csv");
		s.LoadMerchandise("data/merchandise.csv");
	}
    
    public void initializeAdministration() {
		
		String productStuff;
		for(int i = 0; i <  product.size(); i++) {
			productStuff = product.get(i).getProductName() + " - $" + product.get(i).getProductPrice() + " - " + product.get(i).getProductID();
			productListView.getItems().add(i, productStuff);
		}
    }

    @FXML
    void returnToAdminLogin(ActionEvent event) throws IOException {
		
		Parent mainRoot = FXMLLoader.load(getClass().getResource("/application/view/AdminLogin.fxml"));
		Scene mainScene = new Scene(mainRoot);
		Stage mainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
		mainStage.setScene(mainScene);
		mainStage.show();
    }

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
            		
//            		boughtLabel.setText("Successfully purchased " + quantity + " of " + product.get(i).getProductName());
            		break;
        		}
    		}
	    	s.addItem(m);
	    	s.save();
    	}
    	
    	radioButton25.setSelected(true);
    	buyID.clear();
    	buyQuantity.clear();
    }
}
