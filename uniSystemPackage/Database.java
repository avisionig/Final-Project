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
	protected static Vector<Mark> allMarks;
	
	static{
		allMarks = new Vector<Mark>();
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
	public static Request findRequestByID(String reqID) {
		for(Request r : allRequests) {
			if(r.getReqID().equalsIgnoreCase(reqID)) {
				return r;
			}
		}
		return null;
	}
	public static Teacher findTeacherByID(String teacherID) {
		for(Teacher t : allTeachers) {
			if(t.getUserID().equalsIgnoreCase(teacherID)) {
				return t;
			}
		}
		return null;
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
	public static Student findStudentbyID(String studID) {
		for(Student s : allStudents) {
			if(s.getUserID().equals(studID)) {
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
	public static void viewStudentsInCourse(String courseID, String teacherID) {
		Course c = Database.findCoursebyID(courseID);
		Teacher t = Database.findTeacherByID(teacherID);
		if (c != null && t != null) for(Student s : allStudents) {
			if(s.getCoursesAndMarks().keySet().contains(c) && c.getCourseTeachers().contains(t)) {
				System.out.println(s.StudentAndMark(c));
			}
		}
	}
	public static void viewAllCourses() {
		for(Course c : allCourses) {
			System.out.println(c);
		}
	}
	
	public static void viewAllRequests() {
		for(Request r : allRequests) {
			System.out.println(r);
		}
	}

	public static Vector<Mark> getMarks(){
		return Database.allMarks;
	}
	public static Vector<Request> getRequests(){
		return Database.allRequests;
	}
}
