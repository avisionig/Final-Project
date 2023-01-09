package PaperPackage;

import java.util.Objects;
/**
 * Child class of TaskPaper, created in Student. Created donetask than is "checked" by teacher.
 * @see userPackage.Student#checkTasks()
 * @see userPackage.Teacher#checkDoneTask(DoneTaskPaper) 
 *
 */
public class DoneTaskPaper extends TaskPaper{

	private static final long serialVersionUID = 5757435423744273513L;
	protected TaskPaper task;
	protected String studentID;
	public DoneTaskPaper(TaskPaper task, String studentID) {
		super(task.paperName, task.sender, task.date, task.taskType);
		this.studentID = studentID;
	}
	public String getStudentID() {
		return this.studentID;
	}
	public boolean equals(Object o) {
		if(!super.equals(o)) return false;
		DoneTaskPaper dtp = (DoneTaskPaper)o;
		return this.studentID.equals(dtp.studentID);
	}
	
	public int hashCode() {
		return Objects.hash(this.paperName, this.sender, this.date, this.taskType, this.studentID);
	}
	
	public int compareTo(TaskPaper o) {
		return super.compareTo(o);
	}
	
	public String toString() {
		return super.toString() + " | " + this.studentID;
	}
}
