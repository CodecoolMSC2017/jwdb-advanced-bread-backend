package com.codecool.bread.controller;

import com.codecool.bread.model.Ingredient;
import com.codecool.bread.service.simple.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner/{ownerId}/restaurant/{restaurantId}/ingredient")
public class RestIngredientController {

    @Autowired
    private IngredientService ingredientService;

    @GetMapping("")
    public List<Ingredient> getAllIngredients(@PathVariable("restaurantId") int restaurantId) {
        return ingredientService.getAllIngredient();
    }

    @GetMapping("{ingredientId}")
    public Ingredient getIngredientById(@PathVariable("ingredientId") int ingredientId) {
        return ingredientService.getIngredientById(ingredientId);
    }

    @PostMapping(path = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Ingredient addNewIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.addNewIngredient(ingredient);
    }


}
