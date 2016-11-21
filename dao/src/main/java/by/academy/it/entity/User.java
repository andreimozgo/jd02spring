package by.academy.it.entity;

public class User extends AbstractEntity {

    private int id;
    private String login;
    private String password;
    private String userRole;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void setUserId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public int getId() {
        return id;
    }
}

