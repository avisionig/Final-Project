package userPackage;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.StringTokenizer;

import nonUserPackage.Faculty;
import nonUserPackage.Log;
import nonUserPackage.LogAction;
import nonUserPackage.StudentDegree;
import nonUserPackage.TeacherDegree;
import uniSystemPackage.Database;

public final class Admin extends User{

	private Admin(String firstName, String lastName) {
		super(firstName, lastName);
	}
	private static final long serialVersionUID = 5980109120154656187L;
	private static Admin admin = new Admin("Super", "Admin");
	void setUserID() {
		this.userID = "ADMIN777";
	}
	public static Admin adminning() {
		return admin;
	}
	public User addUser() {
		System.out.println("Who you want to add?");
		System.out.println("1.Student\n2.Teacher\n3.Manager");
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			int line = Integer.parseInt(input.readLine());
			System.out.print("Write first and last name:");
			String names = input.readLine();
			StringTokenizer st = new StringTokenizer(names);
			if(line == 1) {
				System.out.println("Choose faculty:");
				System.out.println("1." + Faculty.values()[0] + "\n2." + Faculty.values()[1]+ "\n3." + Faculty.values()[2] + "\n4." + Faculty.values()[3]);
				int fac = Integer.parseInt(input.readLine());
				System.out.println("1." + StudentDegree.values()[0] + "\n2." + StudentDegree.values()[1]+ "\n3." + StudentDegree.values()[2]);
				int deg = Integer.parseInt(input.readLine());
				return new Student(st.nextToken(), st.nextToken(), LocalDate.now(), Faculty.values()[fac - 1], StudentDegree.values()[deg - 1]);
			}
			else if (line == 2) {
				System.out.println("Choose teacher degree:");
				System.out.println("1." + TeacherDegree.values()[0] + "\n2." + TeacherDegree.values()[1]+ "\n3." + TeacherDegree.values()[2] + "\n4." + TeacherDegree.values()[3]);
				int deg = Integer.parseInt(input.readLine());
				return new Teacher(st.nextToken(), st.nextToken(), LocalDate.now(), TeacherDegree.values()[deg - 1]);
			}
			else if (line == 3) {
				return new Manager(st.nextToken(), st.nextToken(), LocalDate.now());
			}
		}
		catch(IOException ioe) {
			
		}
		return null;
	}
	public void deleteUser() {
		System.out.println("Who you want to delete?:");
		System.out.println("1.Student\n2.Teacher\n3.Manager");
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			int line = Integer.parseInt(input.readLine());
			System.out.print("Write login:");
			String login = input.readLine();
			User u;
			if(line == 1) {
				u = Database.accessDB().findStudentbyLogin(login);
				Database.accessDB().getLogs().add(new Log(LogAction.DELETED, u.toString()));
				Database.accessDB().getStudents().remove(u);	
			}
			else if (line == 2) {
				u = Database.accessDB().findTeacherByLogin(login);
				Database.accessDB().getLogs().add(new Log(LogAction.DELETED, u.toString()));
				Database.accessDB().getTeachers().remove(u);
			}
			else if (line == 3) {
				u = Database.accessDB().findManagerbyLogin(login);
				Database.accessDB().getLogs().add(new Log(LogAction.DELETED, u.toString()));
				Database.accessDB().getManagers().remove(u);
			}
		}
		catch(IOException ioe) {
			
		}
	}
	
	
	public void userMenu(BufferedReader input) {
		while(true) {
			super.userMenu(input);
			System.out.println("1.Add user\n2.View users\n3.Delete user\n4.View logs\n5.View news");
			try{
				String action = input.readLine();
				if(action.equals("Q")) {
					return;
				}
				int ac = Integer.parseInt(action);
				if (ac == 1) {
					User newUser = Admin.adminning().addUser();
					
					if (newUser instanceof Student) {
						Database.accessDB().getStudents().add((Student) newUser);
					}
					else if (newUser instanceof Teacher) {
						Database.accessDB().getTeachers().add((Teacher) newUser);
					}
					else if (newUser instanceof Manager) {
						Database.accessDB().getManagers().add((Manager) newUser);
					}
					else if(newUser == null) {
						System.out.println("Error! Null user.");
						}
					Database.accessDB().getLogs().add(new Log(LogAction.ADDED, newUser.toString()));
					}
				else if(ac == 2) {
					Database.accessDB().viewAllUsers();
				}
				else if(ac == 3) {
					Admin.adminning().deleteUser();
				}
				else if(ac == 4) {
					Database.accessDB().viewLogs();
				}
				else if(ac == 5) {
					Admin.adminning().viewNews();
				}
		}catch(IOException ioe) {
			System.out.println("Somethinhg bad happened!");
			ioe.printStackTrace();
			}
		}
	}
	public String toString() {
    	return super.toString();
    }
}
