package uniSystemPackage;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Vector;

import nonUserPackage.*;
import userPackage.*;

@SuppressWarnings("unchecked")
public class Database implements Serializable{

	private static final long serialVersionUID = 1293761087682124070L;
	private static Database db;
	
	protected String databaseName = "dataBase";
	
	private static Vector<Object> all;
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
			allSubscriptions = new Vector<LibrarySubscription>();
			System.out.println("Chop!");
		File databaseFile = new File("C:\\Users\\ayan\\eclipse-workspace\\FinalProject\\database.txt");
		if(databaseFile.exists()) {
			try {
				all = readDatabase();
				allStudents = (Vector<Student>) all.elementAt(0);
				allTeachers = (Vector<Teacher>) all.elementAt(1);
				allManagers = (Vector<Manager>) all.elementAt(2);
				allCourses = (Vector<Course>) all.elementAt(3);
				allNews = (Vector<News>) all.elementAt(4);
				allBooks = (Vector<Book>) all.elementAt(5);
				allRequests = (Vector<Request>) all.elementAt(6);
				allSubscriptions = (Vector<LibrarySubscription>) all.elementAt(7);

			}	
			catch(Exception e) {
				System.out.println("Database setting");
			}
		}
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
				System.out.println(s.viewStudentAndMark(c));
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
	public static void viewNews() {
		System.out.println("News");
		for(int i = Database.allNews.size() - 1; i >= 0 ;i++) {
			System.out.println(allNews.elementAt(i));
		}
	}
	public static Vector<Request> getRequests(){
		return Database.allRequests;
	}
	public static Vector<Teacher> getTeachers(){
		return Database.allTeachers;
	}
	public static Vector<Course> getCourses(){
		return Database.allCourses;
	}
	public static Vector<Student> getStudents(){
		return Database.allStudents;
	}
	public static Vector<Manager> getManagers(){
		return Database.allManagers;
	}
	public static Vector<Book> getBooks(){
		return Database.allBooks;
	}

	private static Vector<Object> readDatabase() {
			try {
				FileInputStream fis = new FileInputStream("C:\\Users\\ayan\\eclipse-workspace\\FinalProject\\database.txt");
				ObjectInputStream ois = new ObjectInputStream(fis);
				@SuppressWarnings("unchecked")
				Vector<Object> readbase = (Vector<Object>)ois.readObject();
				fis.close();
				ois.close();
				System.out.println("readed!");
				return readbase;
			}
			catch (FileNotFoundException e) {
				System.out.println("File not fount!");
			} 
			catch (ClassNotFoundException e) {
				System.out.println("Pop");
			} 
			catch (IOException e) {
				System.out.println("Hop");
			}
			return null;

	}
	public static void saveDatabase() {
		all = new Vector<Object>();
		all.add(allStudents);
		all.add(allTeachers);
		all.add(allManagers);
		all.add(allCourses);
		all.add(allNews);
		all.add(allBooks);
		all.add(allRequests);
		all.add(allSubscriptions);
		FileOutputStream fos;
		ObjectOutputStream oos;
		try {
			fos = new FileOutputStream("C:\\Users\\ayan\\eclipse-workspace\\FinalProject\\database.txt");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(all);
			fos.close();
			oos.close();
			System.out.println("saved!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
