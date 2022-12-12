package uniSystemPackage;

import java.io.Serializable;

import java.util.Vector;

import nonUserPackage.*;
import userPackage.*;

public class Database implements Serializable{

	private static final long serialVersionUID = 1293761087682124070L;
	private static Database db = new Database();
	protected String databaseName = "database";
	protected static Vector<Student> allStudents;
	protected static Vector<Teacher> allTeachers;
	protected static Vector<Course> allCourses;
	protected static Vector<News> allNews;
	protected static Vector<Book> allBooks;
	protected static Vector<Manager> allManagers;
	protected static Vector<Request> allRequests;
	
	static{
		allStudents = new Vector<Student>();
		allTeachers = new Vector<Teacher>();
		allCourses = new Vector<Course>();
		allNews = new Vector<News>();
		allBooks = new Vector<Book>();
		allManagers = new Vector<Manager>();
		allRequests = new  Vector<Request>();
	}
	public Database accessDB() {
		return db;
	}
	public static Course findCoursebyName(String courseName) {
		for(Course c : allCourses) {
			if(c.getCourseName().equals(courseName)) {
				return c;
			}
		}
		return null;
	}
	public static Course findCoursebyID(String courseID) {
		for(Course c : allCourses) {
			if(c.getCourseID().equals(courseID)) {
				return c;
			}
		}
		return null;
	}
	public static void viewAllCourses() {
		for(Course c : allCourses) {
			System.out.println(c);
		}
	}
	public static Employee findEmployeebyID(String userID){
		for(Employee e : allTeachers) {
			if(e.getUserID().equalsIgnoreCase(userID)) {
				return e;
			}
		}for(Employee e : allManagers) {
			if(e.getUserID().equalsIgnoreCase(userID)) {
				return e;
			}
		}
		return null;	
	}
	public static Student findStudentbyLogin(String userLogin) {
		for(Student s : allStudents) {
			if(s.getLogin().equals(userLogin)) {
				return s;
			}
		}
		return null;
	}
	public static Manager findManagerbyLogin(String userLogin) {
		for(Manager m: allManagers) {
			if(m.getLogin().equals(userLogin)) {
				return m;
			}
		}
		return null;
	}
	
}
