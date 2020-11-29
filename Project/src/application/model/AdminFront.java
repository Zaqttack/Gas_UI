package application.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class AdminFront {
	private String adminOrig;
	private String productsFilename;
    private List<Products> items = new ArrayList<Products>();
    
    public AdminFront(String name) {
    	this.adminOrig = name;
    }
    
	public String getAdminOrig() {
		return adminOrig;
	}
	public void setAdminOrig(String adminOrig) {
		this.adminOrig = adminOrig;
	}
	public String getProductsFilename() {
		return productsFilename;
	}
	public void setProductsFilename(String productsFilename) {
		this.productsFilename = productsFilename;
	}
	public List<Products> getItems() {
		return items;
	}
	public void setItems(List<Products> items) {
		this.items = items;
	}

	public void LoadProducts(String filename) throws FileNotFoundException {
		this.productsFilename = filename;
		
    	for(String[] productParts : this.readCsvFile(filename)) {
    		Products p = new Products(productParts[0], productParts[1], productParts[2]);
    		this.items.add(p);
    	}
    }
    
    /**
     * 
     * @param filename the file name
     * @return each line of the read in file
     * @throws FileNotFoundException when the file is not found
     */
    private List<String[]> readCsvFile(String filename) throws FileNotFoundException {
    	File file = new File(filename);
    	
    	try(Scanner scan = new Scanner(file)) {
	    	List<String[]> lines = new LinkedList<>();
	    	
	    	while(scan.hasNext()) {
	    		lines.add(scan.nextLine().split(","));
	    	}
	    	
	    	return lines;
    	}
    }

	public void save() throws FileNotFoundException {
		File file = new File(productsFilename);
		
    	try(PrintWriter output = new PrintWriter(file)) {
    		for(Products p : items) {
    			output.println(p.getCsvString());
    		}
    	}
	}
}
