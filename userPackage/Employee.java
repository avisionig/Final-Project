package userPackage;

import java.time.LocalDate;

public abstract class Employee extends User{

	private static final long serialVersionUID = -4441041619534116664L;

	private double salary;
    protected LocalDate hireDate;
	protected Employee(String firstName, String lastName, LocalDate hireDate) {
		super(firstName, lastName);
		this.hireDate = hireDate;
		this.salary = Math.random() * 10000 + 1000;
	}
	
	abstract void setUserID();

	public LocalDate getHireDate() {
		return hireDate;
	}
	public double getSalary() {
		return this.salary;
	}
	public void sendMessage(String userID){
		
	}

}
