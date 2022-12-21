package userPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

import nonUserPackage.Organization;
import uniSystemPackage.Database;

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
    public void createOrganization(Student s){
    	Organization o = new Organization(s);
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