package models.employee;

import models.User;

import java.util.Date;

public abstract class Employee extends User {
    private double salary;
    private Date hireDate;

    public Employee(String userId, String login, String password, String firstName, String lastName, double salary, Date hireDate) {
        super(userId, login, password, firstName, lastName);
        this.salary = salary;
        this.hireDate = hireDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    //todo make abstract method and rename
    public void sendMessage(){};


}
