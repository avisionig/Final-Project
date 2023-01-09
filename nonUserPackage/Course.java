package nonUserPackage;

import java.io.Serializable;
import java.util.Objects;
import java.util.Vector;

import userPackage.*;
/**
 * Course class, created by Manager, taken by Student and taught by Teacher.
 * Courses are divided to faculties, if faculty is BASE it belongs to everyone.
 * Have prerequisite field, but I don't know if it works correctly.
 * Contains Vector of Teachers.
 *
 */
public class Course implements Serializable{
	
	private static final long serialVersionUID = -3566405900025536509L;
	private static double creditPrice = 40000.0;
	private String courseID;
	private Course prereq;
	private String courseName;
	private Faculty faculty = Faculty.BASE;
	private Vector<Teacher> teachers;
	private int credits;
	{
		teachers = new Vector<Teacher>();
	}
	public Course(String courseName, int credits) {
		this.courseName = courseName;
		this.credits = credits;
		this.setID();
	}
	public Course(String courseName, int credits, Course prereq) {
		this(courseName, credits);
		this.prereq = prereq;
	}
	public Course(String courseName, int credits, Faculty faculty) {
		this(courseName, credits);
		this.faculty = faculty;
		this.setID();
	}
	public Course(String courseName, int credits, Faculty faculty, Course prereq) {
		this(courseName, credits, faculty);
		this.prereq = prereq;
	}
	
	private void setID() {
		this.courseID = this.faculty.name() + "0" + this.credits +String.valueOf((int)(Math.random()*100+69)); 
	}
	public double coursePrice() {
		return this.credits * creditPrice;
	}
	public Course getPrerequesite() {
		return this.prereq;
	}
	
	public Vector<Teacher> getCourseTeachers(){
		return this.teachers;
	}
	
	public void viewCourseTeachers() {
		for(Teacher t: this.teachers) {
			System.out.println(t);
		}
	}
	
	public String getCourseID() {
		return this.courseID;
	}
	
	public String getCourseName() {
		return this.courseName; 
	}
	public int getCredits() {
		return this.credits;
	}
	public int hashCode() {
		return Objects.hash(this.courseID);
	}
	public boolean equals(Object o) {
		if (o == null) return false;
		Course c = (Course) o;
		return this.credits == c.credits && this.courseID.equals(c.getCourseID());
	}
	public String toString() {
		return "( " + this.courseName + " | courseID:" + this.courseID + " | credits:" + this.credits + " | Faculty:" + this.faculty.name() + ")"; 
	}
}
