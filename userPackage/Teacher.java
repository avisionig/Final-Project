package userPackage;

import java.time.LocalDate;

public class Teacher extends Employee{
	
	private static final long serialVersionUID = 1051055956846350581L;
	private static int teacherNum = 0;
	protected Teacher(String firstName, String lastName, LocalDate hireDate) {
		super(firstName, lastName, hireDate);
		this.setUserID();
	}
	
	{
		teacherNum++;
	}

	protected void setUserID() {
		this.userID = String.valueOf(this.hireDate.getYear() - 2000) + "TEACH0" +String.valueOf(teacherNum);
	}
	
}
