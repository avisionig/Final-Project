package nonUserPackage;

import java.util.Date;

public class Document extends Paper{
	
	protected Document(String paperName, String sender, Date date) {
		super(paperName, sender, date);
	}
	
	public boolean equals(Object o) {
		return super.equals(o);
	}
	
	public int hashCode() {
		return super.hashCode();
	}
	
	public int compareTo(Document o) {
		return super.compareTo(o);
	}
	
	public String toString() {
		return super.toString();
	}
}
