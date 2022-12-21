package userPackage;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.StringTokenizer;
import java.util.Vector;

import PaperPackage.DoneTaskPaper;
import PaperPackage.TaskPaper;
import PaperPackage.TaskPaperType;
import nonUserPackage.*;
import uniSystemPackage.Database;

public class Teacher extends Employee{
	protected Schedule teacherSchedule;
	protected TeacherDegree degree;
	protected Vector<TaskPaper> allCreatedTasks;
	private TeacherResearcher researcherAccount;
	private static final long serialVersionUID = 1051055956846350581L;
	{	
		allCreatedTasks = new Vector<TaskPaper>();
		teacherSchedule = new Schedule();
	}
	public Teacher(String firstName, String lastName, LocalDate hireDate, TeacherDegree degree) {
		super(firstName, lastName, hireDate);
		this.degree = degree;
		this.setUserID();
		if(this.degree.equals(TeacherDegree.PROFFESOR)) {
			this.researcherAccount = new TeacherResearcher(this);
		}
	}
	
	public Schedule getSchedule() {
		return this.teacherSchedule;
	}
	public void viewSchedule() {
		System.out.println(this.teacherSchedule);
	}
	protected void setUserID() {
		this.userID = String.valueOf(this.hireDate.getYear() - 2000) + "TEACH0" +String.valueOf((int)(Math.random()*1000+78));
	}
	public TeacherDegree getTeacherDegree() {
		return this.degree;
	}
	public void closeAttestaion() throws IOException {
		System.out.println("Choose course by ID");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String courseID = in.readLine();
		Course c = Database.findCoursebyID(courseID);
		if(c.getCourseTeachers().contains(this)) {
			for(Student s : Database.getStudents()) {
				if(s.getSchedule().findLessonByTeacher(this) != null) {
					s.getCoursesAndMarks().get(c).closeAttestaion();
				}
			}
		}
		else {
			System.out.println("You don't have such course");
		}
	}
	
