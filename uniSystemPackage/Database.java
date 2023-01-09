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
/**
 * class of Database, all data is here.
 * Data is written to serialization file and read from serialization file. There all vectors of important classes.
 * Getters, viewers and etc. 
 * Searching from database with some arguments (ID, name, login).
 */
public class Database implements Serializable{

	private static final long serialVersionUID = 1293761087682124070L;
	private static Database db;
	
	protected String databaseName = "dataBase";
	
	protected Vector<Student> allStudents = new Vector<Student>();
	protected Vector<Teacher> allTeachers = new Vector<Teacher>();
	protected Vector<Course> allCourses = new Vector<Course>();
	protected Vector<News> allNews = new Vector<News>();
	protected Vector<Book> allBooks = new Vector<Book>();
	protected Vector<Manager> allManagers = new Vector<Manager>();
	protected Vector<Request> allRequests = new Vector<Request>();
	protected Vector<LibrarySubscription> allSubscriptions = new Vector<LibrarySubscription>();
	protected Vector<Organization> allOrganization = new Vector<Organization>();
	protected Vector<Log> allLogs = new Vector<Log>();
	
	static{
		System.out.println("Chop!");
		if( new File("C:\\Users\\ayan\\eclipse-workspace\\FinalProject\\database.txt").exists()) {
			try {
				db = readDatabase();
			}	
			catch(Exception e) {
				System.out.println("Database setting");
			}
		}
		else db = new Database();
	}
	private Database() {
		
	}
	public static Database accessDB() {
		return db;
	}
 	public Course findCoursebyName(String courseName) {
		for(Course c : allCourses) {
			if(c.getCourseName().equals(courseName)) {
				return c;
			}
		}
		return null;
	}
	public Course findCoursebyID(String courseID) {
		for(Course c : allCourses) {
			if(c.getCourseID().equals(courseID)) {
				return c;
			}
		}
		return null;
	}
	public Request findRequestByID(String reqID) {
		for(Request r : allRequests) {
			if(r.getReqID().equalsIgnoreCase(reqID)) {
				return r;
			}
		}
		return null;
	}
	public Teacher findTeacherByID(String teacherID) {
		for(Teacher t : allTeachers) {
			if(t.getUserID().equalsIgnoreCase(teacherID)) {
				return t;
			}
		}
		return null;
	}
	public Teacher findTeacherByLogin(String teacherLogin) {
		for(Teacher t : allTeachers) {
			if(t.getLogin().equalsIgnoreCase(teacherLogin)) {
				return t;
			}
		}
		return null;
	}
	public Employee findEmployeebyID(String userID){
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
	public Student findStudentbyLogin(String userLogin) {
		for(Student s : allStudents) {
			if(s.getLogin().equals(userLogin)) {
				return s;
			}
		}
		return null;
	}
	public Student findStudentbyID(String studID) {
		for(Student s : allStudents) {
			if(s.getUserID().equals(studID)) {
				return s;
			}
		}
		return null;
	}
	public Manager findManagerbyLogin(String userLogin) {
		for(Manager m: allManagers) {
			if(m.getLogin().equals(userLogin)) {
				return m;
			}
		}
		return null;
	}
	public Organization findOrganizationByID(String orgID) {
		for(Organization o: allOrganization) {
			if(o.getOrgID().equals(orgID)) {
				return o;
			}
		}
		return null;
	}
	public Book findBookByID(String bookID) {
		for(Book b: allBooks) {
			if(b.getBookID().equals(bookID)) {
				return b;
			}
		}
		return null;
	}
	public void viewAllUsers() {
		viewAllStudents();
		viewAllTeachers();
		viewAllManagers();
	}
	public void viewStudentsInCourse(String courseID, String teacherID) {
		Course c = findCoursebyID(courseID);
		Teacher t = findTeacherByID(teacherID);
		if (c != null && t != null) for(Student s : allStudents) {
			if(s.getCoursesAndMarks().keySet().contains(c) && c.getCourseTeachers().contains(t)) {
				System.out.println(s.viewStudentAndMark(c));
			}
		}
	}
	public void viewAllCourses() {
		for(Course c : allCourses) {
			System.out.println(c);
		}
	}
	
	public void viewAllRequests() {
		for(Request r : allRequests) {
			System.out.println(r);
		}
	}
	public void viewAllStudents() {
		System.out.println("Students:");
		for(Student s: allStudents) {
			System.out.println(s);
		}
	}
	public void viewAllTeachers() {
		System.out.println("Teachers:");
		for(Teacher t: allTeachers) {
			System.out.println(t);
		}
	}
	public void viewAllManagers() {
		System.out.println("Managers:");
		for(Manager m: allManagers) {
			System.out.println(m);
		}
	}
	public void viewNews() {
		System.out.println("News");
		for(int i = allNews.size() - 1; i >= 0 ;i--) {
			System.out.println(allNews.elementAt(i));
		}
	}
	public void viewBooks() {
		System.out.println("Books:");
		for(Book b: allBooks) {
			System.out.println(b);
		}
	}
	public void viewLogs() {
		System.out.println("Logs:");
		for(int i = allLogs.size() - 1; i >= 0; i--) {
			System.out.println(allLogs.elementAt(i));
		}
	}
	public Vector<Organization> getOrganizations(){
		return allOrganization;
	}
	public Vector<Log> getLogs(){
		return allLogs;
	}
	public Vector<Request> getRequests(){
		return allRequests;
	}
	public Vector<Teacher> getTeachers(){
		return allTeachers;
	}
	public Vector<Course> getCourses(){
		return allCourses;
	}
	public Vector<Student> getStudents(){
		return allStudents;
	}
	public Vector<Manager> getManagers(){
		return allManagers;
	}
	public Vector<News> getNews(){
		return allNews;
	}
	public Vector<Book> getBooks(){
		return allBooks;
	}
	public Vector<LibrarySubscription> getSubscriptions(){
		return allSubscriptions;
	}

	private static Database readDatabase() {
			try {
				FileInputStream fis = new FileInputStream("C:\\Users\\ayan\\eclipse-workspace\\FinalProject\\database.txt");
				ObjectInputStream ois = new ObjectInputStream(fis);
				Database readbase = (Database)ois.readObject();
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
		FileOutputStream fos;
		ObjectOutputStream oos;
		try {
			fos = new FileOutputStream("C:\\Users\\ayan\\eclipse-workspace\\FinalProject\\database.txt");
			oos = new ObjectOutputStream(fos);
			oos.writeObject(db);
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
