package userPackage;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.StringTokenizer;

import nonUserPackage.Faculty;
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
		System.out.println("1.Student\n2.Teacher\n3.Manager\n4.Librarian\n5.Dean");
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
				return new Student(st.nextToken(), st.nextToken(), LocalDate.now(), Faculty.values()[fac - 1], StudentDegree.BACHELOR);
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
		System.out.println("1.Student\n2.Teacher\n3.Manager\n4.Librarian\n5.Dean");
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			int line = Integer.parseInt(input.readLine());
			System.out.print("Write login:");
			String login = input.readLine();
			if(line == 1) {
				Database.getStudents().remove(Database.findStudentbyLogin(login));
			}
			else if (line == 2) {
				Database.getTeachers().remove(Database.findTeacherByLogin(login));
			}
			else if (line == 3) {
				Database.getManagers().remove(Database.findManagerbyLogin(login));
			}
		}
		catch(IOException ioe) {
			
		}
	}
	public String toString() {
    	return super.toString() + "\nAdmin: " +
                ", login='" + login + '\'';
    }
}
