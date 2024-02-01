package com.ensa.tests.services;

import com.ensa.tests.dtos.StudentDto;
import com.ensa.tests.entities.Student;
import com.ensa.tests.exceptions.NoSuchStudentException;
import com.ensa.tests.exceptions.StudentAlreadyExistsException;
import com.ensa.tests.repos.StudentRepo;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    private StudentService studentService;
    @Mock
    private StudentRepo studentRepo;

    @BeforeEach
    void setUp() {
        studentService=new StudentService(studentRepo);
    }

    @Test
    void canGetAllStudents() {
        // When
        studentService.getAllStudents();
        // Then
        verify(studentRepo).findAll();
    }

    @Test
    void canAddStudent() throws StudentAlreadyExistsException {
        // Given
        StudentDto studentDto= StudentDto.builder()
                .email("med@gmail.com").age(23).username("med")
                .isActive(false).build();

        // When
        Student savedStudent = studentService.addStudent(studentDto);
        // Then
        ArgumentCaptor<Student> studentArgumentCaptor=ArgumentCaptor.forClass(Student.class);
        verify(studentRepo).saveAndFlush(studentArgumentCaptor.capture());

        Student expectedStudent=studentArgumentCaptor.getValue();
        assertThat(expectedStudent).isEqualTo(savedStudent);
    }

    @Test
    void itShouldNotAddStudentWhenEmailAlreadyExists() throws BadRequestException {
        // Given
        StudentDto studentDto= StudentDto.builder()
                .email("med@gmail.com").age(23).username("med")
                .isActive(false).build();

        // When
        given(studentService.doesEmailAlreadyExist(anyString())).willReturn(true);
        // Then
        //studentService.addStudent(studentDto);
        assertThatThrownBy(()->studentService.addStudent(studentDto))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("email has already been taken");

        verify(studentRepo, never()).save(any());
    }


    @Test
    @Disabled
    void getStudentById() {
    }

    @Test
    @Disabled
    void deleteById() {
    }

    @Test
    @Disabled
    void isEmailValid() {
    }
}