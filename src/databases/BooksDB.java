package databases;

import nonUserPackage.Book;

import java.time.LocalDate;
import java.util.Vector;

public class BooksDB {
    public static Vector<Book> books;

    static {
        books = new Vector<>();

        books.add(new Book("book1", "Book",  LocalDate.of(2005, 1, 1)));
    }
}
