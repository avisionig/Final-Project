package models.employee;

import models.paper.ResearchPaper;

import java.util.Date;
import java.util.Vector;

public class Researcher extends Employee{
    private Vector<ResearchPaper> projects;

    public Researcher(String userId, String login, String password, String firstName, String lastName, double salary, Date hireDate, Vector<ResearchPaper> projects) {
        super(userId, login, password, firstName, lastName, salary, hireDate);
        this.projects = projects;
    }

    public Vector<ResearchPaper> getProjects() {
        return projects;
    }

    public void setProjects(Vector<ResearchPaper> projects) {
        this.projects = projects;
    }
}
