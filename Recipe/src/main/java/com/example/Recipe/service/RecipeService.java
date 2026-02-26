package com.example.Recipe.service;

import com.example.Recipe.model.Recipe;
import com.example.Recipe.repository.RecipeRepo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.example.Recipe.model.RecipeDTO;
import java.io.File;
import java.util.Map;
import java.util.List;


@Service
public class RecipeService {

    @Autowired
    private RecipeRepo repo;

    public Recipe saveRecipe(Recipe recipe) {
        if (recipe.getTitle() == null || recipe.getTitle().isEmpty()) {
            throw new RuntimeException("title is required");
        }
        if (recipe.getCuisine() == null || recipe.getCuisine().isEmpty()) {
            throw new RuntimeException("cuisine is required");
        }
        if (recipe.getPrep_time() == null) {
            throw new RuntimeException("prep_time is required");
        }
        if (recipe.getCook_time() == null) {
            throw new RuntimeException("cook_time is required");
        }
        recipe.setTotal_time(recipe.getPrep_time() + recipe.getCook_time());
        return repo.save(recipe);
    }
    public List<Recipe> getTopRecipes(int limit) {

        Pageable pageable = PageRequest.of(0, limit);
        return repo.findTopRated(pageable);
    }
    public void loadJsonFile() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            File file = new File("C:\\Users\\nithy\\Downloads\\Recipe\\US_recipes_null.json");
            Map<String, RecipeDTO> map = mapper.readValue(
                    file,
                    new TypeReference<Map<String, RecipeDTO>>() {}
            );
            for (RecipeDTO dto : map.values()) {

                Recipe recipe = new Recipe();

                recipe.setTitle(dto.getTitle());
                recipe.setCuisine(dto.getCuisine());
                recipe.setRating(dto.getRating());
                int prep = dto.getPrep_time() != null ? dto.getPrep_time() : 0;
                int cook = dto.getCook_time() != null ? dto.getCook_time() : 0;
                recipe.setTotal_time(prep + cook);
                recipe.setDescription(dto.getDescription());
                if (dto.getNutrients() != null) {
                    recipe.setNutrients(dto.getNutrients().toString());
                }

                recipe.setServes(dto.getServes());

                repo.save(recipe);
            }

            System.out.println("JSON stored successfully!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}