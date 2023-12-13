package com.example.beautysaloneeservlets.model.entity;

import com.example.beautysaloneeservlets.model.entity.enums.Days;
import com.example.beautysaloneeservlets.model.entity.enums.Role;

import java.util.HashMap;
import java.util.List;


public class User extends Entity {

    private String name;
    private String surname;
    private String password;
    private String email;
    private Role role;

    private List<Days> workingDays;

    private Integer rating;

    private HashMap<String, Boolean> timeslots;


    public User() {
    }


    public User(String name, String surname, String password, String email, Role role) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
        this.role = role;
    }


    public User(String name, String surname, String password, String email) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Days> getWorkingDays() {
        return workingDays;
    }


    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public void setWorkingDays(List<Days> workingDays) {
        this.workingDays = workingDays;
    }

    public HashMap<String, Boolean> getTimeslots() {
        return timeslots;
    }

    public void setTimeslots(HashMap<String, Boolean> timeslots) {
        this.timeslots = timeslots;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", workingDays=" + workingDays +
                ", rating=" + rating +
                '}';
    }
}
