package com.ensa.tests.repos;

import com.ensa.tests.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends PagingAndSortingRepository<Client, String>, JpaRepository<Client, String> {
    // Custom query method to select and check if an email exists in the database
    @Query("SELECT COUNT(s) > 0 FROM Client s WHERE s.email = :email")
    boolean selectExistsEmail(@Param("email") String email);
}
