package uniSystemPackage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.StringTokenizer;
import java.util.Vector;

import nonUserPackage.*;
import userPackage.*;

public class UniSystem implements Serializable{

	private static final long serialVersionUID = -4945903509392417238L;
	protected String systemName;
	protected static Vector<Student> allStudents;
	protected static Vector<Teacher> allTeachers;
	protected static Vector<Course> allCourses;
	protected static Vector<News> allNews;
	protected static Vector<Book> allBooks;
	protected static Vector<Manager> allManagers;
	
	static{
		allStudents = new Vector<Student>();
		allTeachers = new Vector<Teacher>();
		allCourses = new Vector<Course>();
		allNews = new Vector<News>();
		allBooks = new Vector<Book>();
		allManagers = new Vector<Manager>();
	}

	public UniSystem(String systemName) {
		this.systemName = systemName;
	}
	public static Course findCoursebyName(String courseName) {
		for(Course c : allCourses) {
			if(c.getCourseName().equals(courseName)) {
				return c;
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
	private static Student findStudentbyLogin(String userLogin) {
		for(Student s : allStudents) {
			if(s.getLogin().equals(userLogin)) {
				return s;
			}
		}
		return null;
	}
	private static Manager findManagerbyLogin(String userLogin) {
		for(Manager m: allManagers) {
			if(m.getLogin().equals(userLogin)) {
				return m;
			}
		}
		return null;
	}
	
	public void turnOn(){
		boolean exit = false;
		int acc;
		while(exit == false) {
			System.out.println("Login as:\n1.Admin\n2.Student\n3.Teacher\n4.Manager\n5.Dean\n6.Leave");
			try{
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				acc = Integer.parseInt(in.readLine());
				if (acc == 1) {
					System.out.println("Hello" + Admin.adminning().toString());
					while(exit == false) {
						System.out.println("What to do?\n1.Add user\n2.Check logs\n3.Log out");
						int action = Integer.parseInt(in.readLine());
						if (action == 1) {
							User newUser = Admin.adminning().addUser();
							
							if (newUser instanceof Student) {
								allStudents.add((Student) newUser);
								System.out.println("Student login" + newUser.getLogin() + " password " + newUser.getPassword());
							}
							else if (newUser instanceof Teacher) {
								allTeachers.add((Teacher) newUser);
							}
							else if (newUser instanceof Manager) {
								allManagers.add((Manager) newUser);
							}
							else if(newUser == null) {
								System.out.println("Error! Null user.");
								}
							}
							else if (action == 3) {
							break;
							}
						}
					}
				else if (acc == 2) {
					while(true) {
						System.out.println("Hello! Write login and password of Student:");
						String loginAndPassword = in.readLine();
						StringTokenizer st = new StringTokenizer(loginAndPassword);
						Student s = UniSystem.findStudentbyLogin(st.nextToken());
						if (s == null) {
							System.out.println("Null");
							break;
						}
						if(!s.checkPassword(st.nextToken())) {
							System.out.println("Not this time(");
							break;
						}
						System.out.println("What to do?\n1.leave");
						int action = Integer.parseInt(in.readLine());
						if(action == 1) {
							break;
						}
						else if(action == 2) {
							
						}
						else if(action == 3) {
								
						}
					}
				}
				else if (acc == 4) {
					while(true) {
						System.out.println("Hello! Write login and password of Manager:");
						String loginAndPassword = in.readLine();
						StringTokenizer st = new StringTokenizer(loginAndPassword);
						Manager m = UniSystem.findManagerbyLogin(st.nextToken());
						if(!m.checkPassword(st.nextToken())) {
							System.out.println("Not this time(");
							break;
						}
						System.out.println("What to do?\n1.Check requests\n2.Create course\n3.Manage News");
						int action = Integer.parseInt(in.readLine());
						if(action == 1) {
								
						}
						else if(action == 2) {
							m.createCourse();
						}
						else if(action == 3) {
								
						}
					}
				}
				else if (acc == 6) {
					System.out.print("GoodBye!");
					exit = true;
				}
			}
			catch(IOException ioe) {
				System.out.print("Bad happened");
			}
		}
	}
	public static void main(String[] args){
		UniSystem a = new UniSystem("WSP");
		a.turnOn();
	}
}

