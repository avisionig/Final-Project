package uniSystemPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.Vector;

import nonUserPackage.*;
import userPackage.*;

public class Database implements Serializable{

	private static final long serialVersionUID = 1293761087682124070L;
	private static Database db;
	
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
//		if(new File("database.txt").exists()) {
//			try {
//				System.out.println("Chop!");
//				db = readDatabase();
//			}
//			catch(Exception e) {
//				System.out.println("Database not found");
//			}
//		}
//		else {
			System.out.println("Opp!");
			db = new Database();
			allMarks = new Vector<Mark>();
			allStudents = new Vector<Student>();
			allTeachers = new Vector<Teacher>();
			allCourses = new Vector<Course>();
			allNews = new Vector<News>();
			allBooks = new Vector<Book>();
			allManagers = new Vector<Manager>();
			allRequests = new  Vector<Request>();
//		}
	}
	public static Database accessDB() {
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
	public static Teacher findTeacherByLogin(String teacherLogin) {
		for(Teacher t : allTeachers) {
			if(t.getLogin().equalsIgnoreCase(teacherLogin)) {
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
	public static void viewAllUsers() {
		viewAllStudents();
		viewAllTeachers();
		viewAllManagers();
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
	public static void viewAllStudents() {
		System.out.println("Students:");
		for(Student s: allStudents) {
			System.out.println(s);
		}
	}
	public static void viewAllTeachers() {
		System.out.println("Teachers:");
		for(Teacher t: allTeachers) {
			System.out.println(t);
		}
	}
	public static void viewAllManagers() {
		System.out.println("Managers:");
		for(Manager m: allManagers) {
			System.out.println(m);
		}
	}

	public static Vector<Mark> getMarks(){
		return Database.allMarks;
	}
	public static Vector<Request> getRequests(){
		return Database.allRequests;
	}
	
	private static Database readDatabase() {
			try {
				FileInputStream fis = new FileInputStream("database.txt");
				ObjectInputStream ois = new ObjectInputStream(fis);
				Database readbase = (Database)ois.readObject();
				fis.close();
				ois.close();
				return readbase;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Not found");
			return null;

	}
	public static void saveDatabase() {
		FileOutputStream fos;
		ObjectOutputStream oos;
		try {
			fos = new FileOutputStream("database.txt");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(Database.accessDB());
			fos.close();
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
