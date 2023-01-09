package PaperPackage;

import java.time.LocalDate;
import java.util.Objects;
/**
 * We had idea, but wasn't realized(
 *
 */
public class Document extends Paper{
	
	private static final long serialVersionUID = -4730063535945046635L;
	protected String description;
	public Document(String paperName, String sender, LocalDate date, String description) {
		super(paperName, sender, date);
		this.description = description;
	}
	
	public boolean equals(Object o) {
		if(!super.equals(o)) return false;
		Document d = (Document)o;
		return this.description.equals(d.description);
	}
	
	public int hashCode() {
		return Objects.hash(this.paperName, this.sender, this.date, this.description);
	}
	
	public int compareTo(Document o) {
		return super.compareTo(o);
	}
	
	public String toString() {
		return super.toString();
	}
}

