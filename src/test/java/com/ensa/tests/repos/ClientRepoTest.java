package com.ensa.tests.repos;

import com.ensa.tests.entities.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class ClientRepoTest {
    @Autowired
    private StudentRepo studentRepo;

    @BeforeEach
    void setUp() {
        Client client = Client.builder()
                .email("med@gmail.com").age(23).username("med")
                .isActive(false).build();
        studentRepo.save(client);
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