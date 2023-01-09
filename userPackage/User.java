
package userPackage;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Objects;
import java.util.StringTokenizer;

import uniSystemPackage.Database;

/**
 * User is abstract class that is base for all user classes.
 * @author Ayan, Amir, Serikbolsyn and Dinmukhamed.
 *
 */
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
    	if(!(this instanceof Admin) && !(this instanceof ResearchDecorator) && !(this instanceof Librarian) && !(this instanceof Dean))System.out.println("Login: " + this.getLogin() + " | password: " + this.getPassword());
    	
    }
    /**
     * Method that creates ID for User, it is overridden in child classes of User
     */
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
    /**
     * Method that changes password, actually it's not test so, don't know if it works
     */
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
    /**
     * To ensure that there won't be no input so NullPointer won't show. Forces user to write login and password in case if not written.
     * @param input
     * @param loginAndPassword
     * @return login and password
     */
    public static String loginToAccount(BufferedReader input, String loginAndPassword) {
    	try {
    		StringTokenizer st = new StringTokenizer(loginAndPassword);
    		String login;
    		if(!st.hasMoreElements()) {
				System.out.print("Write login:");
				login = input.readLine();
			}
    		else { 
    			login = st.nextToken();
    		}
			String password;
			if(!st.hasMoreElements()) {
				System.out.print("Write password:");
				password = input.readLine();
			}
			else {
				password = st.nextToken();
			}
			return login + " " + password;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }
    /**
     * Method to see news.
     */
    public void viewNews() {
    	Database.accessDB().viewNews();
    }
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
	/**
	 * This method was written so there weren't be repeated code in UniSystem class
	 * @param password
	 * @return boolean to login to user account
	 * @throws IOException
	 */
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
	/**
	 * Method that will be invoked in UniSystem class, shows what user can do.
	 * @param input
	 * @see uniSystemPackage.UniSystem
	 */
	public void userMenu(BufferedReader input) {
		System.out.println("What to do?(type Q to leave)");
	}
	
	
    public String toString() {
    	return this.userID + " | " + this.firstName + " " + this.lastName;
    }
    
}