package models;

import models.student.Student;

public class Organization {
    private Student head;

    public Organization(Student head) {
        this.head = head;
    }

    public Student getHead() {
        return head;
    }

    public void setHead(Student head) {
        this.head = head;
    }
}
