package nonUserPackage;

import java.io.Serializable;

import java.util.Vector;
import userPackage.Teacher;
import java.time.DayOfWeek;


public class Schedule implements Serializable{

	private static final long serialVersionUID = -8852617912468901608L;
	protected Vector<Lesson> lessons;
	{
		lessons = new Vector<Lesson>();
	}
	public void addLesson(Lesson les) {
		for(Lesson l : lessons) {
			if(l.timeEquality(les) || (l.timeEquality(les) && l.roomEquality(les))) {
				System.out.println("There is a collision!");
				return;
			}
		}
		lessons.add(les);
		System.out.println("Lesson is added!");
	}
	public Lesson findLessonByFullInfo(Time t, Teacher teacher, Course c, int roomt) {
		Lesson l = new Lesson(t, teacher, c, roomt);
		for(Lesson les : lessons) {
			if (les.equals(l) ) {
				return les;
			}
		}
		return null;
	}
	public Lesson findLessonByTeacher(Teacher t) {
		for(Lesson les : lessons) {
			if(les.lessonTeacher.equals(t)) {
				return les;
			}
		}
		return null;
	}
	public Lesson findLessonByCourse(Course c) {
		for(Lesson l : lessons) {
			if(l.courseLesson.equals(c)) {
				return l;
			}
		}
		return null;
	}
	public Lesson findLessonByTime(Time t) {
		for(Lesson les : lessons) {
			if (les.getTime().equals(t) ) {
				return les;
			}
		}
		return null;
	}
	public Vector<Lesson> getLessons(){
		return this.lessons;
	}
	public String toString() {
		String schedule = new String();
		for(DayOfWeek d : DayOfWeek.values()) {
			schedule += d.name() + ":\n";
			for(Lesson l : lessons) {
				if(l.getTime().getDayOfWeek().equals(d)) {
					schedule += l + "\n";
				}
			}
		}
		return schedule;
	}
}
