package userPackage;

import java.time.LocalDate;
import nonUserPackage.Faculty;

public class Dean extends Employee{

	private static final long serialVersionUID = -4142893869445496239L;
	private Faculty faculty;

    public Dean(String firstName, String lastName, LocalDate hireDate, Faculty faculty) {
        super(firstName, lastName, hireDate);
        this.faculty = faculty;
        this.setUserID();
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public void signRequests(){}
    public void approveOrganization(){}
    public void createOrganization(){}

	@Override
	protected void setUserID() {
		this.userID = "DEAN0" + this.faculty.name();
	}

}