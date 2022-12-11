package models.employee;

import models.student.Faculty;

import java.util.Date;

public class Dean extends Employee{
    private Faculty faculty;

    public Dean(String userId, String login, String password, String firstName, String lastName, double salary, Date hireDate, Faculty faculty) {
        super(userId, login, password, firstName, lastName, salary, hireDate);
        this.faculty = faculty;
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

}
