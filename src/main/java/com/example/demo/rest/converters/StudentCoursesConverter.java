package com.example.demo.rest.converters;

import com.example.demo.persistence.model.Course;
import com.example.demo.persistence.model.Student;
import com.example.demo.rest.model.course.StudentCourseResponseModel;
import com.example.demo.rest.model.course.StudentCoursesResponseModel;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class StudentCoursesConverter {
    public StudentCoursesResponseModel convert(Student student){
        return new StudentCoursesResponseModel(student.getId(), student.getName(), convertCourses(student));
    }

    private StudentCourseResponseModel convert(Course course){
        return new StudentCourseResponseModel(course.getId(), course.getName());
    }

    private Set<StudentCourseResponseModel> convertCourses(Student student){
        return student.getCourses().stream().map(this::convert).collect(Collectors.toSet());
    }
}
