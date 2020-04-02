package nl.saxion.lh.persistent.Activity;

public class User {
    private String name;
    private String email;
    private String phone;
    private String github;
    private String point;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }

    public User(String name, String email, String phone, String github, String point) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.github = github;
        this.point = point;
    }
}
