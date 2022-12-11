package models.student;

import models.User;

import java.util.Date;

public class Student extends User {
    private Faculty faculty;
    private Date yearOfAdmission;
    private double feeStudy;
    private StudentDegree degree;

    public Student(String userId, String login, String password, String firstName, String lastName, Faculty faculty, Date yearOfAdmission, double feeStudy, StudentDegree degree) {
        super(userId, login, password, firstName, lastName);
        this.faculty = faculty;
        this.yearOfAdmission = yearOfAdmission;
        this.feeStudy = feeStudy;
        this.degree = degree;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Date getYearOfAdmission() {
        return yearOfAdmission;
    }

    public void setYearOfAdmission(Date yearOfAdmission) {
        this.yearOfAdmission = yearOfAdmission;
    }

    public double getFeeStudy() {
        return feeStudy;
    }

    public void setFeeStudy(double feeStudy) {
        this.feeStudy = feeStudy;
    }

    public StudentDegree getDegree() {
        return degree;
    }

    public void setDegree(StudentDegree degree) {
        this.degree = degree;
    }

    public void getMarks() {
    }

    public void doHW() {
    }

    public void viewTranscript() {
    }

    public void createOrganization() {
    }

    public void viewCourseFile() {
    }

    public void viewAttendance() {
    }

    public void registerForDiscipline() {
    }

    public void payFinance() {
    }

    public void fillOutQuestionnaire() {
    }

    public void rateTeacher() {
    }

    public void takeBook() {
    }

    @Override
    public void showProfile() {
        System.out.println("Student: " +
                "faculty=" + faculty +
                ", yearOfAdmission=" + yearOfAdmission +
                ", feeStudy=" + feeStudy +
                ", degree=" + degree);
    }
}
