package services.impl;

import databases.BooksDB;
import databases.LibrarySubscriptionDB;
import databases.StudentsBD;
import models.Book;
import models.LibrarySubscription;
import models.student.Student;
import services.LibraryRegister;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LibraryRegisterImpl implements LibraryRegister {

    private int id = 0;

    @Override
    public void showBooks() {
        BooksDB.books.forEach(System.out::println);
    }

    @Override
    public void showSubscriptions() {
        LibrarySubscriptionDB.listLibSubscriptions.forEach(System.out::println);
    }

    @Override
    public void addLibrarySubscription(Student student, Book book) {
        LibrarySubscriptionDB.listLibSubscriptions.add(new LibrarySubscription(id + "", student, book));
        id++;
    }

    @Override
    public LibrarySubscription findLibrarySubscriptionByStudentId(String studentId) {
        return LibrarySubscriptionDB.listLibSubscriptions.stream().filter(s -> Objects.equals(s.getStudent().getUserId(), studentId)).collect(Collectors.toList()).get(0);
    }

    @Override
    public LibrarySubscription findLibrarySubscriptionByBookId(String bookId) {
        return LibrarySubscriptionDB.listLibSubscriptions.stream().filter(s -> Objects.equals(s.getBook().getBookId(), bookId)).collect(Collectors.toList()).get(0);
    }

    @Override
    public LibrarySubscription findLibrarySubscriptionById(String subscriptionId) {
        return LibrarySubscriptionDB.listLibSubscriptions.stream().filter(s -> Objects.equals(subscriptionId, s.getSubscriptionId())).collect(Collectors.toList()).get(0);
    }

    @Override
    public List<LibrarySubscription> findAllLibrarySubscriptions() {
        return new ArrayList<>(LibrarySubscriptionDB.listLibSubscriptions);
    }

    @Override
    public void updateLibrarySubscription(Student student, Book book) {

    }

    @Override
    public void removeLibrarySubscription(String subscriptionId) {

    }
}
