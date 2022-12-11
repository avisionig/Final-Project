package userPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.StringTokenizer;

import nonUserPackage.Faculty;

public class Admin extends User{

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
		System.out.println("1.Student" + "\n" + "2.Teacher" + "\n" + "3.Manager" + "4.Librarian" + "\n" + "5.Dean");
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			int line = input.read();
			System.out.print("Write first and last name:");
			String names = input.readLine();
			StringTokenizer st = new StringTokenizer(names);
			if(line == 1) {
				System.out.println("Choose faculty:");
				System.out.println("1." + Faculty.values()[0] + "\n" + "2." + Faculty.values()[1]+ "\n" + "3." + Faculty.values()[2] + "4." + Faculty.values()[3]);
				int fac = input.read();
				return new Student(st.nextToken(), st.nextToken(), LocalDate.now(), Faculty.values()[fac - 1]);
			}
			
		}
		catch(IOException ioe) {
			
		}
		return null;
	}
}
