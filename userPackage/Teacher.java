package userPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

import nonUserPackage.*;
import uniSystemPackage.Database;

public class Teacher extends Employee{
	
	private static final long serialVersionUID = 1051055956846350581L;
	protected Teacher(String firstName, String lastName, LocalDate hireDate) {
		super(firstName, lastName, hireDate);
		this.setUserID();
	}


	protected void setUserID() {
		this.userID = String.valueOf(this.hireDate.getYear() - 2000) + "TEACH0" +String.valueOf(Math.random()*1000+78);
	}
	public void closeAttestaion() throws IOException {
		System.out.println("Choose course by ID");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String courseID = in.readLine();
		Course c = Database.findCoursebyID(courseID);
		if(c.getCourseTeachers().contains(this)) {
//			for(Mark m : Database.getMarks()) {
//				if(m.getCourseID().equalsIgnoreCase(courseID) && m.getTeacherID().equalsIgnoreCase(this.userID)) {
//					m.closeAttestaion();
//				}
//			}
		}
		else {
			System.out.println("You don't have such course");
		}
	}
	
	public void putMark() throws IOException {
		System.out.println("Choose course by ID");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String courseID = in.readLine();
		if(Database.findCoursebyID(courseID).getCourseTeachers().contains(this)) {
			Database.viewStudentsInCourse(courseID, this.userID);
			System.out.println("Choose student by ID");
			String studID = in.readLine();
			Mark m = Database.findStudentbyID(studID).getCoursesAndMarks().get(Database.findCoursebyID(courseID));
			System.out.println("Put student's mark:");
			double point = Double.parseDouble(in.readLine());
			m.setMark(point);
		}
		else {
			System.out.println("You don't have such course");
		}
	}
	public String toString() {
		return super.toString();
	}
//	public boolean equals(Object o) {
//		super.equals(o);
//
//	}
	public static void main(String[] args) {
		Teacher t = new Teacher("M", "Y", LocalDate.now());
		System.out.println(t);
	}
}
