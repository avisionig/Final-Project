package nonUserPackage;

import java.util.Date;
import java.util.Objects;

public class Book {
    private String bookId, name, author;
    private Date publishDate;

    public Book(String bookId, String name, String author, Date publishDate) {
        this.bookId = bookId;
        this.name = name;
        this.author = author;
        this.publishDate = publishDate;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
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
    			"Author: " + getAuthor() + " | " + "Published date: " + getPublishDate();
    }
}
