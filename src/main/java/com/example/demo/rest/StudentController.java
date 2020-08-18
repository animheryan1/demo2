package com.example.demo.rest;

import com.example.demo.persistence.model.Course;
import com.example.demo.persistence.model.Student;
import com.example.demo.persistence.repository.CourseRepository;
import com.example.demo.persistence.repository.StudentRepository;
import com.example.demo.rest.converters.StudentConverter;
import com.example.demo.rest.converters.StudentCoursesConverter;
import com.example.demo.rest.model.course.StudentCoursesResponseModel;
import com.example.demo.rest.model.student.CreateStudentRequestModel;
import com.example.demo.rest.model.student.StudentResponseModel;
import com.example.demo.rest.model.student.UpdateStudentRequestModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class StudentController {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentConverter studentConverter;
    private final StudentCoursesConverter studentCoursesConverter;
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);


    public StudentController(StudentRepository studentRepository, StudentConverter studentConverter, CourseRepository courseRepository, StudentCoursesConverter studentCoursesConverter) {
        this.studentRepository = studentRepository;
        this.studentConverter = studentConverter;
        this.courseRepository = courseRepository;
        this.studentCoursesConverter = studentCoursesConverter;
    }

    @PostMapping(value = "student")
    public ResponseEntity<StudentResponseModel> create(@RequestBody CreateStudentRequestModel createStudentRequestModel) {
        Student student = new Student(createStudentRequestModel.getName(), createStudentRequestModel.getSurname());
        Student savedStudent = studentRepository.save(student);
        StudentResponseModel convert = studentConverter.convert(savedStudent);
        return ResponseEntity.ok(convert);
    }

    @GetMapping(value = "student/{id}")
    public ResponseEntity<StudentResponseModel> read(@PathVariable(value = "id") Long id) {
        LOGGER.info("Creating student");
        LOGGER.trace("Creating student trace");
        LOGGER.warn("Create warn");
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            return ResponseEntity.ok(studentConverter.convert(optionalStudent.get()));
        }
        throw new RuntimeException();
    }

    @GetMapping(value = "student/{id}/courses")
    public ResponseEntity<StudentCoursesResponseModel> readCourses(@PathVariable(value = "id") Long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            return ResponseEntity.ok(studentCoursesConverter.convert(optionalStudent.get()));
        }
        LOGGER.error("No student with id - {}", id);
        throw new RuntimeException();
    }

    @GetMapping(value = "student")
    public ResponseEntity<List<StudentResponseModel>> readAll() {
        List<Student> allStudents = studentRepository.findAll();
        return ResponseEntity.ok(allStudents.stream().map(studentConverter::convert).collect(Collectors.toList()));
    }

    @PutMapping(value = "student/{id}")
    public ResponseEntity<StudentResponseModel> update(@PathVariable(value = "id") Long id, @RequestBody UpdateStudentRequestModel updateStudentRequestModel) {
        Student student = studentRepository.findById(id).orElseThrow(RuntimeException::new);
        student.setName(updateStudentRequestModel.getName());
        student.setSurname(updateStudentRequestModel.getSurname());
        Set<Course> courses = new HashSet<>(courseRepository.findAllById(updateStudentRequestModel.getCourseIDs()));
        student.setCourses(courses);
        StudentResponseModel convert = studentConverter.convert(studentRepository.save(student));
        return ResponseEntity.ok(convert);
    }

    @DeleteMapping(value = "student/{id}")
    public boolean delete(@PathVariable(value = "id") Long id) {
        studentRepository.deleteById(id);
        return !studentRepository.existsById(id);
    }
}
