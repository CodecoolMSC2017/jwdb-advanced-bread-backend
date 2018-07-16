package com.codecool.bread.service;

import com.codecool.bread.exception.IngredientAlreadyExistsException;
import com.codecool.bread.exception.IngredientNotFoundException;
import com.codecool.bread.model.Ingredient;
import com.codecool.bread.repository.IngredientRepository;
import com.codecool.bread.service.simple.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class IngredientServiceImpl implements IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> getAllIngredient() {
        return ingredientRepository.findAll();
    }

    public Ingredient getIngredientById(int ingredientId) throws IngredientNotFoundException {
        if(!ingredientRepository.findById(ingredientId).isPresent()) {
            throw new IngredientNotFoundException();
        }
        return ingredientRepository.findById(ingredientId).get();
    }

    public Ingredient addNewIngredient(Ingredient ingredient) {
        if(getAllIngredient().contains(ingredient)) {
            throw new IngredientAlreadyExistsException();
        }

        return ingredientRepository.save(ingredient);
    }

    public void addNewIngredients(Set<Ingredient> ingredients) {
        List<Ingredient> newIngredients = new ArrayList<>();
        for(Ingredient ingredient : ingredients) {
            if(!getAllIngredient().contains(ingredient)) {
                newIngredients.add(ingredient);
            }
        }
        ingredientRepository.saveAll(newIngredients);
    }
}
