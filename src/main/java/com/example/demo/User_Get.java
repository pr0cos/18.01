package com.example.demo;

public class User_Get {
    private String username;
    private int age;
    private String password;
    private String repeatPassword;

    public User_Get(String username, String password, int age, String repeatPassword) {
        this.username = username;
        this.age = age;
        this.password = password;
        this.repeatPassword = repeatPassword;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean checkPassword(){
        return password.equals(repeatPassword);
    }
}
