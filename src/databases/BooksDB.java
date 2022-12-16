package databases;

import models.Book;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BooksDB {
    public static List<Book> books;

    static {
        books = new ArrayList<>();

        try {
            books.add(new Book("book1", "Book", "Author", new SimpleDateFormat("MM/dd/yyyy").parse("12/15/1996")));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
