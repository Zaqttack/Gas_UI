package application.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class User {
	
	private String username;
	private String password;
	private String userFile;
	private String personnelFile;
	private ArrayList<User> login = new ArrayList<>();
	
	public User(String user, String passCode)
	{
		setUser(user);
		setPassword(passCode);	
	}
	public String getUser()
	{
		return username;
	}
	public String getPassword()
	{
		return password;
	}
	public void setUser(String username)
	{
		this.username = username;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}

	public User validate(String username, String password)throws IOException
	{
		this.userFile = "data/users.csv";
		BufferedReader br = null;
	     String line = "";
	     br = new BufferedReader(new FileReader(this.userFile));
	     int lineNum = 0;
	     while((line = br.readLine())!= null)
	     {
	      String[] userAndPassword = line.split(",");
	      if(userAndPassword.length < 2)
	      {
	       br.close();
	                throw new IOException("Error reading csv file.\n");     
	      }
	    	  if(userAndPassword[0].equals(username))
	    	  {
	    		  if(userAndPassword[1].equals(password))
	    		  {
	    			  User newUser = new User(userAndPassword[0],userAndPassword[1]);
	    			  return newUser;
	    		  }  
	    	  }
	     }
	     br.close();
		return null;
	}
	
	
}
