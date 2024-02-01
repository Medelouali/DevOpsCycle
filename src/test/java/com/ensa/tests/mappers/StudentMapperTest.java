package com.ensa.tests.mappers;

import com.ensa.tests.dtos.StudentDto;
import com.ensa.tests.entities.Student;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StudentMapperTest {
    private final StudentMapper underTest=new StudentMapper();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void itShouldGetStudentDtoFromStudent() {
        Student student=Student.builder().age(23).email("Sam@gmail.com").isActive(true).username("Sam").build();
        assertThat(underTest.getStudentDtoFromStudent(student)).isNotNull();
        assertThat(underTest.getStudentDtoFromStudent(student)).
                usingRecursiveComparison().ignoringFields("id").isEqualTo(student);
    }

    @Test
    void getStudentFromStudentDto() {
        StudentDto studentDto= StudentDto.builder().age(23).email("Sam@gmail.com").isActive(true).username("Sam").build();
        assertThat(underTest.getStudentFromStudentDto(studentDto)).isNotNull();
        assertThat(underTest.getStudentFromStudentDto(studentDto)).
                usingRecursiveComparison().ignoringFields("id").isEqualTo(studentDto);
    }

    @Test
    void getStudentDtosFromStudents() {
        List<Student> studentList=List.of(
                Student.builder().age(23).email("test1@gmail.com").isActive(true).username("Toto1").build(),
                Student.builder().age(23).email("test2@gmail.com").isActive(false).username("Toto2").build()
        );

        assertThat(underTest.getStudentDtosFromStudents(studentList)).isNotNull();
        assertThat(underTest.getStudentDtosFromStudents(studentList)).
                usingRecursiveComparison().ignoringFields("id").isEqualTo(studentList);
    }

    @Test
    void getStudentsFromStudentsDtos() {
        List<StudentDto> studentList=List.of(
                StudentDto.builder().age(23).email("test1@gmail.com").isActive(true).username("Toto1").build(),
                StudentDto.builder().age(23).email("test2@gmail.com").isActive(false).username("Toto2").build()
        );

        assertThat(underTest.getStudentsFromStudentsDtos(studentList)).isNotNull();
        assertThat(underTest.getStudentsFromStudentsDtos(studentList)).
                usingRecursiveComparison().ignoringFields("id").isEqualTo(studentList);
    }
}