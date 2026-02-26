package com.example.Recipe.controller;

import com.example.Recipe.model.Recipe;
import com.example.Recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author nithy
 **/
@RestController
public class RecipeController {
    @Autowired
    private RecipeService serv;

    @PostMapping("/recipes")
    public Recipe createRecipe(@RequestBody Recipe recipe) {
        return serv.saveRecipe(recipe);
    }
    @GetMapping("/recipes/top")
    public List<Recipe> getTopRated(@RequestParam(defaultValue = "5") int limit) {
        return serv.getTopRecipes(limit);
    }
    @PostMapping("/recipes/load-json")
    public String loadJson() {
        serv.loadJsonFile();
        return "JSON stored successfully";
    }
}
