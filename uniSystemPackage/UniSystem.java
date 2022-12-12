package uniSystemPackage;
import java.io.BufferedReader;


import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.StringTokenizer;

import nonUserPackage.*;
import userPackage.*;

public class UniSystem implements Serializable{

	private static final long serialVersionUID = -4945903509392417238L;
	protected String systemName;
	public UniSystem(String systemName) {
		this.systemName = systemName;
	}
	
	public void turnOn(){
		while(true) {
			boolean exit = false;
			int acc;
			System.out.println("Login as:\n1.Admin\n2.Student\n3.Teacher\n4.Manager\n5.Dean\n6.Leave");
			try{
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				acc = Integer.parseInt(in.readLine());
				if (acc == 1) {
					System.out.println("Hello " + Admin.adminning().toString());
					while(exit == false) {
						System.out.println("What to do?\n1.Add user\n2.Check logs\n3.Log out");
						int action = Integer.parseInt(in.readLine());
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
							else if (action == 3) {
							exit = true;
							}
						}
					}
				else if (acc == 2) {
					while(exit == false) {
						System.out.println("Hello! Write login and password of Student:");
						String loginAndPassword = in.readLine();
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
							int action = Integer.parseInt(in.readLine());
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
					
				}
				else if (acc == 4) {
					while(exit == false) {
						System.out.println("Hello! Write login and password of Manager:");
						String loginAndPassword = in.readLine();
						StringTokenizer st = new StringTokenizer(loginAndPassword);
						Manager m = Database.findManagerbyLogin(st.nextToken());
						if(!m.checkPassword(st.nextToken())) {
							System.out.println("Not this time(");
							break;
						}
						while(exit == false) {
							System.out.println("What to do?\n1.Check requests\n2.Create course\n3.Manage News\n4.leave");
							int action = Integer.parseInt(in.readLine());
							if(action == 1) {
									
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
							else if(action == 4) {
								exit = true;
							}
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

