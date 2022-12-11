package models;

public class Requests extends User{

    private String id;
    private String title;
    private String description;
    private int employeeId;

    public Requests(String userId, String login, String password, String firstName, String lastName, String id, String title, String description, int employeeId) {
        super(userId, login, password, firstName, lastName);
        this.id = id;
        this.title = title;
        this.description = description;
        this.employeeId = employeeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
