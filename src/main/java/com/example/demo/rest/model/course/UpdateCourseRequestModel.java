package com.example.demo.rest.model.course;

public class UpdateCourseRequestModel {
    private String name;
    private String instructor;
//    private
    //TODO edit student

    public UpdateCourseRequestModel(String name, String instructor) {
        this.name = name;
        this.instructor = instructor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
}
