package userPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Objects;

import uniSystemPackage.Database;


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
    	if(!(this instanceof Admin))System.out.println("Login: " + this.getLogin() + " | password: " + this.getPassword());
    	
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
    	Database.viewNews();
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
		return Objects.hash(this.userID);
	}
	public boolean checkPassword(String password) throws IOException{
		while(true) {
			if(this.getPassword().equals(password)) {
				System.out.println("Hello!" + this.firstName + " " + this.lastName);
				return true;
			}
			else {
				BufferedReader in = new BufferedReader( new InputStreamReader(System.in));
				System.out.println("Want to try again?(write 'NO' in anycase to leave)");
				String passwordAgain = in.readLine();
				if (passwordAgain.equalsIgnoreCase("no")) {
					break;
				}
				else {
					if(this.getPassword().equals(passwordAgain)) {
						System.out.println("Hello!" + this.firstName + " " + this.lastName);
						return true;
					}
					else {
						continue;
					}
				}
			}
		}
		return false;
	}
	
	public void userMenu(BufferedReader input) {
		System.out.println("What to do?(type Q to leave)");
	}
	
    public String toString() {
    	return this.userID + " | " + this.firstName + " " + this.lastName;
    }
    
}