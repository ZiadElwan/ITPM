package com.khr.justquitit;

public class UserData {
    public String username, password, email, age, startDate, endDate;

    public UserData() {
    }

    public UserData(String username, String password, String email, String age, String startDate, String endDate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.startDate = startDate;
        this.endDate = endDate;

    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
