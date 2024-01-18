package com.example.demo;

public class User_User {
    private long id;
    private String username;
    private int age;
    public User_User(long id, String username, int age) {
        this.id = id;
        this.username = username;
        this.age = age;
    }
    public long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", age=" + age + "]";
    }
}
