package models.employee.teacher;

import models.Courses;
import models.employee.Employee;

import java.util.Date;
import java.util.Vector;

public class Teacher extends Employee {
    private Vector<Courses> courses;
    private TeacherDegree degree;

    public Teacher(String userId, String login, String password, String firstName, String lastName, double salary, Date hireDate, Vector<Courses> courses, TeacherDegree degree) {
        super(userId, login, password, firstName, lastName, salary, hireDate);
        this.courses = courses;
        this.degree = degree;
    }

    public Vector<Courses> getCourses() {
        return courses;
    }

    public void setCourses(Vector<Courses> courses) {
        this.courses = courses;
    }

    public TeacherDegree getDegree() {
        return degree;
    }

    public void setDegree(TeacherDegree degree) {
        this.degree = degree;
    }

    public void giveHW(){}
    public void putMarks(){}
    public void manageCourseFiles(){}
    public void viewListOfStudents(){}
    public void viewSchedule(){}
    public void viewCourses(){}
    public void leaveRequest(){}
    public void putAttendance(){}
    public void viewInfoAboutStudents(){}

}
