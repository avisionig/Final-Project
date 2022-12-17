package nonUserPackage;

import java.io.Serializable;
import java.util.Vector;
import java.util.stream.Stream;

import userPackage.Teacher;

import java.time.DayOfWeek;


public class Schedule implements Serializable{

	private static final long serialVersionUID = -8852617912468901608L;
	protected Vector<Lesson> lessons;
	{
		lessons = new Vector<Lesson>();
	}
	public void addLesson(Lesson lesson) {
		for(Lesson l : lessons) {
			if((l.equals(lesson))) {
				System.out.println("There is a collision!");
				return;
			}
		}
		lessons.add(lesson);
		System.out.println("Lesson is added!");
	}
	public Lesson findLessonByFullInfo(Time t, Teacher teacher, Course c, int roomt) {
		Lesson les = (Lesson) this.lessons.stream().filter(l -> l.equals(new Lesson(t, teacher, c, roomt))).limit(1);
		return les;
	}
	public Lesson findLessonByTeacher(Teacher t) {
		Lesson les = (Lesson) this.lessons.stream().filter(l -> l.getLessonTeacher().equals(t)).limit(1);
		return les;
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
