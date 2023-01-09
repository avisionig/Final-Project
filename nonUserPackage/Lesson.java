package nonUserPackage;
import java.io.Serializable;


import java.util.HashMap;
import java.util.Map.Entry;

import PaperPackage.*;
import uniSystemPackage.Database;
import userPackage.Student;
import userPackage.Teacher;
/**
 * Class of lesson, Teacher and Student have lessons in their schedule. Tasks that were created by Teacher can be available in Lesson class.
 * Have field of time, room, course and teacher.
 *@see nonUserPackage.Schedule
 */
public class Lesson implements Serializable{

	private static final long serialVersionUID = 6064200216371872457L;
	protected Time timeOfLesson;
	protected Teacher lessonTeacher;
	protected Course courseLesson;
	protected int room;
	protected HashMap <TaskPaper, Boolean> tasks;{
		tasks = new HashMap<TaskPaper, Boolean>();
	}
	public Lesson (Time timeOfLesson, Teacher lessonTeacher, Course courseLesson, int room) {
		this.timeOfLesson = timeOfLesson;
		this.lessonTeacher = lessonTeacher;
		this.courseLesson = courseLesson;
		this.room = room;
	}
	public int getRoom() {
		return this.room;
	}
	public void setRoom(int room) {
		this.room = room;
	}
	public Course getLessonCourse() {
		return this.courseLesson;
	}
	public Teacher getLessonTeacher() {
		return this.lessonTeacher;
	}

	public Time getTime() {
		return this.timeOfLesson;
	}
	public boolean equals(Object o) {
		if(o == null) return false;
		Lesson l = (Lesson) o;
		return this.room == l.room && this.timeOfLesson.equals(l.timeOfLesson) && this.lessonTeacher.equals(l.lessonTeacher) && this.courseLesson.equals(l.courseLesson);
	}
	/**
	 * To check in 2 lessons have collision.
	 * @see nonUserPackage.Schedule#addLesson(Lesson)
	 * @param l
	 * @return boolean, true if there is collision (13:00-15:00 and 14:00-16:00 have collision), else false.
	 */
	public boolean timeCollsision(Lesson l) {
		return this.timeOfLesson.hourCollision(l.getTime());
	}
	public boolean roomEquality(Lesson l) {
		return this.room == l.room;
	}
	public HashMap<TaskPaper, Boolean> getTasks(){
		return this.tasks;
	}
	/**
	 * @see userPackage.Teacher#viewStudentsMarksInLesson()
	 */
	public void viewMarksOfStudents() {
		for(Student s : Database.accessDB().getStudents()) {
			if(s.getSchedule().lessons.contains(this))
			System.out.println(s.nameAndID()+ " " +s.getCoursesAndMarks().get(this.courseLesson));
		}
	}
	public void viewTasks() {
		for(Entry<TaskPaper, Boolean> tp : this.tasks.entrySet()) {
			if(tp.getValue().equals(true)) {
				System.out.println(tp.getKey());
			}
		}
	}
	/**
	 * @see userPackage.Student#checkTasks()
	 * @param name
	 * @param tp
	 * @return task that will be used to create donetask
	 */
	public TaskPaper getTaskPaperByNameAndType(String name, TaskPaperType tp) {
		for(Entry<TaskPaper, Boolean> tpb: this.tasks.entrySet()) {
			if(tpb.getKey().getPaperName().equals(name) &&tpb.getKey().getTaskType().equals(tp)) {
				return tpb.getKey();
			}
		}
		return null;
	}
	public String toString() {
		return "(" + this.courseLesson.getCourseName()+ " | " + this.lessonTeacher.getFirstName() + " " + this.lessonTeacher.getLastName() + " | time: " + this.timeOfLesson + " | room: " + this.room +")";
	}
}
