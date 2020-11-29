package application.model;

public class Products {
	String productName;
	String productPrice;
	String productID;

	public Products(String name, String price, String ID) {
		this.productName = name;
		this.productPrice = price;
		this.productID = ID;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(String productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductID() {
		return productID;
	}

	public void setProductID(String productID) {
		this.productID = productID;
	}
	
	public String getCsvString() {
		return String.format("%s,%s,%s", productName, productPrice, productID);
	}
}