	public void putMark() {
		System.out.println("Choose course by ID");
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			String courseID = in.readLine();
			if(Database.findCoursebyID(courseID).getCourseTeachers().contains(this)) {
				Database.viewStudentsInCourse(courseID, this.userID);
				System.out.println("Choose student by ID");
				String studID = in.readLine();
				Mark m = Database.findStudentbyID(studID).getCoursesAndMarks().get(Database.findCoursebyID(courseID));
				System.out.println("Put student's mark:");
				double point = Double.parseDouble(in.readLine());
				m.setMark(point);
			}
			else {
				System.out.println("You don't have such course");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void viewStudentsMarksInLesson() {
		System.out.println("All lessons:\n" + this.teacherSchedule);
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Type lesson time(starting time 00:00 format, duration and day):");
			String lessonTime = br.readLine();
			StringTokenizer st = new StringTokenizer(lessonTime, ":, ");
			Lesson l = this.teacherSchedule.findLessonByTime(new Time(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Double.parseDouble(st.nextToken()), DayOfWeek.valueOf(st.nextToken())));
			l.viewMarksOfStudents();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error!");
		}
	}
	public void launchAttendance(){
		System.out.println("All lessons:\n" + this.teacherSchedule);
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Type lesson time(starting time 00:00 format, duration and day):");
			String lessonTime = br.readLine();
			StringTokenizer st = new StringTokenizer(lessonTime, ":, ");
			Lesson l = this.teacherSchedule.findLessonByTime(new Time(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Double.parseDouble(st.nextToken()), DayOfWeek.valueOf(st.nextToken())));
			for(Student s : Database.getStudents()) {
				if(s.schedule.getLessons().contains(l)) {
					s.coursesAndMarks.entrySet().stream().filter(c -> c.getKey().equals(l.getLessonCourse())).forEach(c -> c.getValue().launchAttendance());
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error!");
		}
	}
	
	public void createTask() {
		System.out.println("Create task:(it's name and type(HW, MID) capital letters");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String taskPP = br.readLine();
			StringTokenizer st = new StringTokenizer(taskPP, " ");
			TaskPaper taskPaper = new TaskPaper(st.nextToken(), this.userID, LocalDate.now(), TaskPaperType.valueOf(st.nextToken()));
			this.allCreatedTasks.add(taskPaper);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void checkDoneTask(DoneTaskPaper dtp) {
		Student s = Database.findStudentbyID(dtp.getStudentID());
		Course c = s.schedule.findLessonByTeacher(this).getLessonCourse();
		s.coursesAndMarks.get(c).setMark(Math.round((Math.random() * 15 + 15) * 1000)/1000.0);
	}
	public void viewTasks() {
		int i = 1;
		for(TaskPaper tp : this.allCreatedTasks) {
			System.out.println(i + "." +tp);
			i++;
		}
	}
	public void setTasks() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("All lessons:\n" + this.teacherSchedule);
			System.out.println("Type lesson time(starting time 00:00 format, duration and day):");
			String lessonTime = br.readLine();
			this.viewTasks();
			int taskPP = Integer.parseInt(br.readLine());
			StringTokenizer stLesson = new StringTokenizer(lessonTime, ":, ");
			Lesson l = this.teacherSchedule.findLessonByTime(new Time(Integer.parseInt(stLesson.nextToken()), Integer.parseInt(stLesson.nextToken()), Double.parseDouble(stLesson.nextToken()), DayOfWeek.valueOf(stLesson.nextToken())));
			l.getTasks().put(this.allCreatedTasks.elementAt(taskPP - 1), true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error!");
		}	
	}
	public void closeAccessToTask() {
		System.out.println("All lessons:\n" + this.teacherSchedule);
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Type lesson time(starting time 00:00 format, duration and day):");
			String lessonTime = br.readLine();
			StringTokenizer st = new StringTokenizer(lessonTime, ":, ");
			this.viewTasks();
			int taskPP = Integer.parseInt(br.readLine());
			Lesson l = this.teacherSchedule.findLessonByTime(new Time(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Double.parseDouble(st.nextToken()), DayOfWeek.valueOf(st.nextToken())));
//			l.getTasks().put(this.allCreatedTasks.elementAt(taskPP - 1), false);
			l.getTasks().compute(this.allCreatedTasks.elementAt(taskPP - 1), (k, v) -> v = false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error!");
		}
	}
	public void tasks(BufferedReader in) {
		while(true) {
			System.out.print("What to do?\n1.Create task\n2.Set task\n3.Close access to task\n4.leave\n");
			try {
				int action = Integer.parseInt(in.readLine());
				if(action == 1) {
					this.createTask();
				}
				else if(action == 2) {
					this.setTasks();
				}
				else if(action == 3) {
					this.closeAccessToTask();
				}
				else if(action == 4) {
					return;
				}
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void userMenu(BufferedReader input) {
		while(true) {
			super.userMenu(input);
			System.out.println("1.Put mark\n2.Close attestaion\n3.Launch attendance for lesson\n4.Tasks\n5.View marks in lesson\n6.View news");
			try {
				String action = input.readLine();
				if(action.equals("Q")) {
					return;
				}
				int ac = Integer.parseInt(action);
				
				if(ac == 1) {
					this.putMark();
				}
				else if(ac == 2) {
					this.closeAttestaion();
				}
				else if(ac == 3) {
					this.launchAttendance();
				}
				else if(ac == 4) {
					this.tasks(input);
				}
				else if(ac == 5) {
					this.viewStudentsMarksInLesson();
				}
				else if(ac == 6) {
					this.viewNews();
				}
				if(this.researcherAccount != null) {
					System.out.println("7.Do science");
					if(ac == 7) {
						this.researcherAccount.userMenu(input);
					}
				}
			} catch (IOException e) {
				System.out.println("Somethinhg bad happened!");
				e.printStackTrace();
			}
		}
	}
	public String toString() {
		return super.toString()+ " " + this.degree.name();
	}
//	
//	public boolean equals(Object o) {
//		return super.equals(o);
//	}
//	
//	public int hashCode() {
//		return super.hashCode();
//	}
//	
//	public int compareTo(Teacher o) {
//		return super.compareTo(o);
//	}
	
	
}