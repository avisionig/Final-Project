package nonUserPackage;


import java.io.Serializable;
import java.util.Vector;

import userPackage.Student;

public class LibrarySubscription implements Serializable{

	private static final long serialVersionUID = -2163718416223210678L;
	private String subscriptionId;
    private Student student;
    private Vector<Book> books;
    {
    	books = new Vector<Book>();
    }
    public LibrarySubscription() {
    }

    public LibrarySubscription(String subscriptionId, Student student) {
        this.subscriptionId = subscriptionId;
        this.student = student;
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
    public Vector<Book> getBooksInSubscriptions(){
    	return this.books;
    }
   public void addBook(Book book) {
	   this.books.add(book);
   }

    @Override
    public String toString() {
        return "LibrarySubscription: " +
                "subscriptionID: " + subscriptionId+
                " | student: " + student +
                " | books: " + books.size();
    }
}
