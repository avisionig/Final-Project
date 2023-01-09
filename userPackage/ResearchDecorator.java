package userPackage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.Vector;

import PaperPackage.ResearchPaper;
import nonUserPackage.Book;
import uniSystemPackage.Database;
/**
 * Researcher class, extends in Student- and TeacherResearcher
 *
 */
public class ResearchDecorator extends User implements Research{
	private static final long serialVersionUID = -398218604779845006L;
	protected Vector<ResearchPaper> researches;
	{
		researches = new Vector<ResearchPaper>();
	}
	protected ResearchDecorator(String firstName, String lastName) {
		super(firstName, lastName);
	}

	/**
	 * To write articles, articles can be pusblished then
	 */
	public void doScience() {
		System.out.println("Write article name:");
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			String articleName = input.readLine();
			System.out.println("Write article content:");
			String articleContent = input.readLine();
			this.researches.add(new ResearchPaper(articleName, this.userID, LocalDate.now(), articleContent));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Publishes created articles from vector of research papers. Published articles added to database
	 */
	public void publishResearch() {
		this.viewResearches();
		System.out.println("Choose number of research to publish");
		try {
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			int articleIndex = Integer.parseInt(input.readLine());
			ResearchPaper rp = this.researches.elementAt(articleIndex - 1);
			Database.accessDB().getBooks().add(new Book(rp.getPaperName(), this.firstName+ " " + this.lastName, rp.getDate(), rp.getResearchContent()));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void viewResearches() {
		System.out.println("Researches:");
		for(int i = 0; i < this.researches.size(); i++) {
			System.out.println((i + 1) + "." + this.researches.elementAt(i));
		}
	}
	protected void setUserID() {
		this.userID = "SCI0" + String.valueOf((int)(Math.random()*1000+78));
	}
	/**
	 * user menu for researcher, invokes in student or teacher
	 * @see userPackage.Student#userMenu(BufferedReader)
	 *  @see userPackage.Teacher#userMenu(BufferedReader)
	 */
	public void userMenu(BufferedReader input) {
		System.out.println("1.Do Science\n2.Publish work");
		String action;
		try {
			action = input.readLine();
	
			if(action.equals("Q")) {
				return;
			}
			int ac = Integer.parseInt(action);
			if(ac == 1) {
				this.doScience();
			}
			else if(ac == 2) {
				this.publishResearch();
			}
		} 	catch (IOException e) {
			System.out.println("Somethinhg bad happened!");
			e.printStackTrace();
		}
	}

}
