package nonUserPackage;

import java.util.Date;

public class DoneTaskPaper extends Paper{
	
	protected DoneTaskPaper(String paperName, String sender, Date date) {
		super(paperName, sender, date);
	}
	
	public boolean equals(Object o) {
		return super.equals(o);
	}
	
	public int hashCode() {
		return super.hashCode();
	}
	
	public int compareTo(DoneTaskPaper o) {
		return super.compareTo(o);
	}
	
	public String toString() {
		return super.toString();
	}
}
