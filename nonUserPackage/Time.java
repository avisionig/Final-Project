package nonUserPackage;

import java.io.Serializable;
import java.time.DayOfWeek;
/**
 * Custom Time class, because existing wasn't comfortable to use.
 *
 */
public class Time implements Serializable{

	private static final long serialVersionUID = 5522472545885085573L;
	protected int startHour;
	protected double duration;
	protected int startMin;
	protected DayOfWeek dayOfWeek;
	protected int endHour;
	protected int endMin;
	
	public Time(int startHour, int startMin, double duration, DayOfWeek dayOfWeek) {
		this.startMin = startMin;
		this.startHour = startHour;
		this.duration = duration;
		this.dayOfWeek = dayOfWeek;
		this.calculcateEndTime();
	}
	/**
	 * When lesson is created we don't end time, start is enough and this method calculates end time.
	 */
	private void calculcateEndTime() {
		this.endHour = (int)this.duration + this.startHour;
		this.endMin = (int) (this.startMin + (60 * (this.duration - Math.floor(this.duration))));
		if(this.endMin >= 60) {
			this.endMin -= 60;
			this.endHour += 1;
		}
	}
	/**
	 * To change "9" to "09", for example. 
	 * @param num
	 * @return corrected num for toString method.
	 * @see Time#toString()
	 */
	private String zeroCorrector(int num) {
		if(num < 10) {
			return "0" + num;
		}
		return String.valueOf(num);
	}
	public int getStartHour() {
		return this.startHour;
	}
	public int getStartMin() {
		return this.startMin;
	}
	public int getEndHour() {
		return this.endHour;
	}
	public int getEndMin() {
		return this.endMin;
	}
	public DayOfWeek getDayOfWeek() {
		return this.dayOfWeek;
	}
	public double getDuratuin() {
		return this.duration;
	}
	public String toString() {
		return this.zeroCorrector(startHour)+ ":" + this.zeroCorrector(startMin) + " | " + this.zeroCorrector(endHour)+ ":"+ this.zeroCorrector(endMin);
	}
	/**
	 * Collision checker for lesson.
	 * @param t
	 * @return boolean, if true lesson won't be added to schedule.
	 * @see nonUserPackage.Schedule#addLesson(Lesson)
	 */
	public boolean hourCollision(Time t) {
		if(this.startHour <= t.startHour && this.endHour >= t.startHour && this.startHour <= t.endHour && this.endHour <= t.endHour && this.dayOfWeek.equals(t.dayOfWeek)) {
			return true;
		}
		return false;
	}
	public boolean equals(Object o) {
		if(o == null) return false;
		Time t = (Time)o;
		return this.startHour==t.startHour && this.startMin==t.startMin && this.endHour == t.endHour && this.endMin == t.endMin && this.dayOfWeek.equals(t.dayOfWeek);
	}
}