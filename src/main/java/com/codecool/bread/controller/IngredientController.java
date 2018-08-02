package com.codecool.bread.controller;

import com.codecool.bread.exception.IngredientNotFoundException;
import com.codecool.bread.model.Ingredient;
import com.codecool.bread.repository.IngredientRepository;
import com.codecool.bread.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/owner/restaurant/{restaurantId}/ingredient")
public class IngredientController extends AbstractController {

    @GetMapping("")
    public List<Ingredient> getAllIngredients(@PathVariable("restaurantId") int restaurantId) {
        return ingredientService.getAllIngredient();
    }

    @GetMapping("/{ingredientId}")
    public Ingredient getIngredientById(@PathVariable("ingredientId") int ingredientId) {
        return ingredientService.getIngredientById(ingredientId);
    }

    @PostMapping(path = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Ingredient addNewIngredient(@RequestBody Ingredient ingredient) {
        return ingredientService.addNewIngredient(ingredient);
    }

    @PutMapping("/{ingredientId}")
    public Ingredient editIngredient(@RequestBody Ingredient ingredient) {
        if(ingredientService.getIngredientById(ingredient.getId()) != null) {
            return ingredientService.editEmployee(ingredient);
        } else {
            throw new IngredientNotFoundException();
        }
    }

    @GetMapping("/item/{itemId}")
    public Set<Ingredient> getIngredientsByItemId(@PathVariable("itemId") int itemId, @PathVariable("restaurantId") int restaurantId) {
        return ingredientService.getIngredientsByItemIdFromDb(itemId, restaurantId);
    }
}
