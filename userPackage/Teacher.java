package userPackage;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.StringTokenizer;

import PaperPackage.TaskPaper;
import PaperPackage.TaskPaperType;
import nonUserPackage.*;
import uniSystemPackage.Database;

public class Teacher extends Employee{
	protected Schedule teacherSchedule;
	protected TeacherDegree degree;
	private static final long serialVersionUID = 1051055956846350581L;
	{
		teacherSchedule = new Schedule();
	}
	public Teacher(String firstName, String lastName, LocalDate hireDate, TeacherDegree degree) {
		super(firstName, lastName, hireDate);
		this.degree = degree;
		this.setUserID();
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
	
	public void putMark() throws IOException {
		System.out.println("Choose course by ID");
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
	
	
	public void setTasks() {
		System.out.println("Create task:(it's name and type(HW, MID) capital letters");
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String taskPP = br.readLine();
			StringTokenizer st = new StringTokenizer(taskPP, " ");
			TaskPaper taskPaper = new TaskPaper(st.nextToken(), this.userID, LocalDate.now(), TaskPaperType.valueOf(st.nextToken()));
			System.out.println("All lessons:\n" + this.teacherSchedule);
			System.out.println("Type lesson time(starting time 00:00 format, duration and day):");
			String lessonTime = br.readLine();
			StringTokenizer stLesson = new StringTokenizer(lessonTime, ":, ");
			Lesson l = this.teacherSchedule.findLessonByTime(new Time(Integer.parseInt(stLesson.nextToken()), Integer.parseInt(stLesson.nextToken()), Double.parseDouble(stLesson.nextToken()), DayOfWeek.valueOf(stLesson.nextToken())));
			// TODO view the problem with taskPaper in put on HashMap
			l.getTasks().put(taskPaper, true);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error!");
		}	
	}
//	public String toString() {
//		return super.toString();
//	}
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
