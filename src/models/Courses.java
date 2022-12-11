package models;

import java.util.Vector;

public class Courses {
    private String idOfCourse;
    private String nameOfCourse;
    private String teacherId;
    private Vector<String> studentId;

    public Courses(String idOfCourse, String nameOfCourse, String teacherId, Vector<String> studentId) {
        this.idOfCourse = idOfCourse;
        this.nameOfCourse = nameOfCourse;
        this.teacherId = teacherId;
        this.studentId = studentId;
    }

    public String getIdOfCourse() {
        return idOfCourse;
    }

    public void setIdOfCourse(String idOfCourse) {
        this.idOfCourse = idOfCourse;
    }

    public String getNameOfCourse() {
        return nameOfCourse;
    }

    public void setNameOfCourse(String nameOfCourse) {
        this.nameOfCourse = nameOfCourse;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public Vector<String> getStudentId() {
        return studentId;
    }

    public void setStudentId(Vector<String> studentId) {
        this.studentId = studentId;
    }


    public String getFullData(){
        return "Courses:" +
                "idOfCourse='" + idOfCourse + '\'' +
                ", nameOfCourse='" + nameOfCourse + '\'' +
                ", teacherId='" + teacherId + '\'' +
                ", studentId=" + studentId;
    }
}
