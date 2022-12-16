package databases;

import models.LibrarySubscription;

import java.util.ArrayList;
import java.util.List;

public class LibrarySubscriptionDB {
    public static List<LibrarySubscription> listLibSubscriptions;

    static {
        listLibSubscriptions = new ArrayList<>();
        listLibSubscriptions.add(new LibrarySubscription("0", StudentsBD.students.get(0), BooksDB.books.get(0)));
    }


}
