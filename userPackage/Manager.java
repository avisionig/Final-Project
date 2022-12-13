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
//	protected ManagerType managerType;
	protected Manager(String firstName, String lastName, LocalDate hireDate/*,ManagerType managerType*/) {
		super(firstName, lastName, hireDate);
		this.setUserID();
		/*this.managerType = managerType;*/
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
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String courseInput = in.readLine();
			StringTokenizer st = new StringTokenizer(courseInput);
			System.out.println("Choose faculty:");
			System.out.println("1." + Faculty.values()[0] + "\n2." + Faculty.values()[1]+ "\n3." + Faculty.values()[2] + "\n4." + Faculty.values()[3] + "\n5." + Faculty.values()[4]);
			int fac = Integer.parseInt(in.readLine());
			System.out.println("Does course have prerequesite?\n1.Yes\n2.No");
			int prereqYesOrNo = Integer.parseInt(in.readLine());
			if(prereqYesOrNo == 1 ) {
				System.out.print("Write prerequesite name:");
				String prereqName = in.readLine();
				Course prereq = Database.findCoursebyName(prereqName);
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
	
	public void checkRequests() throws IOException {
		System.out.println("All requests:");
		Database.viewAllRequests();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String reqID = in.readLine();
		Request r = Database.findRequestByID(reqID);
		System.out.println("1.Add\n2.No");
		int action = Integer.parseInt(in.readLine());
		if (action == 1) {
			Student s = (Student) r.getRequestAuthor();
			Course c = Database.findCoursebyID(r.getDesctiption());
			System.out.println("Which teacher:");
			c.viewCourseTeachers();
			String teacherID = in.readLine();
			s.getCoursesAndMarks().put(c, new Mark(s.getUserID(), teacherID, c.getCourseID()));
			Database.getRequests().remove(r);
		}
		else {
			Database.getRequests().remove(r);
		}
		
	}
	
	
	
	
	public void manageCourse() throws IOException {
		Database.viewAllCourses();
		System.out.println("Choose course by ID");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String courseID = in.readLine();
		Course c = Database.findCoursebyID(courseID);
		System.out.println("1.Set teacher\n2.Delete teacher");
		int action = Integer.parseInt(in.readLine());
		if(action == 1) {
			System.out.print("Write teacher ID:");
			String teacherID = in.readLine();
			c.getCourseTeachers().add(Database.findTeacherByID(teacherID));
			System.out.print("Teacher added");
		}
		else if(action == 2) {
			System.out.print("Write teacher ID: ");
			String teacherID = in.readLine();
			c.getCourseTeachers().remove(Database.findTeacherByID(teacherID));
			System.out.print("Teacher deleted");
		}
	}

}
