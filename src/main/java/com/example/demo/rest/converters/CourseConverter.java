package com.example.demo.rest.converters;

import com.example.demo.persistence.model.Course;
import com.example.demo.rest.model.course.CourseResponseModel;
import org.springframework.stereotype.Component;

@Component
public class CourseConverter {
    public CourseResponseModel convert(Course course){
        return new CourseResponseModel(course.getId(), course.getName(), course.getInstructor());
    }
}

