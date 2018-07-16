package com.codecool.bread.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IngredientAlreadyExistsException extends RuntimeException {
    public IngredientAlreadyExistsException() {
        super("This ingredient is already exists!");
    }
}
