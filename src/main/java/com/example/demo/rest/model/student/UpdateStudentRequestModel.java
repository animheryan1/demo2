package com.example.demo.rest.model.student;


import java.util.Set;

public class UpdateStudentRequestModel {
    private String name;
    private String surname;
    private Set<Long> courseIDs;

    public UpdateStudentRequestModel(String name, String surname, Set<Long> courseIDs) {
        this.name = name;
        this.surname = surname;
        this.courseIDs = courseIDs;
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

    public Set<Long> getCourseIDs() {
        return courseIDs;
    }

    public void setCourseIDs(Set<Long> courseIDs) {
        this.courseIDs = courseIDs;
    }
}
