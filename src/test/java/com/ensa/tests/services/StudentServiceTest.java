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
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @InjectMocks
    private StudentService studentService;
    @Mock
    private StudentRepo studentRepo;

    @Test
    void itShouldNotGetAllStudentsWhenPageParamIsNegative() {
        // Given
        Integer page=-1;

        // When
        // Then
        assertThatThrownBy(()->studentService.getAllStudents(page)).
                isInstanceOf(IllegalArgumentException.class).
                hasMessageContaining("please check the parameters you are passing");
    }

    @Test
    @Disabled
    void itShouldGetAllStudentsWhenPageParamIsPositive() {
        // Given
        int page=0;
        when(studentRepo.findAll(PageRequest.of(page, 10))).thenReturn(null);

        // When

        // Then
    }

    @Test
    void itShouldAddStudent() throws StudentAlreadyExistsException {
        // Given
        StudentDto studentDto= StudentDto.builder()
                .email("med@gmail.com").age(23).username("med")
                .isActive(false).build();

        // When
        Student savedStudent = studentService.addStudent(studentDto).getBody().getData();
        // Then
        ArgumentCaptor<Student> studentArgumentCaptor=ArgumentCaptor.forClass(Student.class);
        verify(studentRepo).saveAndFlush(studentArgumentCaptor.capture());

        Student expectedStudent=studentArgumentCaptor.getValue();
        assertThat(expectedStudent).isEqualTo(savedStudent);
    }

    @Test
    void itShouldNotAddStudentWhenEmailAlreadyExistsAndThrowException() {
        // Given
        StudentDto studentDto= StudentDto.builder()
                .email("med@gmail.com").age(23).username("med")
                .isActive(false).build();

        // When
        when(studentService.doesEmailAlreadyExist(anyString())).thenReturn(true);
        assertThatThrownBy(()->studentService.addStudent(studentDto))
                .isInstanceOf(StudentAlreadyExistsException.class)
                .hasMessageContaining("email has already been taken");

        verify(studentRepo, never()).save(any());
    }


    @Test
    void getStudentById() throws NoSuchStudentException, StudentAlreadyExistsException {
        // Given
        Student student=Student.builder().username("toto").age(23).email("x@gmail.com").isActive(true).build();

        // When
        when(studentRepo.findById(any())).thenReturn(Optional.of(student));

        // Then
        assertThat(studentService.getStudentById(student.getId()).getBody()).isNotNull();
        assertThat(studentService.getStudentById(student.getId()).getBody().getData()).
        usingRecursiveComparison().ignoringFields("id").isEqualTo(student);
    }

    @Test
    void isShouldNoDeleteStudentThatDoesNotExist() {
        // Given
        String studentId="832134ab-7549-4fbd-9e72-a8775f07ca9c";

        // When

        // Then
        assertThatThrownBy(()->studentService.deleteById(studentId)).
                isInstanceOf(NoSuchStudentException.class)
                .hasMessageContaining("There is no student with this id to delete");
    }

    @Test
    void isShouldDeleteStudentThatDoesExist() throws NoSuchStudentException {
        // Given
        String studentId="832134ab-7549-4fbd-9e72-a8775f07ca9c";
        when(studentRepo.findById(studentId)).thenReturn(Optional.of(new Student()));

        // When
        studentService.deleteById(studentId);

        // Then
        verify(studentRepo).deleteById(studentId);
    }

    @Test
    void itShouldValidateAnExistingEmail() {
        // Given
        String studentEmail="elouali@gmail.com";
        when(studentRepo.selectExistsEmail(studentEmail)).thenReturn(true);

        // When

        // Then
        assertThat(studentService.doesEmailAlreadyExist(studentEmail)).isTrue();
    }

    @Test
    void itShouldNotValidateANoneExistingEmail() {
        // Given
        String studentEmail="elouali@gmail.com";
        when(studentRepo.selectExistsEmail(studentEmail)).thenReturn(false);

        // When

        // Then
        assertThat(studentService.doesEmailAlreadyExist(studentEmail)).isFalse();
    }
}