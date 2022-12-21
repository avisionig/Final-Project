package userPackage;

public class TeacherResearcher extends ResearchDecorator implements Research{
	
	private static final long serialVersionUID = 119143833619738912L;
	protected TeacherResearcher(Teacher t) {
		super(t.firstName, t.lastName);
		this.userID = t.userID;
	}

}
