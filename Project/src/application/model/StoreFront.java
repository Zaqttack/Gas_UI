package application.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class StoreFront {
	private String parkName;
	private String merchandiseFilename;
    private List<Merchandise> items = new ArrayList<Merchandise>();
    
    public StoreFront(String parkName) {
    	this.parkName = parkName;
    }
    
    public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public List<Merchandise> getItems() {
		return items;
	}

	public void setItems(List<Merchandise> items) {
		this.items = items;
	}

	public void LoadMerchandise(String filename) throws FileNotFoundException {
		this.merchandiseFilename = filename;
		
    	for(String[] merchParts : this.readCsvFile(filename)) {
    		Merchandise m = new Merchandise(merchParts[0], merchParts[1], merchParts[2], merchParts[3]);
    		this.items.add(m);
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
    
    public void removeItem(Merchandise m) {
		items.remove(m);
	}

	public void save() throws FileNotFoundException {
		File file = new File(merchandiseFilename);
		
    	try(PrintWriter output = new PrintWriter(file)) {
    		for(Merchandise m : items) {
    			output.println(m.getCsvString());
    		}
    	}
	}
}
