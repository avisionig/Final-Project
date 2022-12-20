package models.employee;

import models.LibrarySubscription;
import models.student.Student;
import nonUserPackage.Book;
import services.LibraryRegister;
import uniSystemPackage.Database;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Librarian extends Employee implements LibraryRegister {
    private List<LibrarySubscription> subscriptionList;
    private int id = 0;

    public Librarian(String userId, String login, String password, String firstName, String lastName, double salary, LocalDate hireDate, List<LibrarySubscription> subscriptionList) {
        super(userId, login, password, firstName, lastName, salary, hireDate);
        this.subscriptionList = subscriptionList;
    }

    public List<LibrarySubscription> getSubscriptionList() {
        return subscriptionList;
    }

    public void setSubscriptionList(List<LibrarySubscription> subscriptionList) {
        this.subscriptionList = subscriptionList;
    }

    public void giveBook(Student student, Book book) {
        if (findLibrarySubscriptionByStudentId(student.getUserId()) == null) {
            addLibrarySubscription(student, book);
        } else {
            updateLibrarySubscription(student, book);
        }
    }

    public void addBook(Book book) {
        Database.getAllBooks().add(book);
    }

    //--
    @Override
    public void showBooks() {
        Database.getAllBooks().forEach(System.out::println);
    }

    @Override
    public void showSubscriptions() {
        subscriptionList.forEach(System.out::println);
    }

    @Override
    public void addLibrarySubscription(Student student, Book book) {
        subscriptionList.add(new LibrarySubscription(id + "", student, book));
        id++;
    }

    @Override
    public LibrarySubscription findLibrarySubscriptionByStudentId(String studentId) {
        List<LibrarySubscription> collect = subscriptionList.stream().filter(s -> Objects.equals(s.getStudent().getUserId(), studentId)).collect(Collectors.toList());
        if (collect.size() != 0) {
            return collect.get(0);
        }
        return null;
    }

    @Override
    public LibrarySubscription findLibrarySubscriptionByBookId(String bookId) {
        return subscriptionList.stream().filter(s -> Objects.equals(s.getBook().getBookId(), bookId)).collect(Collectors.toList()).get(0);
    }

    @Override
    public LibrarySubscription findLibrarySubscriptionById(String subscriptionId) {
        return subscriptionList.stream().filter(s -> Objects.equals(subscriptionId, s.getSubscriptionId())).collect(Collectors.toList()).get(0);
    }

    @Override
    public List<LibrarySubscription> findAllLibrarySubscriptions() {
        return new ArrayList<>(subscriptionList);
    }

    @Override
    public void updateLibrarySubscription(Student student, Book book) {
        LibrarySubscription librarySubscription = findLibrarySubscriptionByStudentId(student.getUserId());
        int i = findAllLibrarySubscriptions().indexOf(librarySubscription);
        librarySubscription.setBook(book);
        librarySubscription.setStudent(student);
        subscriptionList.set(i, librarySubscription);
    }

    @Override
    public void removeLibrarySubscription(String subscriptionId) {
        LibrarySubscription librarySubscription = findLibrarySubscriptionById(subscriptionId);
        subscriptionList.remove(librarySubscription);
    }

    @Override
    public void removeAll() {
        subscriptionList.clear();
    }
}
