package nonUserPackage;

import java.util.Date;
import java.util.Objects;

public class Paper {
	protected String paperName, sender;
	protected Date date;
	
	protected Paper(String paperName, String sender, Date date) {
		this.paperName = paperName;
		this.sender = sender;
		this.date = date;
	}
	
	public String getPaperName() {
		return paperName;
	}
	
//	public void setPaperName() {
//		this.paperName = paperName;
//	}
	
	public String getSender() {
		return sender;
	}
	
//	public void setSender() {
//		this.sender = sender;
//	}
	
	public Date getDate() {
		return date;
	}
	
//	public void setDate() {
//		this.date = date;
//	}
	
	public int compareTo(Paper o) {
		if(this.sender.compareToIgnoreCase(o.sender) == 1) return 1;
		else if(this.sender.compareToIgnoreCase(o.sender) == -1) return -1;
		return 0;
	}
	
	public boolean equals(Object o) {
		if(o == null) return false;
		Paper p = (Paper) o;
		return paperName.equals(p.paperName) && sender.equals(p.sender) && date.equals(p.date);
	}
	
	public int hashCode() {
		return Objects.hash(this.paperName);
	}
	
	public String toString() {
		return this.paperName + " | " + this.sender + " | " + this.date; 
	}
	
}
