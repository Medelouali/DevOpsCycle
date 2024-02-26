package com.ensa.tests.repos;


import com.ensa.tests.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "categories")
public interface CategoriesRepo extends JpaRepository<Category, String> {
}
