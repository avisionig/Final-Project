package userPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;

import nonUserPackage.*;
import uniSystemPackage.Database;

public class Teacher extends Employee{
	
	private static final long serialVersionUID = 1051055956846350581L;
	private static int teacherNum = 0;
	protected Teacher(String firstName, String lastName, LocalDate hireDate) {
		super(firstName, lastName, hireDate);
		this.setUserID();
	}
	
	{
		teacherNum++;
	}

	protected void setUserID() {
		this.userID = String.valueOf(this.hireDate.getYear() - 2000) + "TEACH0" +String.valueOf(teacherNum);
	}
	public void closeAttestaion() throws IOException {
		System.out.println("Choose course by ID");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String courseID = in.readLine();
		Course c = Database.findCoursebyID(courseID);
		if(c.getCourseTeachers().contains(this)) {
			for(Mark m : Database.getMarks()) {
				if(m.getCourseID().equalsIgnoreCase(courseID) && m.getTeacherID().equalsIgnoreCase(this.userID)) {
					m.closeAttestaion();
				}
			}
		}
		else {
			System.out.println("You don't have such course");
		}
		in.close();
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
		in.close();
	}
	public String toString() {
		return super.toString();
	}
	
}
