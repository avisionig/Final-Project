package userPackage;
/**
 * class that takes Student as argument in constructor
 *
 */
public class StudentResearcher extends ResearchDecorator {

	private static final long serialVersionUID = 3002101482604275121L;
	protected StudentResearcher(Student s) {
		super(s.firstName, s.lastName);
		this.userID = s.userID;
	}

}
