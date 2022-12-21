package nonUserPackage;

import java.io.Serializable;

import userPackage.*;

public class Organization implements Serializable{

	private static final long serialVersionUID = 1775228474882123168L;
	private Student head;
	private String orgName;
    private String orgID;
    public Organization(Student head) {
        this.head = head;
        this.setOrgID();
    }
    private void setOrgID() {
    	this.orgID = "ORG" + this.head.getFaculty().name() + String.valueOf((int)(Math.random()*100+14));
    }
    public Student getHead() {
        return head;
    }
    public String getOrgID() {
    	return this.orgID;
    }
    public void setHead(Student head) {
        this.head = head;
    }
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
