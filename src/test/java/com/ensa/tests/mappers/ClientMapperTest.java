package com.ensa.tests.mappers;

import com.ensa.tests.dtos.StudentDto;
import com.ensa.tests.entities.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class ClientMapperTest {
    private final StudentMapper underTest=new StudentMapper();

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void itShouldGetStudentDtoFromStudent() {
        Client client = Client.builder().age(23).email("Sam@gmail.com").isActive(true).username("Sam").build();
        assertThat(underTest.getStudentDtoFromStudent(client)).isNotNull();
        assertThat(underTest.getStudentDtoFromStudent(client)).
                usingRecursiveComparison().ignoringFields("id").isEqualTo(client);
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
        List<Client> clientList =List.of(
                Client.builder().age(23).email("test1@gmail.com").isActive(true).username("Toto1").build(),
                Client.builder().age(23).email("test2@gmail.com").isActive(false).username("Toto2").build()
        );

        assertThat(underTest.getStudentDtosFromStudents(clientList)).isNotNull();
        assertThat(underTest.getStudentDtosFromStudents(clientList)).
                usingRecursiveComparison().ignoringFields("id").isEqualTo(clientList);
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