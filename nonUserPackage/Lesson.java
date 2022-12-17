package nonUserPackage;
import java.io.Serializable;
import userPackage.Teacher;

public class Lesson implements Serializable{

	private static final long serialVersionUID = 6064200216371872457L;
	protected Time timeOfLesson;
	protected Teacher lessonTeacher;
	protected Course courseLesson;
	protected int room;
	
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
		return this.room == l.room && this.timeOfLesson.equals(l.timeOfLesson);
	}
	public String toString() {
		return "(" + this.courseLesson.getCourseName()+ " | " + this.lessonTeacher.getFirstName() + " " + this.lessonTeacher.getLastName() + " | time: " + this.timeOfLesson + " | room: " + this.room +")";
	}
}
