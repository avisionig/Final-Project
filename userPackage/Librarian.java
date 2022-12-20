package userPackage;

import nonUserPackage.Book;
import nonUserPackage.LibrarySubscription;
import uniSystemPackage.Database;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Librarian extends Employee implements LibraryRegister {
    private List<LibrarySubscription> subscriptionList = new ArrayList<>();

    protected Librarian(String firstName, String lastName, LocalDate hireDate) {
        super(firstName, lastName, hireDate);
    }

    public List<LibrarySubscription> getSubscriptionList() {
        return subscriptionList;
    }

    public void setSubscriptionList(List<LibrarySubscription> subscriptionList) {
        this.subscriptionList = subscriptionList;
    }

    public void giveBook(Student student, Book book) {
        if (findLibrarySubscriptionByStudentId(student.getUserID()) == null) {
            addLibrarySubscription(student, book);
        } else {
            updateLibrarySubscription(student, book);
        }
    }

    public void addBook(Book book) {
        Database.getBooks().add(book);
    }

    //--
    @Override
    public void showBooks() {
        Database.getBooks().forEach(System.out::println);
    }

    @Override
    public void showSubscriptions() {
        subscriptionList.forEach(System.out::println);
    }

    @Override
    public void addLibrarySubscription(Student student, Book book) {
        String id = String.valueOf((int) (Math.random() * 1000 + 100));
        if (findLibrarySubscriptionById(id) != null) {
            return;
        }
        subscriptionList.add(new LibrarySubscription(id, student, book));
    }

    @Override
    public LibrarySubscription findLibrarySubscriptionByStudentId(String studentId) {
        List<LibrarySubscription> collect = subscriptionList.stream().filter(s -> Objects.equals(s.getStudent().getUserID(), studentId)).collect(Collectors.toList());
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
        List<LibrarySubscription> collect = subscriptionList.stream().filter(s -> Objects.equals(subscriptionId, s.getSubscriptionId())).collect(Collectors.toList());
        if (collect.size() == 0) {
            return null;
        }
        return collect.get(0);
    }

    @Override
    public List<LibrarySubscription> findAllLibrarySubscriptions() {
        return new ArrayList<>(subscriptionList);
    }

    @Override
    public void updateLibrarySubscription(Student student, Book book) {
        LibrarySubscription librarySubscription = findLibrarySubscriptionByStudentId(student.getUserID());
        int i = findAllLibrarySubscriptions().indexOf(librarySubscription);
        if (librarySubscription == null) {
            return;
        }
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

    @Override
    void setUserID() {
        this.userID = String.valueOf("21B0" + String.valueOf((int) (Math.random() * 1000 + 78)));
    }
}
