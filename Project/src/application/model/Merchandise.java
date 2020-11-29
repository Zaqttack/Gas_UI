package application.model;

public class Merchandise {
	String itemName;
	String price;
	String ID;
	String count;

	public Merchandise(String itemName, String price, String ID, String count) {
		this.itemName = itemName;
		this.price = price;
		this.ID = ID;
		this.count = count;
	}
	
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
	public String getCsvString() {
		return String.format("%s,%s,%s,%s", itemName, price, ID, count);
	}
	
}
