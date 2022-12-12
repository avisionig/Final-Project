package nonUserPackage;

import java.io.Serializable;

import uniSystemPackage.Database;

public class Mark implements Serializable{

	private static final long serialVersionUID = 2041843948330859866L;
	
	protected String teacherID;
	protected String studentID;
	protected String courseID;
	private double firstAttestation = 0;
	private boolean firstClosed = true;
	private double secondAttestation = 0;
	private boolean secondClosed = true;
	private double finalPoints = 0;
	private boolean finalClosed = true;
	{
		Database.getMarks().add(this);
	}
	public Mark(String stud, String teach, String course) {
		this.studentID = stud;
		this.teacherID = teach;
		this.courseID = course;
	}
	public void setMark(double point) {
		if(!this.firstClosed) {
			this.firstAttestation += point;
		}
		else if(!this.secondClosed) {
			this.secondAttestation += point;
		}
		else if(!this.finalClosed){
			this.finalPoints += point;
		}
	}
	public void closeAttestaion() {
		if(this.firstClosed) {
			this.finalClosed = false;
		}
		else if (this.secondClosed) {
			this.secondClosed = false;
		}
		else if(this.finalClosed) {
			this.finalClosed = false;
		}
	}
	public String toString() {
		return this.firstAttestation + " " + this.secondAttestation + " " + this.finalPoints;
	}
	public String getTeacherID() {
		return this.teacherID;
	}
	public String getCourseID() {
		return this.courseID;
	}

}
