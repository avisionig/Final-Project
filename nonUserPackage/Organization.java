package nonUserPackage;

import userPackage.*;

public class Organization {
    private Student head;
    private String orgID;
    public Organization(Student head) {
        this.head = head;
        this.setOrgID();
    }
    private void setOrgID() {
    	this.orgID = "ORG" + this.head.getFaculty().name() + " " + String.valueOf(Math.random()*100+14);
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
}
