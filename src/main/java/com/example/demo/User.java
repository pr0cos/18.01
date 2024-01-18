package com.example.demo;

import jakarta.persistence.*;

@Entity
@Table(name = "tutorials")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "age")
    private int age;
    public User() {
    }
    public User(String username, String password, int age) {
        this.username = username;
        this.password = password;
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
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    public boolean checkPassword(String password){
        return this.password.equals(password);
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