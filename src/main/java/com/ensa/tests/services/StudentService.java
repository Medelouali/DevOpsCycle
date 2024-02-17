package com.ensa.tests.services;

import com.ensa.tests.dtos.Response;
import com.ensa.tests.dtos.StudentDto;
import com.ensa.tests.entities.Client;
import com.ensa.tests.exceptions.NoSuchStudentException;
import com.ensa.tests.exceptions.StudentAlreadyExistsException;
import com.ensa.tests.repos.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepo studentRepo;
    @Autowired
    public StudentService(StudentRepo accountService) {
        this.studentRepo = accountService;
    }

    // Method to add a student
    public ResponseEntity<Response<Client>> addStudent(StudentDto studentDto) throws StudentAlreadyExistsException {
        // Add any validation or business logic here if needed
        Client client = Client.builder()
                .isActive(studentDto.getIsActive())
                .age(studentDto.getAge())
                .username(studentDto.getUsername())
                .email(studentDto.getEmail())
                .build();
        if(this.doesEmailAlreadyExist(studentDto.getEmail()))
            throw new StudentAlreadyExistsException("This " +
                "email has already been taken");
        studentRepo.saveAndFlush(client);
        return new ResponseEntity<>(
                new Response<>("Client has been saved successfully", client),
                HttpStatus.CREATED);
    }

    // Method to get a student by ID
    public ResponseEntity<Response<Client>> getStudentById(String studentId) throws  NoSuchStudentException{
        if(studentRepo.findById(studentId).isEmpty())
            throw new NoSuchStudentException("There is no student with this id");
        return new ResponseEntity<>(
                new Response<>("Client has been retrieved successfully",
                        studentRepo.findById(studentId).get()),
                HttpStatus.OK);
    }

    public ResponseEntity<Response<String>> deleteById(String studentId) throws NoSuchStudentException {
        if(studentRepo.findById(studentId).isEmpty())
            throw new NoSuchStudentException("There is no student with this id to delete");
        studentRepo.deleteById(studentId);
        return new ResponseEntity<>(
                new Response<>("Client has been deleted successfully",
                        null),
                HttpStatus.OK);
    }

    // Method to get all students
    public List<Client> getAllStudents(Integer page) {
        // Add any additional logic if needed
        if(page<0) throw new IllegalArgumentException("please check the parameters you are passing");
        return studentRepo.findAll(PageRequest.of(page, 10)).toList();
    }

    public Boolean doesEmailAlreadyExist(String email) {
        return studentRepo.selectExistsEmail(email);
    }
}
