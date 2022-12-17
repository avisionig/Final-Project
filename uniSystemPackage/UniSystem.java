package uniSystemPackage;
import java.io.BufferedReader;



import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import nonUserPackage.*;
import userPackage.*;

public class UniSystem {

	protected String systemName;
	public UniSystem(String systemName) {
		this.systemName = systemName;
	}
	
	public void turnOn(){
		while(true) {
			boolean exit = false;
			int acc;
			Database.accessDB();
			System.out.println("Login as:\n1.Admin\n2.Student\n3.Teacher\n4.Manager\n5.Dean\n6.Leave");
			try{
				BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
				acc = Integer.parseInt(input.readLine());
				if (acc == 1) {
					System.out.println("Hello admin");
					while(exit == false) {
						System.out.println("What to do?\n1.Add user\n2.View users\n3.Delete user\n4.Log out");
						int action = Integer.parseInt(input.readLine());
						if (action == 1) {
							User newUser = Admin.adminning().addUser();
							
							if (newUser instanceof Student) {
								Database.allStudents.add((Student) newUser);
							}
							else if (newUser instanceof Teacher) {
								Database.allTeachers.add((Teacher) newUser);
							}
							else if (newUser instanceof Manager) {
								Database.allManagers.add((Manager) newUser);
							}
							else if(newUser == null) {
								System.out.println("Error! Null user.");
								}
							}
						else if(action == 2) {
							Database.viewAllUsers();
						}
						else if(action == 3) {
							Admin.adminning().deleteUser();
						}
						else if (action == 4) {
								exit = true;
							}
						}
					}
				else if (acc == 2) {
					while(exit == false) {
						System.out.println("Hello! Write login and password of Student:");
						String loginAndPassword = input.readLine();
						StringTokenizer st = new StringTokenizer(loginAndPassword);
						Student s = Database.findStudentbyLogin(st.nextToken());
						if (s == null) {
							System.out.println("Null");
							break;
						}
						if(!s.checkPassword(st.nextToken())) {
							System.out.println("Not this time(");
							break;
						}
						while(exit == false) {
							System.out.println("What to do?\n1.leave\n2.register to course");
							int action = Integer.parseInt(input.readLine());
							if(action == 1) {
								exit = true;
							}
							else if(action == 2) {
								Request r = s.registerTo();
								if(r != null) {
									Database.allRequests.add(r);
								}
								else {
									System.out.println("Error in system, please try again");
								}
							}
						}
					}
				}
				else if(acc == 3) {
					while(exit == false) {
						System.out.println("Hello! Write login and password of Teacher:");
						String loginAndPassword = input.readLine();
						StringTokenizer st = new StringTokenizer(loginAndPassword);
						Teacher t = Database.findTeacherByLogin(st.nextToken());
						if(!t.checkPassword(st.nextToken())) {
							System.out.println("Not this time(");
							break;
						}
						while(exit == false) {
							System.out.println("What to do?\n1.Put mark\n2.Close attestation\n3.leave");
							int action = Integer.parseInt(input.readLine());
							
							if(action == 1) {
								t.putMark();
							}
							else if(action == 2) {
								t.closeAttestaion();
							}
							
							else if(action == 3) {
								exit = true;
							}
						}
					}
				}
				else if (acc == 4) {
					while(exit == false) {
						System.out.println("Hello! Write login and password of Manager:");
						String loginAndPassword = input.readLine();
						StringTokenizer st = new StringTokenizer(loginAndPassword);
						Manager m = Database.findManagerbyLogin(st.nextToken());
						if(!m.checkPassword(st.nextToken())) {
							System.out.println("Not this time(");
							break;
						}
						while(exit == false) {
							System.out.println("What to do?\n1.Check requests\n2.Create course\n3.Manage News\n4.Manage courses\n5.leave");
							int action = Integer.parseInt(input.readLine());
							
							if(action == 1) {
								m.checkRequests();
							}
							else if(action == 2) {
								Course c = m .createCourse();
								if(c != null) {
									Database.allCourses.add(c);
								}
								else {
									System.out.println("Error");
								}
							}
							
							else if(action == 3) {
									
							}
							else if (action == 4) {
								m.manageCourse();
							}
							else if(action == 5) {
								exit = true;
							}
						}
					}
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

