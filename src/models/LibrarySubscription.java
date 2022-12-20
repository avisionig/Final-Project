package models;

import models.student.Student;
import nonUserPackage.Book;

public class LibrarySubscription {
    private String subscriptionId;
    private Student student;
    private Book book;

    public LibrarySubscription(String subscriptionId, Student student, Book book) {
        this.subscriptionId = subscriptionId;
        this.student = student;
        this.book = book;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "LibrarySubscription: " +
                "subscriptionId: " + subscriptionId+
                " | student: " + student +
                " | book: " + book;
    }
}
