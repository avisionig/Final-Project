package models.employee;

import models.User;

import java.time.LocalDate;

public abstract class Employee extends User {
    private double salary;
    private LocalDate hireDate;

    public Employee(String userId, String login, String password, String firstName, String lastName, double salary, LocalDate hireDate) {
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

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    //todo make abstract method and rename
    public void sendMessage() {
    }

    ;


}
