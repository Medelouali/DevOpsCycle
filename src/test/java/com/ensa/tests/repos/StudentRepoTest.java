package com.ensa.tests.repos;

import com.ensa.tests.entities.Student;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class StudentRepoTest {
    @Autowired
    private StudentRepo studentRepo;

    @BeforeEach
    void setUp() {
        Student student= Student.builder()
                .email("med@gmail.com").age(23).username("med")
                .isActive(false).build();
        studentRepo.save(student);
    }

    // Having clean state for each test
    @AfterEach
    void tearDown() {
        studentRepo.deleteAll();
    }

    @Test
    void itShouldCheckIfEmailExists() {
        // Given
        String email = "med@gmail.com";

        // When
        Boolean expected=studentRepo.selectExistsEmail(email);

        // Then
        assertThat(expected).isTrue();

    }

    @Test
    void itShouldCheckIfEmailDoesNotExist() {
        // Given
        String email="tata@gmail.com";

        // When
        Boolean expected=studentRepo.selectExistsEmail(email);

        // Then
        assertThat(expected).isFalse();

    }
}