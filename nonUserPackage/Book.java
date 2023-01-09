package nonUserPackage;

import java.io.Serializable;
import java.time.LocalDate;

import java.util.Objects;
/**
 * It is a book, quite understandable. Have content that can be read by Student. Created by Librarian, all books are saved in Database
 * @see userPackage.Student#readBooks()
 * @see userPackage.Librarian#createBook() 
 *@see uniSystemPackage.Database
 */
public class Book implements Serializable{
   
	private static final long serialVersionUID = 2942921656208602622L;
	private String bookID, name, author, content;
    private LocalDate publishDate;
    public Book( String name, String author, LocalDate publishDate, String content) {
        this.name = name;
        this.author = author;
        this.publishDate = publishDate;
        this.content = content;
        this.setBookId();
    }

    public String getBookID() {
        return bookID;
    }
    public String getContent() {
    	return this.content;
    }
    private void setBookId() {
        this.bookID = "BOOK0" + String.valueOf((int)(Math.random()*100+69));
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
        return bookID.equals(b.bookID) && name.equals(b.name) && author.equals(b.author) && publishDate.equals(b.publishDate) && this.content.equals(b.content); 
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookID, name, author, publishDate);
    }
    
    public String toString() {
    	return "(" + "ID: " + getBookID() + " | " + "Name: " + getName() + " | " +
    			"Author: " + getAuthor() + " | " + "Published date: " + getPublishDate() + ")";
    }
}
