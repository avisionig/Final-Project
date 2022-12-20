package databases;

import models.LibrarySubscription;

import java.util.Vector;

public class LibrarySubscriptionDB {
    public static Vector<LibrarySubscription> listLibSubscriptions;

    static {
        listLibSubscriptions = new Vector<>();
        listLibSubscriptions.add(new LibrarySubscription("0", StudentsBD.students.get(0), BooksDB.books.get(0)));
    }


}
