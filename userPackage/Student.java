package userPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.HashMap;

import nonUserPackage.*;
import uniSystemPackage.Database;

public class Student extends User{
	private static final long serialVersionUID = 7227165493952096441L;
	protected int yearOfEducation = 1;
	protected Faculty faculty = null;
	protected LocalDate yearOfAdmission;
	protected double feeStudy;
	protected StudentDegree degree;
	protected HashMap<Course, Mark> coursesAndMarks;
	protected Mark studMarks;
	protected Schedule schedule;
	
	{
		schedule = new Schedule();
    	coursesAndMarks = new HashMap<Course,Mark>();
    }
	public Student(String firstName, String lastName, LocalDate yearOfAdmission,Faculty faculty) {
		super(firstName, lastName);
		this.yearOfAdmission = yearOfAdmission;
		this.faculty = faculty;
		this.setUserID();
		
	}
	protected void setUserID() {
		this.userID = String.valueOf(this.yearOfAdmission.getYear() - 2000) + "STUD0" + String.valueOf(this.faculty.ordinal() + 1) +String.valueOf((int)(Math.random()*1000+78));
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
	public HashMap<Course, Mark> getCoursesAndMarks(){
		return this.coursesAndMarks;
	}
	public Schedule getSchedule() {
		return this.schedule;
	}
	public void viewSchedule() {
		System.out.println(this.schedule);
	}
	public Request registerTo() throws IOException {
		String courseID;
		System.out.println("Choose course:");
		Database.viewAllCourses();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Write courseID:");
		courseID = in.readLine();
		Course c = Database.findCoursebyID(courseID);
		if(c == null) {
			while(true) {
				System.out.println("Incorrect course ID, try again?\1.Yes\2.No");
				int action = Integer.parseInt(in.readLine());
				if(action == 2) {
					return null;
				}
				else {
					courseID = in.readLine();
					c = Database.findCoursebyID(courseID);
					if ( c != null) {
						break;
					}
				}
			}
		}
		c.viewCourseTeachers();
		System.out.println("Which teacher you want to choose:");
		String teacherID = in.readLine();
		Teacher t = Database.findTeacherByID(teacherID);
		if(t == null) {
			while(true) {
				System.out.println("Incorrect teacher ID, try again?\1.Yes\2.No");
				int action = Integer.parseInt(in.readLine());
				if(action == 2) {
					return null;
				}
				else {
					teacherID = in.readLine();
					t = Database.findTeacherByID(teacherID);
					if ( t != null) {
						break;
					}
				}
			}
		}
		System.out.println("Choose teachers lesson:");
		t.viewSchedule();
		System.out.print("Write lesson time(format 00:00), lesson duration, day of week(with capital letters), room (separated with spaces): ");
		String lessonInfo = in.readLine();
		return new Request(courseID + " " + teacherID + " " + lessonInfo ,this, RequestType.values()[0]);
	}
	public boolean equals(Object o) {
		if (!super.equals(o)) return false;
		Student s = (Student)o;
		return this.yearOfEducation == s.yearOfEducation && this.faculty == s.faculty;
	}
	public String toString() {
		return super.toString() + " "+ this.faculty.name() +" year of Education " + this.yearOfEducation + " year of admission " + this.yearOfAdmission; 
	}
	
	public int hashCode() {
		return super.hashCode();
	}
	
	public int compareTo(Student o) {
		return super.compareTo(o);
	}
	public String viewStudentAndMark(Course c) {
		return this.userID + " | " + this.firstName + " " + this.lastName + " | " + this.getCoursesAndMarks().get(c);
	}
	public static void main(String args[]) {
		Database.findStudentbyLogin("a_igali").viewSchedule();
	}
}
