package userPackage;

import java.io.BufferedReader;


import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.StringTokenizer;

import nonUserPackage.*;
import uniSystemPackage.*;

public class Manager extends Employee{

	private static final long serialVersionUID = -885431281351803814L;
//	protected ManagerType managerType;
	protected Manager(String firstName, String lastName, LocalDate hireDate/*,ManagerType managerType*/) {
		super(firstName, lastName, hireDate);
		this.setUserID();
		/*this.managerType = managerType;*/
	}
	
	protected void setUserID() {
		this.userID = String.valueOf(this.hireDate.getYear() - 2000) + "MNG0" +String.valueOf((int)(Math.random()*1000+78));
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
	
	public News createNews() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Title: ");
			String title = in.readLine();
			System.out.println("Description: ");
			String desc = in.readLine();
			return new News(title, desc, LocalDate.now());
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
		System.out.println("Choose type of request:");
		System.out.println("1.Registration\n 2.Document");
		int type = Integer.parseInt(in.readLine());
		if(type == 1) {
			String reqID = in.readLine();
			Request r = Database.findRequestByID(reqID);
			System.out.println("1.Add\n2.No");
			int action = Integer.parseInt(in.readLine());
			if (action == 1) {
				Student s = (Student) r.getRequestAuthor();
				StringTokenizer st = new StringTokenizer(r.getDesctiption(), ": ");
				Course c = Database.findCoursebyID(st.nextToken());
				if(c == null) {
					System.out.println("Error no such course");
					Database.getRequests().remove(r);
					return;
				}
				Teacher t = Database.findTeacherByID(st.nextToken());
				if(t == null) {
					System.out.println("Error no such teacher");
					Database.getRequests().remove(r);
					return;
				}
				Lesson l = t.getSchedule().findLessonByFullInfo(new Time(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Double.parseDouble(st.nextToken()), DayOfWeek.valueOf(st.nextToken())), t, c, Integer.parseInt(st.nextToken()));
				if(l == null) {
					System.out.println("Error no such teacher");
					Database.getRequests().remove(r);
					return;
				}
				s.getCoursesAndMarks().put(c, new Mark());
				s.getSchedule().addLesson(l);
				Database.getRequests().remove(r);
			}
			else {
				Database.getRequests().remove(r);
			}
		}
		else if(type == 2) {
			// НАДО ЗАПОЛНИТЬ
		}
	}
	
	
	public void manageCourse() throws IOException {
		Database.viewAllCourses();
		System.out.println("Choose course by ID");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String courseID = in.readLine();
		Course c = Database.findCoursebyID(courseID);
		System.out.println("1.Set teacher\n2.Manage lessons\n3.Delete teacher");
		int action = Integer.parseInt(in.readLine());
		if(action == 1) {
			Database.viewAllTeachers();
			System.out.print("Write teacher ID:");
			String teacherID = in.readLine();
			c.getCourseTeachers().add(Database.findTeacherByID(teacherID));
			System.out.println("Teacher added!");
		}
		else if(action == 2) {
			System.out.print("Write lesson time(format 00:00), lesson duration, day of week(with capital letters), room (separated with spaces): ");
			String lessonInfo = in.readLine();
			StringTokenizer st = new StringTokenizer(lessonInfo, ": ");
			System.out.print("Which teacher lesson it will be:");
			c.viewCourseTeachers();
			String teacherID = in.readLine();
			Teacher t = Database.findTeacherByID(teacherID);
			t.getSchedule().addLesson(new Lesson(new Time(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Double.parseDouble(st.nextToken()), DayOfWeek.valueOf(st.nextToken())), t, c, Integer.parseInt(st.nextToken())));
		}
		else if(action == 3) {
			System.out.print("Write teacher ID: ");
			String teacherID = in.readLine();
			c.getCourseTeachers().remove(Database.findTeacherByID(teacherID));
			System.out.println("Teacher deleted");
		}
	}
	
	public String toString() {
		return super.toString();
	}
	
	public boolean equals(Object o) {
		return super.equals(o);
	}
	
	public int compareTo(Manager o) {
		return super.compareTo(o);
	}
	public int hashCode() {
		return super.hashCode();
	}

}
