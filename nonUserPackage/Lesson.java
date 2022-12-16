package nonUserPackage;
import java.io.Serializable;
import userPackage.Teacher;

public class Lesson implements Serializable{

	private static final long serialVersionUID = 6064200216371872457L;
	protected Time timeOfLesson;
	protected Teacher lessonTeacher;
	protected Course courseLesson;
	
	public Lesson (Time timeOfLesson, Teacher lessonTeacher, Course courseLesson) {
		this.timeOfLesson = timeOfLesson;
		this.lessonTeacher = lessonTeacher;
		this.courseLesson = courseLesson;
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
		return this.timeOfLesson.equals(l.timeOfLesson) && this.lessonTeacher.equals(l.lessonTeacher) && this.courseLesson.equals(l.courseLesson);
	}
	
	public String toString() {
		return this.courseLesson.getCourseName()+ " | " + this.lessonTeacher.getFirstName() + " " + this.lessonTeacher.getLastName() + " | " + this.timeOfLesson + ")";
	}
}
