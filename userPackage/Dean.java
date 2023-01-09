package userPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

import nonUserPackage.Organization;
import uniSystemPackage.Database;
/**
 * Dean class, creates organization. Not finished
 *
 */
public class Dean extends User{
	private static Dean dean = new Dean("Dean", "Master");
	protected Dean(String firstName, String lastName) {
		super(firstName, lastName);
		this.setUserID();
	}

	private static final long serialVersionUID = -4142893869445496239L;
	
	public static Dean deaning() {
		return dean;
	}
	/**
	 * creates Organization, this method invokes in Student class. Puts student as the head of organization that was send as argument.
	 * @param student
	 */
    public void createOrganization(Student student){
    	Organization o = new Organization(student);
    	Database.accessDB().getOrganizations().add(o);
    	System.out.println("Organization created " + o.getOrgID());
    	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    	System.out.println("Write organization name:");
		String orgName;
		try {
			orgName = in.readLine();
			o.setOrgName(orgName);
			System.out.println("That's all!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void setUserID() {
		this.userID = "DEAN000";
	}
	
	public int hashCode() {
		return Objects.hash(this.firstName, this.lastName, this.userID);
	}
	
	public int compareTo(Dean o) {
		return super.compareTo(o);
	}
	
	public boolean equals(Dean o) {
		return super.equals(o);
	}
	
	public String toString() {
		return super.toString();
	}
}