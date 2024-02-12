package com.ensa.tests.services;

import com.ensa.tests.dtos.Response;
import com.ensa.tests.dtos.StudentDto;
import com.ensa.tests.entities.Student;
import com.ensa.tests.exceptions.NoSuchStudentException;
import com.ensa.tests.exceptions.StudentAlreadyExistsException;
import com.ensa.tests.repos.AccountRepo;
import com.ensa.tests.repos.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepo studentRepo;
    @Autowired
    public StudentService(StudentRepo accountService) {
        this.studentRepo = accountService;
    }

    // Method to add a student
    public ResponseEntity<Response<Student>> addStudent(StudentDto studentDto) throws StudentAlreadyExistsException {
        // Add any validation or business logic here if needed
        Student student= Student.builder()
                .isActive(studentDto.getIsActive())
                .age(studentDto.getAge())
                .username(studentDto.getUsername())
                .email(studentDto.getEmail())
                .build();
        if(this.doesEmailAlreadyExist(studentDto.getEmail()))
            throw new StudentAlreadyExistsException("This " +
                "email has already been taken");
        studentRepo.saveAndFlush(student);
        return new ResponseEntity<>(
                new Response<>("Student has been saved successfully", student),
                HttpStatus.CREATED);
    }

    // Method to get a student by ID
    public ResponseEntity<Response<Student>> getStudentById(String studentId) throws  NoSuchStudentException{
        if(studentRepo.findById(studentId).isEmpty())
            throw new NoSuchStudentException("There is no student with this id");
        return new ResponseEntity<>(
                new Response<>("Student has been retrieved successfully",
                        studentRepo.findById(studentId).get()),
                HttpStatus.OK);
    }

    public ResponseEntity<Response<String>> deleteById(String studentId) throws NoSuchStudentException {
        if(studentRepo.findById(studentId).isEmpty())
            throw new NoSuchStudentException("There is no student with this id to delete");
        studentRepo.deleteById(studentId);
        return new ResponseEntity<>(
                new Response<>("Student has been deleted successfully",
                        null),
                HttpStatus.OK);
    }

    // Method to get all students
    public List<Student> getAllStudents(Integer page) {
        // Add any additional logic if needed
        if(page<0) throw new IllegalArgumentException("please check the parameters you are passing");
        return studentRepo.findAll(PageRequest.of(page, 10)).toList();
    }

    public Boolean doesEmailAlreadyExist(String email) {
        return studentRepo.selectExistsEmail(email);
    }
}
