package com.example.Recipe.repository;

import com.example.Recipe.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface RecipeRepo extends JpaRepository<Recipe, Long> {

    @Query("SELECT r FROM Recipe r ORDER BY r.rating DESC")
    List<Recipe> findTopRated(Pageable pageable);
}