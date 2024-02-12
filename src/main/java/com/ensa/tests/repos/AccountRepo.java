package com.ensa.tests.repos;

import com.ensa.tests.entities.Account;
import com.ensa.tests.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepo extends PagingAndSortingRepository<Account, String>, JpaRepository<Account, String>{
}
