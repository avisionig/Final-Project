package nonUserPackage;

import java.io.Serializable;
import java.time.LocalDate;

import java.util.Objects;

public class Book implements Serializable{
   
	private static final long serialVersionUID = 2942921656208602622L;
	private String bookId, name, author;
    private LocalDate publishDate;
    public Book( String name, String author, LocalDate publishDate) {
        this.name = name;
        this.author = author;
        this.publishDate = publishDate;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = "BOOK0" + String.valueOf((int)(Math.random()*100+69));
    }

    public String getName() {
        return name;
    }


    public String getAuthor() {
        return author;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Book)) return false;
        Book b = (Book) o;
        return bookId.equals(b.bookId) && name.equals(b.name) && author.equals(b.author) && publishDate.equals(b.publishDate); 
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, name, author, publishDate);
    }
    
    public String toString() {
    	return "(" + "ID: " + getBookId() + " | " + "Name: " + getName() + " | " +
    			"Author: " + getAuthor() + " | " + "Published date: " + getPublishDate() + ")";
    }
}
