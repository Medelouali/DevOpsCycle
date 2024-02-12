package com.ensa.tests.web;

import com.ensa.tests.dtos.Response;
import com.ensa.tests.dtos.StudentDto;
import com.ensa.tests.entities.Student;
import com.ensa.tests.exceptions.NoSuchStudentException;
import com.ensa.tests.exceptions.StudentAlreadyExistsException;
import com.ensa.tests.services.StudentService;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/students")
public class StudentController {
    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService accountService) {
        this.studentService = accountService;
    }

    // Method to add a student
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<Response<Student>> addStudent(@RequestBody StudentDto studentDto) throws StudentAlreadyExistsException {
        return studentService.addStudent(studentDto);
    }

    // Method to get a student by ID
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.CONTINUE)
    public ResponseEntity<Response<Student>> getStudentById(@RequestParam("id") String id) throws NoSuchStudentException{

        return studentService.getStudentById(id);
    }

    // Method to get all students
    @RequestMapping(method = RequestMethod.GET, value = "all")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public List<Student> getAllStudents(@RequestParam(value = "page", defaultValue = "0") Integer page ){
        return studentService.getAllStudents(page);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Response<String>> deleteById(@RequestParam("id") String id) throws NoSuchStudentException{
        return studentService.deleteById(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "valid-emails")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Boolean isEmailValid(@RequestParam("email") String email) {
        return studentService.doesEmailAlreadyExist(email);
    }
}
