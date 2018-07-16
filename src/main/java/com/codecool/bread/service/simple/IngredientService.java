package com.codecool.bread.service.simple;

import com.codecool.bread.exception.IngredientNotFoundException;
import com.codecool.bread.model.Ingredient;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface IngredientService {

    List<Ingredient> getAllIngredient();
    Ingredient getIngredientById(int ingredientId) throws IngredientNotFoundException;
    Ingredient addNewIngredient(Ingredient ingredient);
    void addNewIngredients(Set<Ingredient> ingredients);

}
