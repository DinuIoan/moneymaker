package com.bestapps.moneymaker.model;

public class Profile {
    private Long id;
    private String name;
    private String password;
    private String email;
    private String date;
    private int active;

    public Profile() {
    }

    public Profile(Long id, String name, String password, String email, String date, int active) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.date = date;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
