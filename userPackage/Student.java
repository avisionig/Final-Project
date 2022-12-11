package userPackage;

import java.time.LocalDate;

import nonUserPackage.Faculty;
import nonUserPackage.Mark;
import nonUserPackage.StudentDegree;

public class Student extends User{
	private static int studNum = 0;
	private static final long serialVersionUID = 7227165493952096441L;
	protected int yearOfEducation = 1;
	protected Faculty faculty;
	protected LocalDate yearOfAdmission;
	protected double feeStudy;
	protected StudentDegree degree;{
    	studNum++;
    }
	protected Student(String firstName, String lastName, LocalDate yearOfAdmission) {
		super(firstName, lastName);
		this.yearOfAdmission = yearOfAdmission;
		
	}
	protected void setUserID() {
		this.userID = String.valueOf(this.yearOfAdmission.getYear() - 2000) + "STUD" + String.valueOf(studNum);
	}
	public Faculty getFaculty() {
		return this.faculty;
	} 
	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public LocalDate getYearOfAdmission() {
		 return this.yearOfAdmission;
	}

	public double getFeeStudy() {
		 return this.feeStudy;
	}

	public StudentDegree getDegree() {
		 return this.degree;
	}
	public int getYearOfEducation() {
		return this.yearOfEducation;
	}
	public Mark getMarks() {
		 return null;
	}
		    
		    
}