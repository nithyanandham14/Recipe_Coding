package com.example.Recipe.model;

import lombok.Data;
import java.util.Map;

@Data
public class RecipeDTO {
    private String title;
    private String cuisine;
    private Float rating;
    private Integer prep_time;
    private Integer cook_time;
    private String description;
    private Map<String, String> nutrients;
    private String serves;
}