import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;

import nonUserPackage.*;
import userPackage.*;

public class UniSystem {
	protected String systemName;
	protected Vector<Student> allStudents;
	protected Vector<Teacher> allTeachers;
	protected Vector<Course> allCourses;
	protected Vector<News> allNews;
	protected Vector<Book> allBooks;
	protected Vector<Manager> allManagers;
	{
		allStudents = new Vector<Student>();
	}
	public UniSystem(String systemName) {
		this.systemName = systemName;
	}
	public void turnOn() {
		while(true) {
			System.out.println("Login as:" + "\n" + "1.Admin" + "\n" + "2.Student" + "\n" + "3.Teacher" + "\n" + "4.Manager" + "\n" + "5.Dean");
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				int acc = in.read();
				if (acc == 1) {
					System.out.println("Hello" + Admin.adminning().toString());
					while(true) {
						System.out.println("What to do?" + "\n" + "1.Add user" + "\n" + "2.Check logs" + "\n" + "3.Log out");
						int action = in.read();
						if (action == 1) {
							User newUser = Admin.adminning().addUser();
							if (newUser instanceof Student) {
								allStudents.add((Student) newUser);
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
			}
			catch(IOException ioe) {
				System.out.println("Something bad happend!");
			}
		}
	}
}
