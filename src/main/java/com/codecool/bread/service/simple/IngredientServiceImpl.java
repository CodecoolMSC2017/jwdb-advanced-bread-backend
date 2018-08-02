package com.codecool.bread.service.simple;

import com.codecool.bread.exception.IngredientAlreadyExistsException;
import com.codecool.bread.exception.IngredientNotFoundException;
import com.codecool.bread.model.Ingredient;
import com.codecool.bread.model.Item;
import com.codecool.bread.repository.IngredientRepository;
import com.codecool.bread.service.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class IngredientServiceImpl extends AbstractService implements IngredientService {

    public List<Ingredient> getAllIngredient() {
        return ingredientRepository.findAll();
    }

    public Ingredient getIngredientById(int ingredientId) throws IngredientNotFoundException {
        Optional<Ingredient> ingredient = ingredientRepository.findById(ingredientId);
        if(!ingredient.isPresent()) {
            throw new IngredientNotFoundException();
        }
        return ingredient.get();
    }

    public Ingredient addNewIngredient(Ingredient ingredient) {
        if(getAllIngredient().contains(ingredient)) {
            throw new IngredientAlreadyExistsException();
        }

        return ingredientRepository.saveAndFlush(ingredient);
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

    @Override
    public Ingredient editEmployee(Ingredient ingredient) throws IngredientNotFoundException{
        return ingredientRepository.saveAndFlush(ingredient);
    }

    @Override
    public Set<Ingredient> getIngredientsByItemIdFromDb(int itemId, int restaurantId) {
        Item item = itemRepository.findByIdAndRestaurantId(itemId, restaurantId);
        return item.getIngredients();
    }
}
