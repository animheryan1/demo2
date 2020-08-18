package com.example.demo.rest.converters;

import com.example.demo.persistence.model.Student;
import com.example.demo.rest.model.student.StudentResponseModel;
import org.springframework.stereotype.Component;

@Component
public class StudentConverter {
    public StudentResponseModel convert(Student student){
        return new StudentResponseModel(student.getId(), student.getName(), student.getSurname());
    }
}
