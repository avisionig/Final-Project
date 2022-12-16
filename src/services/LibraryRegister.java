package services;

import models.Book;
import models.LibrarySubscription;
import models.student.Student;

import java.util.List;

public interface LibraryRegister {
    void showBooks();

    void showSubscriptions();

    void addLibrarySubscription(Student student, Book book);

    LibrarySubscription findLibrarySubscriptionByStudentId(String studentId);

    LibrarySubscription findLibrarySubscriptionByBookId(String bookId);

    LibrarySubscription findLibrarySubscriptionById(String subscriptionId);

    List<LibrarySubscription> findAllLibrarySubscriptions();

    void updateLibrarySubscription(Student student, Book book);

    void removeLibrarySubscription(String subscriptionId);
}
