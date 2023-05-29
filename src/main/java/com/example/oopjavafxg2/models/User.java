package com.example.oopjavafxg2.models;

import java.time.LocalDate;

public class User {
    private int id;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private String username;
    private String password;
    private boolean accepted;

    public User() {
    }

    public User(int id, String name, String surname, LocalDate birthdate, String username, String password, boolean accepted) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthdate = birthdate;
        this.username = username;
        this.password = password;
        this.accepted = accepted;
    }

    public User(String name, String surname, LocalDate birthdate, String username, String password, boolean accepted) {
        this(0, name, surname, birthdate, username, password, accepted);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
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

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}











