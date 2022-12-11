package models;

public class LibrarySubscription {
    private String subscriptionId;
    private String studentId;
    private String bookId;

    public LibrarySubscription(String subscriptionId, String studentId, String bookId) {
        this.subscriptionId = subscriptionId;
        this.studentId = studentId;
        this.bookId = bookId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
