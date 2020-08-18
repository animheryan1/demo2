package com.example.demo.rest;

import com.example.demo.persistence.model.Course;
import com.example.demo.persistence.repository.CourseRepository;
import com.example.demo.rest.converters.CourseConverter;
import com.example.demo.rest.model.course.CourseResponseModel;
import com.example.demo.rest.model.course.CreateCourseRequestModel;
import com.example.demo.rest.model.course.UpdateCourseRequestModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CourseController {

    private final CourseRepository courseRepository;
    private final CourseConverter courseConverter;


    public CourseController(CourseRepository courseRepository, CourseConverter courseConverter) {
        this.courseRepository = courseRepository;
        this.courseConverter = courseConverter;
    }

    @PostMapping(value = "course")
    public ResponseEntity<CourseResponseModel> create(@RequestBody CreateCourseRequestModel createCourseRequestModel) {
        Course course = new Course(createCourseRequestModel.getName(), createCourseRequestModel.getInstructor());
        Course savedCourse = courseRepository.save(course);
        CourseResponseModel convert = courseConverter.convert(savedCourse);
        return ResponseEntity.ok(convert);
    }

    @GetMapping(value = "course/{id}")
    public ResponseEntity<CourseResponseModel> read(@PathVariable(value = "id") Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isPresent()) {
            return ResponseEntity.ok(courseConverter.convert(optionalCourse.get()));
        }
        throw new RuntimeException();
    }

    @GetMapping(value = "course")
    public ResponseEntity<List<CourseResponseModel>> readAll() {
        List<Course> allCourses = courseRepository.findAll();
        return ResponseEntity.ok(allCourses.stream().map(courseConverter::convert).collect(Collectors.toList()));
    }

    @PutMapping(value = "course/{id}")
    public ResponseEntity<CourseResponseModel> update(@PathVariable(value = "id") Long id, @RequestBody UpdateCourseRequestModel updateCourseRequestModel) {
        Course course = courseRepository.findById(id).orElseThrow(RuntimeException::new);
        course.setName(updateCourseRequestModel.getName());
        course.setInstructor(updateCourseRequestModel.getInstructor());
        CourseResponseModel convert = courseConverter.convert(courseRepository.save(course));
        return ResponseEntity.ok(convert);
    }

    @DeleteMapping(value = "course/{id}")
    public boolean delete(@PathVariable(value = "id") Long id){
        courseRepository.deleteById(id);
        return !courseRepository.existsById(id);
    }
}
