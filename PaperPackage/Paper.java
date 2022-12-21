package PaperPackage;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Paper implements Serializable, Comparable<Paper>{

	private static final long serialVersionUID = -5521859643283924756L;
	protected String paperName, sender;
	protected LocalDate date;
	
	public Paper(String paperName, String sender, LocalDate date) {
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
	
	public LocalDate getDate() {
		return date;
	}
	
//	public void setDate() {
//		this.date = date;
//	}
	
	public int compareTo(Paper o) {
		if(this.paperName.compareToIgnoreCase(o.paperName) == 1) return 1;
		else if(this.paperName.compareToIgnoreCase(o.paperName) == -1) return -1;
		return 0;
	}
	
	public boolean equals(Object o) {
		if(o == null) return false;
		Paper p = (Paper) o;
		return paperName.equals(p.paperName) && sender.equals(p.sender) && date.equals(p.date);
	}
	
	public int hashCode() {
		return Objects.hash(this.paperName, this.sender, this.date);
	}
	
	public String toString() {
		return this.paperName + " | " + this.sender + " | " + this.date; 
	}
	
}
