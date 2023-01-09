package userPackage;

import java.io.BufferedReader;


import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.StringTokenizer;

import nonUserPackage.*;
import uniSystemPackage.*;
/**
 * 
 * class Manager, creates news, courses, lessons for them, puts teacher to course, approves requests and etc.
 *
 */
public class Manager extends Employee{

	private static final long serialVersionUID = -885431281351803814L;
	protected Manager(String firstName, String lastName, LocalDate hireDate) {
		super(firstName, lastName, hireDate);
		this.setUserID();
	}
	
	protected void setUserID() {
		this.userID = String.valueOf(this.hireDate.getYear() - 2000) + "MNG0" +String.valueOf((int)(Math.random()*1000+78));
	}
	
	/**
	 * Creates course, can choose it's faculty and prerequisite , if needed.
	 * actually don't know if thing with prerequisite, wasn't checked.
	 * @return new course that will be added in database.
	 */
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
				Course prereq = Database.accessDB().findCoursebyName(prereqName);
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
	/**
	 * creates News, that can be see with every user
	 * @see userPackage.User#viewNews()
	 * @return news that will be added in database
	 */
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
	/**
	 * checks requests that were send by students
	 * adds or don't courses
	 * requests with documents wasn't finished( 
	 * @throws IOException
	 */
	public void checkRequests() throws IOException {
		System.out.println("All requests:");
		Database.accessDB().viewAllRequests();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String reqID = in.readLine();
		Request r = Database.accessDB().findRequestByID(reqID);
		if(r.getRequestType() == RequestType.ADDDROP) {
			System.out.println("1.Add\n2.No");
			int action = Integer.parseInt(in.readLine());
			if (action == 1) {
				Student s = (Student) r.getRequestAuthor();
				StringTokenizer st = new StringTokenizer(r.getDesctiption(), ": ");
				Course c = Database.accessDB().findCoursebyID(st.nextToken());
				if(c == null) {
					System.out.println("Error no such course");
					Database.accessDB().getRequests().remove(r);
					return;
				}
				Teacher t = Database.accessDB().findTeacherByID(st.nextToken());
				if(t == null) {
					System.out.println("Error no such teacher");
					Database.accessDB().getRequests().remove(r);
					return;
				}
				Lesson l = t.getSchedule().findLessonByFullInfo(new Time(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Double.parseDouble(st.nextToken()), DayOfWeek.valueOf(st.nextToken())), t, c, Integer.parseInt(st.nextToken()));
				if(l == null) {
					System.out.println("Error no such teacher");
					Database.accessDB().getRequests().remove(r);
					return;
				}
				s.getCoursesAndMarks().put(c, new Mark());
				s.getSchedule().addLesson(l);
				Database.accessDB().getRequests().remove(r);
			}
			else {
				Database.accessDB().getRequests().remove(r);
			}
		}
		else if(r.getRequestType() == RequestType.DOCUMENT) {
			
		}
	}
	
	/**
	 * to put teacher to course, create lesson for courses and deletes teachers from course.
	 * don't know what will happen with schedule of teacher and etc, didn't tested deletion.
	 * @throws IOException
	 */
	public void manageCourse() throws IOException {
		Database.accessDB().viewAllCourses();
		System.out.println("Choose course by ID");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String courseID = in.readLine();
		Course c = Database.accessDB().findCoursebyID(courseID);
		System.out.println("1.Set teacher\n2.Manage lessons\n3.Delete teacher");
		int action = Integer.parseInt(in.readLine());
		if(action == 1) {
			Database.accessDB().viewAllTeachers();
			System.out.print("Write teacher ID:");
			String teacherID = in.readLine();
			c.getCourseTeachers().add(Database.accessDB().findTeacherByID(teacherID));
			System.out.println("Teacher added!");
		}
		else if(action == 2) {
			System.out.println("Write lesson time(format 00:00), lesson duration, day of week(with capital letters), room (separated with spaces): ");
			String lessonInfo = in.readLine();
			StringTokenizer st = new StringTokenizer(lessonInfo, ": ");
			System.out.println("Which teacher lesson it will be:");
			c.viewCourseTeachers();
			String teacherID = in.readLine();
			Teacher t = Database.accessDB().findTeacherByID(teacherID);
			t.getSchedule().addLesson(new Lesson(new Time(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Double.parseDouble(st.nextToken()), DayOfWeek.valueOf(st.nextToken())), t, c, Integer.parseInt(st.nextToken())));
		}
		else if(action == 3) {
			System.out.print("Write teacher ID: ");
			String teacherID = in.readLine();
			c.getCourseTeachers().remove(Database.accessDB().findTeacherByID(teacherID));
			System.out.println("Teacher deleted");
		}
	}
	
	/**
	 * user menu with important methods of manager.
	 * invokes in UniSystem class.
	 * 
	 * @see uniSystemPackage.UniSystem
	 */
	public void userMenu(BufferedReader input) {
		while(true) {
			super.userMenu(input);
			System.out.println("1.Check requests\n2.Create course\n3.Create News\n4.Manage courses\n5.View news");
			try {
				String action = input.readLine();
		
				if(action.equals("Q")) {
					return;
				}
				int ac = Integer.parseInt(action);
				if(ac == 1) {
					this.checkRequests();
				}
				else if(ac == 2) {
					Course c = this.createCourse();
					if(c != null) {
						Database.accessDB().getCourses().add(c);
					}
					else {
						System.out.println("Error");
					}
				}
				else if(ac == 3) {
					News n = this.createNews();
					if(n != null) {
						Database.accessDB().getNews().add(n);
					}
					else {
						System.out.println("Error");
					}
				}
				else if(ac == 4) {
					this.manageCourse();
				}
				else if(ac == 5) {
					this.viewNews();
				}
			} 	catch (IOException e) {
				System.out.println("Somethinhg bad happened!");
				e.printStackTrace();
			}
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
