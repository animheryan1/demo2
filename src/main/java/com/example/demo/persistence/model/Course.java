package com.example.demo.persistence.model;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Course extends AbstractBaseEntity {

    private String name;
    private String instructor;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;

    public Course() {
    }

    public Course(String name, String instructor) {
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

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
