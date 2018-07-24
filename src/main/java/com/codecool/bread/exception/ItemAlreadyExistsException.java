package com.codecool.bread.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ItemAlreadyExistsException extends RuntimeException {

    public ItemAlreadyExistsException() {
        super("This item already exists!");
    }
}
