package com.ensa.tests.repos;

import com.ensa.tests.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepo extends PagingAndSortingRepository<Student, String>, JpaRepository<Student, String> {
    // Custom query method to select and check if an email exists in the database
    @Query("SELECT COUNT(s) > 0 FROM Student s WHERE s.email = :email")
    boolean selectExistsEmail(@Param("email") String email);
}
