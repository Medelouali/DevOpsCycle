package com.ensa.tests.web;

import com.ensa.tests.dtos.StudentDto;
import com.ensa.tests.entities.Student;
import com.ensa.tests.services.StudentService;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/students")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;

    // Method to add a student
    @RequestMapping(method = RequestMethod.POST)
    public Student addStudent(@RequestBody StudentDto studentDto) throws BadRequestException {
        // Add any validation or business logic here if needed
        Student student= Student.builder()
                .isActive(studentDto.getIsActive())
                .age(studentDto.getAge())
                .username(studentDto.getUsername())
                .build();
        return studentService.addStudent(studentDto);
    }

    // Method to get a student by ID
    @RequestMapping(method = RequestMethod.GET)
    public Optional<Student> getStudentById(@RequestParam("id") String id) {
        return studentService.getStudentById(id);
    }

    // Method to get all students
    @RequestMapping(method = RequestMethod.GET, value = "all")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteById(@RequestParam("id") String id) {
        studentService.deleteById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "valid-emails")
    public Boolean isEmailValid(@RequestParam("email") String email) {
        return studentService.doesEmailAlreadyExist(email);
    }
}
