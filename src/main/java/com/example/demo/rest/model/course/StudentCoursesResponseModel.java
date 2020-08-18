package com.example.demo.rest.model.course;

import java.util.Set;

public class StudentCoursesResponseModel {
    private Long id;
    private String name;
    private Set<StudentCourseResponseModel> courses;

    public StudentCoursesResponseModel(Long id, String name, Set<StudentCourseResponseModel> courses) {
        this.id = id;
        this.name = name;
        this.courses = courses;
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

    public Set<StudentCourseResponseModel> getCourses() {
        return courses;
    }

    public void setCourses(Set<StudentCourseResponseModel> courses) {
        this.courses = courses;
    }
}
