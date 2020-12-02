package application.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class User {
	private String username;
	private String password;
	
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
	
	public boolean validate(String username, String password) {
		
		String file = "data/users.csv";
		BufferedReader br = null;
		String line = "";
		
		try {
			br = new BufferedReader(new FileReader(file));
			
			while( (line = br.readLine()) != null ) {
				String[] user = line.split(",");
				
				if(username.equals(user[0]) && password.equals(user[1])) {
					return true;
				}
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return false;
	}
}
