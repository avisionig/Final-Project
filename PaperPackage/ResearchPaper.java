package PaperPackage;

import java.time.LocalDate;
import java.util.Objects;
/**
 * Child class of Paper.
 * ResearchPaper is created in Researcher class student either teacher, can be published as book.
 * Has content field of what in research.
 *
 */
public class ResearchPaper extends Paper {

	private static final long serialVersionUID = 1525333223133379900L;
	
	protected String researchContent;
	public ResearchPaper(String paperName, String sender, LocalDate date, String content) {
		super(paperName, sender, date);
		this.researchContent = content;
	}
	public int hashCode() {
		return Objects.hash(this.paperName, this.sender, this.date, this.researchContent);
	}
	public boolean equals(Object o) {
		if(!super.equals(o))return false;
		ResearchPaper rp = (ResearchPaper)o;
		return this.researchContent.equals(rp.researchContent);
	}
	public String getResearchContent() {
		return this.researchContent;
	}
	public String toString() {
		return this.paperName + " | " + this.sender + " | " + this.date + " | " + this.researchContent; 
	}
}
