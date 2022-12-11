package userPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.StringTokenizer;

import nonUserPackage.*;
import uniSystemPackage.*;

public class Manager extends Employee{

	private static final long serialVersionUID = -885431281351803814L;
	private static int managerNum = 0;
	protected ManagerType managerType;
	protected Manager(String firstName, String lastName, LocalDate hireDate,ManagerType managerType) {
		super(firstName, lastName, hireDate);
		this.managerType = managerType;
	}
	
	{
		managerNum++;
	}

	protected void setUserID() {
		this.userID = String.valueOf(this.hireDate.getYear() - 2000) + "MNG0" +String.valueOf(managerNum);
	}
	public Course createCourse() {
		
		System.out.print("Write course name and credits amount:");
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			String courseInput = input.readLine();
			StringTokenizer st = new StringTokenizer(courseInput);
			System.out.println("Choose faculty:");
			System.out.println("1." + Faculty.values()[0] + "\n2." + Faculty.values()[1]+ "\n3." + Faculty.values()[2] + "\n4." + Faculty.values()[3] + "\n5." + Faculty.values()[4]);
			int fac = input.read();
			System.out.println("Does course have prerequesite?\n1.Yes\n2.No");
			int prereqYesOrNo = input.read();
			if(prereqYesOrNo == 1 ) {
				System.out.print("Write prerequesite name:");
				String prereqName = input.readLine();
				Course prereq = UniSystem.findCoursebyName(prereqName);
				return new Course(st.nextToken(), Integer.parseInt(st.nextToken()),Faculty.values()[fac - 1], prereq);
			}
			else {
				return new Course(st.nextToken(), Integer.parseInt(st.nextToken()),Faculty.values()[fac - 1]);
			}
			
		}
		catch(IOException ioe) {
			System.out.println("Something bad happened!");
		}
		return null;
	}

}
