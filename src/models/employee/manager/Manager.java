package models.employee.manager;

import databases.NewsDB;
import models.CRUD;
import models.News;
import models.employee.Employee;

import java.time.LocalDate;
import java.util.List;

public class Manager extends Employee {
    private ManagerType type;
    private List<News> listNews;

    public Manager(String userId, String login, String password, String firstName, String lastName, double salary, LocalDate hireDate, ManagerType type, List<News> listNews) {
        super(userId, login, password, firstName, lastName, salary, hireDate);
        this.type = type;
        this.listNews = listNews;
    }

    public ManagerType getType() {
        return type;
    }

    public void setType(ManagerType type) {
        this.type = type;
    }

    public List<News> getListNews() {
        return listNews;
    }

    public void setListNews(List<News> listNews) {
        this.listNews = listNews;
    }

    public void manageNews(News n, CRUD crud){
        if (crud == CRUD.CREATE){
            NewsDB.newsList.add(new News("321", "ENDTERM", "ENDTERM etc..."));
        } else if (crud == CRUD.DELETE) {
            NewsDB.newsList.remove(n);
        } else if (crud == CRUD.UPDATE) {

        } else if (crud == CRUD.READ) {
            for (News news : NewsDB.newsList) {
                System.out.println(news);
            }
        }
    }
    public void manageRequests(){};
    public void viewStudents(){};
    public void viewTeachers(){};
    public void statisticReport(){};
    public void assignCourses(){};
    public void changeFinance(){};
    public void createQuestionnaire(){};

//    @Override
//    public void showProfile() {
//        System.out.println("Manager{" +
//                "type=" + type +
//                ", listNews=" + listNews);
//    }
}
