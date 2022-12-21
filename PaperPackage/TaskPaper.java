package PaperPackage;

import java.time.LocalDate;
import java.util.Objects;

public class TaskPaper extends Paper{

	private static final long serialVersionUID = 3364195740706364585L;
	protected TaskPaperType taskType;
	public TaskPaper(String paperName, String teacherName, LocalDate date, TaskPaperType taskType) {
		super(paperName, teacherName, date);
		this.taskType = taskType;
	}
	public TaskPaperType getTaskType() {
		return this.taskType;
	}
	public boolean equals(Object o) {
		return super.equals(o);
	}
	
	public int hashCode() {
		return Objects.hash(this.paperName, this.sender, this.date, this.taskType);
	}
	
	public int compareTo(TaskPaper o) {
		return super.compareTo(o);
	}
	
	public String toString() {
		return super.toString() + " " + this.taskType.name();
	}
}
