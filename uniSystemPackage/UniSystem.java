package uniSystemPackage;
import java.io.BufferedReader;



import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import userPackage.*;

public class UniSystem {

	protected String systemName;
	public UniSystem(String systemName) {
		this.systemName = systemName;
	}
	
	public void turnOn(){
		while(true) {
			int acc;
			Database.accessDB();
			System.out.println("Login as:\n1.Admin\n2.Student\n3.Teacher\n4.Manager\n5.Librarian\n6.Leave");
			try{
				BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
				acc = Integer.parseInt(input.readLine());
				if (acc == 1) {
					System.out.println("Hello, admin!");
					Admin.adminning().userMenu(input);
					}
				else if (acc == 2) {
					System.out.println("Hello! Write login and password of Student:");
					String loginAndPassword = input.readLine();
					String lap = User.loginToAccount(input, loginAndPassword);
					if(lap == null) {
						System.out.println("Null");
						}
					else {
						StringTokenizer st = new StringTokenizer(lap);
						Student s = Database.accessDB().findStudentbyLogin(st.nextToken());
						if(!s.checkPassword(st.nextToken())) {
							System.out.println("Not this time(");
							break;
						}
						s.userMenu(input);
					}
				}
				else if(acc == 3) {
					System.out.println("Hello! Write login and password of Teacher:");
					String loginAndPassword = input.readLine();
					String lap = User.loginToAccount(input, loginAndPassword);
					if(lap == null) {
						System.out.println("Null");
						}
					else {
						StringTokenizer st = new StringTokenizer(lap);
						Teacher t = Database.accessDB().findTeacherByLogin(st.nextToken());
						if(!t.checkPassword(st.nextToken())) {
							System.out.println("Not this time(");
							break;
						}
						t.userMenu(input);
					}
				}
				else if (acc == 4) {
						System.out.println("Hello! Write login and password of Manager:");
						String loginAndPassword = input.readLine();
						String lap = User.loginToAccount(input, loginAndPassword);
						if(lap == null) {
							System.out.println("Null");
							}
						else {
							StringTokenizer st = new StringTokenizer(lap);
							Manager m = Database.accessDB().findManagerbyLogin(st.nextToken());
							if(!m.checkPassword(st.nextToken())) {
								System.out.println("Not this time(");
								break;
							}
							m.userMenu(input);
						}
						
				}
				else if (acc == 5) {
					System.out.println("Hello, Librarian!");
					Librarian.getLibrarian().userMenu(input);
				}
				else if (acc == 6) {
					System.out.print("GoodBye!");
					Database.saveDatabase();
					break;
				}
			}
			catch(IOException ioe) {
				System.out.print("Bad happened");
				ioe.printStackTrace();
				Database.saveDatabase();
				break;
			}
		}
	}
	public static void main(String[] args){
		UniSystem a = new UniSystem("WSP");
		a.turnOn();
	}
}

