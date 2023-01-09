package nonUserPackage;

import java.io.Serializable;

/**
 * Mark of student connected to some course. Teacher can put scores to mark and student can see them.
 *	@see userPackage.Student
 */
public class Mark implements Serializable{

	private static final long serialVersionUID = 2041843948330859866L;
	
	private int absence = 0;
	private Attestaion first;
	private Attestaion second;
	private FinalAttestaion finalAtt;
	private boolean attendance = false;
	
	{
		first = new Attestaion();
		second = new Attestaion();
		finalAtt = new FinalAttestaion();
	}
	public Mark() {
		
	}
	public void setMark(double point) {
		if(this.first.getStatus()) {
			this.first.putPoint(point);
		}
		else if(this.second.getStatus()) {
			this.second.putPoint(point);
		}
		else if(this.finalAtt.getStatus()){
			this.finalAtt.putPoint(point);
		}
	}
	public void closeAttestaion() {
		if(this.first.getStatus()) {
			this.first.changeStatus();
		}
		else if (this.second.getStatus()) {
			this.second.changeStatus();
			if((this.first.getPoint() + this.second.getPoint() < 29.5) || this.absence > 9) {
				this.finalAtt.changeStatus();
				System.out.println("Bro you got retake(");
			}
		}
		else if(this.finalAtt.getStatus()) {
			if(this.finalAtt.getPoint() < 9.5) {
				System.out.println("Bro you got retake(");
				this.finalAtt.changeStatus();
			}
			if(this.finalAtt.getPoint() < 19.5) {
				System.out.println("Bro you have FX, try again");
			}
		}
	}
	public int getAbsence() {
		return this.absence;
	}
	public void increaseAbsence() {
		this.absence += 1;
	}
	public boolean getAttendanceStatus() {
		return this.attendance;
	}
	public void markAttendance() {
		if(this.attendance) this.attendance = false;
	}
	public void launchAttendance() {
		if(!this.attendance) this.attendance = true; 
	}
	public void closeAttendance() {
		if(this.attendance) this.attendance = false;
	}
	public boolean equals(Object o) {
		if (o == null) return false;
		if (!(o instanceof Mark)) return false;
		Mark m = (Mark)o;
		return this.first.getPoint() == m.first.getPoint() && this.second.getPoint() == m.second.getPoint() && this.finalAtt.getPoint() == m.finalAtt.getPoint(); 
	}
	public String toString() {
		return this.first.getPoint() + " | " + this.second.getPoint() + " | " + this.finalAtt.getPoint();
	}

}
