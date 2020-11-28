package application.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class StoreFront {
	private String parkName;
    private List<Merchandise> items = new ArrayList<Merchandise>();
    
    public StoreFront(String parkName) {
    	this.parkName = parkName;
    }
    
    public void LoadMerchandise(String filename) throws FileNotFoundException {
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
    	
    	//let try with resources handling closing the scanner for us - best practice
    	try(Scanner scan = new Scanner(file)) 
    	{
	    	List<String[]> lines = new LinkedList<>();
	    	
	    	while(scan.hasNext()) {
	    		lines.add(scan.nextLine().split(","));
	    	}
	    	
	    	return lines;
    	}
    }
}
