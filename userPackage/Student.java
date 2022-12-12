package userPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.HashMap;

import nonUserPackage.*;
import nonUserPackage.StudentDegree;
import uniSystemPackage.Database;

public class Student extends User{
	private static int studNum = 0;
	private static final long serialVersionUID = 7227165493952096441L;
	protected int yearOfEducation = 1;
	protected Faculty faculty = null;
	protected LocalDate yearOfAdmission;
	protected double feeStudy;
	protected StudentDegree degree;
	protected HashMap<Course, Mark> coursesAndMarks;
	protected Mark studMarks;
	{
    	studNum++;
    }
	protected Student(String firstName, String lastName, LocalDate yearOfAdmission,Faculty faculty) {
		super(firstName, lastName);
		this.yearOfAdmission = yearOfAdmission;
		this.faculty = faculty;
		this.setUserID();
		
	}
	protected void setUserID() {
		this.userID = String.valueOf(this.yearOfAdmission.getYear() - 2000) + "STUD0" + String.valueOf(this.faculty.ordinal() + 1) +String.valueOf(studNum);
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
	public Request registerTo() throws IOException {
		String courseID;
		System.out.println("Choose course:");
		Database.viewAllCourses();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Write courseID:");
		courseID = in.readLine();
		if (Database.findCoursebyID(courseID) != null) return new Request(courseID, this, RequestType.values()[0]);
		else {
			while(true) {
				System.out.println("Incorrect course ID, try again?\1.Yes\2.No");
				int action = Integer.parseInt(in.readLine());
				if(action == 2) {
					break;
				}
				else {
					courseID = in.readLine();
					if (Database.findCoursebyID(courseID) != null) return new Request(courseID, this, RequestType.values()[0]);
				}
			}
		}
		return null;
	}
	public boolean equals(Object o) {
		if (!super.equals(o)) return false;
		Student s = (Student)o;
		return this.yearOfEducation == s.yearOfEducation && this.faculty == s.faculty;
	}
	public String toString() {
		return super.toString() + " "+ this.faculty.name() +" year of Education " + this.yearOfEducation + " year of admission " + this.yearOfAdmission; 
	}
	public static void main(String[] args) {
		Student a = new Student("Ayan", "Igali", LocalDate.now(), Faculty.FIT);
		System.out.println(a);
	}
}