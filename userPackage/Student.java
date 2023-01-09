package userPackage;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.StringTokenizer;

import PaperPackage.DoneTaskPaper;
import PaperPackage.TaskPaper;
import PaperPackage.TaskPaperType;
import nonUserPackage.*;
import uniSystemPackage.Database;
/**
 * Student class, student can register to courses, do tasks, put attendance, get books and then read them.
 * Student can be researcher if their degree PhD.
 * @author ayan
 *
 */
public class Student extends User implements Scheduleable{
	private static final long serialVersionUID = 7227165493952096441L;
	protected int yearOfEducation = 1;
	protected Faculty faculty = null;
	protected LocalDate yearOfAdmission;
	protected double feeStudy;
	protected StudentDegree degree;
	protected HashMap<Course, Mark> coursesAndMarks;
	protected Mark studMarks;
	protected Schedule schedule;
	private StudentResearcher researcherAccount;
	{
		schedule = new Schedule();
    	coursesAndMarks = new HashMap<Course,Mark>();
    }
	public Student(String firstName, String lastName, LocalDate yearOfAdmission,Faculty faculty, StudentDegree degree) {
		super(firstName, lastName);
		this.yearOfAdmission = yearOfAdmission;
		this.faculty = faculty;
		this.degree = degree;
		this.setUserID();
		if(this.degree.equals(StudentDegree.PHD)) {
			this.researcherAccount = new StudentResearcher(this);
		}
		
	}
	protected void setUserID() {
		this.userID = String.valueOf(this.yearOfAdmission.getYear() - 2000) + "STUD0" + String.valueOf(this.faculty.ordinal() + 1) +String.valueOf((int)(Math.random()*1000+78));
	}
	public Faculty getFaculty() {
		return this.faculty;
	} 
	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}

	public LocalDate getYearOfAdmission() {
		 return this.yearOfAdmission;
	}

	public double getFeeStudy() {
		 return this.feeStudy;
	}

	public StudentDegree getDegree() {
		 return this.degree;
	}
	public int getYearOfEducation() {
		return this.yearOfEducation;
	}
	public HashMap<Course, Mark> getCoursesAndMarks(){
		return this.coursesAndMarks;
	}
	public Schedule getSchedule() {
		return this.schedule;
	}
	public void viewSchedule() {
		System.out.println(this.schedule);
	}
	/**
	 * Method to check Attendance, if attendance was turned on in teacher class.
	 */
	public void checkAttendance(){
		System.out.println("Attendamce:");
		this.coursesAndMarks.entrySet().stream().filter(c -> c.getValue().getAttendanceStatus() == true).map(c -> this.schedule.findLessonByCourse(c.getKey())).forEach(System.out :: println);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Write course name to fill attendance(Q to quit):");
		String courseName;
		try {
			courseName = br.readLine();
			if(courseName.equals("Q")) {
				return;
			}
			this.coursesAndMarks.entrySet().stream().filter(c -> c.getKey().getCourseName().equals(courseName)).forEach(c -> c.getValue().markAttendance());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error!");
		}
		
	}
	/**
	 * Method that "do" tasks, after task is done student gets some score.
	 * To check tasks in course, course name need to be written.
	 * To do task that student have student need to write task name and it's type separated with space. 
	 */
	public void checkTasks() {
		for(Course c : this.coursesAndMarks.keySet()) {
			System.out.println(c);
		}
		System.out.println("Choose course by name:");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String courseName = br.readLine();
			Course c = Database.accessDB().findCoursebyName(courseName);
			Lesson l = this.schedule.findLessonByCourse(c);
			l.viewTasks();
			System.out.println("Choose task name and type(type enter to leave, if there is nothing ;) ):");
			String task = br.readLine();
			
			StringTokenizer st = new StringTokenizer(task, " ");
			if(!st.hasMoreElements()) {
				return;
			}
			TaskPaper tp = l.getTaskPaperByNameAndType(st.nextToken(), TaskPaperType.valueOf(st.nextToken()));
			Database.accessDB().findTeacherByID(tp.getSender()).checkDoneTask(new DoneTaskPaper(tp, this.userID));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Student register to course, it actually creates request. 
	 * Student writes courseID that student wants to learn and writes time of lesson. 
	 * @return request that will be checked with manager
	 * @throws IOException
	 */
	public Request registerTo() throws IOException {
		String courseID;
		System.out.println("Choose course:");
		Database.accessDB().viewAllCourses();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("Write courseID:");
		courseID = in.readLine();
		Course c = Database.accessDB().findCoursebyID(courseID);
		if(c == null) {
			while(true) {
				System.out.println("Incorrect course ID, try again?\1.Yes\2.No");
				int action = Integer.parseInt(in.readLine());
				if(action == 2) {
					return null;
				}
				else {
					courseID = in.readLine();
					c = Database.accessDB().findCoursebyID(courseID);
					if ( c != null) {
						break;
					}
				}
			}
		}
		c.viewCourseTeachers();
		System.out.println("Which teacher you want to choose:");
		String teacherID = in.readLine();
		Teacher t = Database.accessDB().findTeacherByID(teacherID);
		if(t == null) {
			while(true) {
				System.out.println("Incorrect teacher ID, try again?\1.Yes\2.No");
				int action = Integer.parseInt(in.readLine());
				if(action == 2) {
					return null;
				}
				else {
					teacherID = in.readLine();
					t = Database.accessDB().findTeacherByID(teacherID);
					if ( t != null) {
						break;
					}
				}
			}
		}
		System.out.println("Choose teachers lesson:");
		t.viewSchedule();
		System.out.print("Write lesson time(format 00:00), lesson duration, day of week(with capital letters), room (separated with spaces): ");
		String lessonInfo = in.readLine();
		return new Request(courseID + " " + teacherID + " " + lessonInfo ,this, RequestType.values()[0]);
	}
	
	
	public boolean equals(Object o) {
		if (!super.equals(o)) return false;
		Student s = (Student)o;
		return this.yearOfEducation == s.yearOfEducation && this.faculty == s.faculty;
	}
	
	/**
	 * Creates subscription in "library" to get books. Student can have several subscriptions, but one is enough. Invoke this method only time, please. 
	 * 
	 */
	public void getSubscription() {
		Librarian.getLibrarian().addLibrarySubscription(this);
		System.out.println("Added");
	}
	
	/**
	 * gets book from "library" and adds that book to subscription.
	 */
	public void getBook() {
		Database.accessDB().viewBooks();
		System.out.println("Write bookID you want to get:");
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String bookID = in.readLine();
			Book b = Database.accessDB().findBookByID(bookID);
			Librarian.getLibrarian().giveBook(this, b);
			System.out.println("Added");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * shows content of book.
	 */
	public void readBooks() {
		LibrarySubscription ls = Librarian.getLibrarian().findLibrarySubscriptionByStudentId(this.userID);
		if(ls != null) { 
			int i = 1;
			for(Book b : ls.getBooksInSubscriptions()) {
				System.out.println(i + "." + b);
			}
			try {
				System.out.println("Choose book:");
				BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
				int book = Integer.parseInt(in.readLine());
				System.out.println(ls.getBooksInSubscriptions().elementAt(book - 1).getContent());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * method that combines 3 methods that connected with books.
	 * This method than invokes in user menu method.
	 * @param input
	 */
	public void library(BufferedReader input) {
		while(true) {System.out.println("1.Get subscription\n2.Get book\n3.Read book\n4.Leave");
			try {
				int ac = Integer.parseInt(input.readLine());
				if (ac == 1) {
					this.getSubscription();
				}
				else if(ac == 2) {
					this.getBook();
				}
				else if (ac == 3) {
					this.readBooks();
				}
				else if(ac == 4) {
					return;
				}
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * method that combines all important method of student that needed. that method invokes in UniSystem class.
	 * @see uniSystemPackage.UniSystem
	 */
	public void userMenu(BufferedReader input) {
		while(true) {
			super.userMenu(input);
			System.out.println("1.register to course\n2.Attendance\n3.Check tasks\n4.View new\n5.library\n6.Create organization");
			String action;
			try {
				if(this.researcherAccount != null) {
					System.out.println("7.Do science");
				}
				action = input.readLine();
		
				if(action.equals("Q")) {
					return;
				}
				int ac = Integer.parseInt(action);
				if(ac == 1) {
					Request r = this.registerTo();
					if(r != null) {
						Database.accessDB().getRequests().add(r);
					}
					else {
						System.out.println("Error in system, please try again");
					}
				}
				else if(ac == 2) {
					this.checkAttendance();
				}
				else if(ac == 3) {
					this.checkTasks();
				}
				else if(ac == 4) {
					this.viewNews();
				}
				else if(ac == 5) {
					this.library(input);;
				}
				else if(ac == 6) {
					Dean.deaning().createOrganization(this);
				}
				if(this.researcherAccount != null) {
					if(ac == 7) {
						this.researcherAccount.userMenu(input);
					}
				}
			} 	catch (IOException e) {
				System.out.println("Somethinhg bad happened!");
				e.printStackTrace();
			}
		}
	}
	
	public String toString() {
		return super.toString() + " "+ this.faculty.name() +" year of Education " + this.yearOfEducation + " year of admission " + this.yearOfAdmission; 
	}
	public String nameAndID() {
		return super.toString();
	}
	public int hashCode() {
		return super.hashCode();
	}
	
	public int compareTo(Student o) {
		return super.compareTo(o);
	}
	public String viewStudentAndMark(Course c) {
		return this.userID + " | " + this.firstName + " " + this.lastName + " | " + this.getCoursesAndMarks().get(c);
	}

}
