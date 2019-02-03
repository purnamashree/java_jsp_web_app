package com.venkat.enity;

import java.io.Serializable;

public class Employee implements Serializable {

    private String firstName;
    private String lastName;
    private String title;
    private int id;

    public Employee(){}

    public Employee(int id){
        this.id = id;
    }

    public Employee(String firstName, String lastName, String title, int id) {
        this(firstName, lastName, title);
        this.id = id;
    }

    public Employee(String firstName, String lastName, String title) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
