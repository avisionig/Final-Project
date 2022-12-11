package userPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;


public abstract class User implements Comparable<User>, Serializable, Cloneable {
    
	private static final long serialVersionUID = 181327650330927735L;

	protected String userID;
	protected String login;
    protected String password;
    protected String firstName;
    protected String lastName;
    
    protected User(String firstName, String lastName){
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.login = (firstName.charAt(0) + "_" + lastName).toLowerCase();
    	this.password = (firstName + lastName).toLowerCase();
    	
    }
    abstract void setUserID(); 
    
    public String getUserID() {
        return this.userID;
    }

    public String getLogin() {
        return this.login;
    }
    public String getPassword(){
    	return this.password;
    }
    
//    private void setPassword() {
//    	System.out.print("Type for " + this.login + " user:");
//        try{
//        	BufferedReader passReader = new BufferedReader( new InputStreamReader(System.in));
//        	String password = passReader.readLine();
//        	this.password = password;
//        	System.out.println("Password is set");
//        	passReader.close();}
//        catch(IOException ioe) {
//        	System.out.println("Something is wrong!");
//        }
//    }
    public void changePassword() {
    	System.out.print("Type old password:");
        try{
        	BufferedReader passReader = new BufferedReader( new InputStreamReader(System.in));
        	String password = passReader.readLine();
        	if (this.password.equals(password)) {
        		System.out.print("Correct!" + "\r\n" + "Type new password:");
        		this.password = passReader.readLine();
        		System.out.println("Password is set");
        	}
        	else { 
        		System.out.println("Incorrect!");
        	}
        	passReader.close();}
        catch(IOException ioe) {
        	System.out.println("Something is wrong!");
        	}
        
    }
    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void viewNews() {
        
    }
	@Override
	public int compareTo(User o) {
		if (this.firstName.compareToIgnoreCase(o.firstName) == 1) return 1;
		else if (this.firstName.compareToIgnoreCase(o.firstName) == 0 && this.lastName.compareToIgnoreCase(o.lastName) == 1)return 1;
		else if (this.firstName.compareToIgnoreCase(o.firstName) == -1)return -1;
		else if (this.firstName.compareToIgnoreCase(o.firstName) == 0 && this.lastName.compareToIgnoreCase(o.lastName) == -1) return -1;
		return 0;
	}
	public boolean equals(Object o) {
		if(o == null) return false;
		User u = (User)o;
		return this.firstName.equals(u.firstName) && this.lastName.equals(u.lastName) && this.userID.equals(u.userID);
	}
	public int hashCode() {
		return this.userID.hashCode();
	}
    public String toString() {
    	return this.firstName + " " + this.lastName + " " + this.userID;
    }
    
}