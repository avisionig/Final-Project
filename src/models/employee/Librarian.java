package models.employee;

import models.LibrarySubscription;

import java.util.Date;
import java.util.List;

public class Librarian extends Employee{
    private List<LibrarySubscription> subscriptionList;

    public Librarian(String userId, String login, String password, String firstName, String lastName, double salary, Date hireDate, List<LibrarySubscription> subscriptionList) {
        super(userId, login, password, firstName, lastName, salary, hireDate);
        this.subscriptionList = subscriptionList;
    }

    public List<LibrarySubscription> getSubscriptionList() {
        return subscriptionList;
    }

    public void setSubscriptionList(List<LibrarySubscription> subscriptionList) {
        this.subscriptionList = subscriptionList;
    }

    public void giveBook(){}
}
