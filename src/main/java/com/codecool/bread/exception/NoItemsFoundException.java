package com.codecool.bread.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoItemsFoundException extends RuntimeException {
    public NoItemsFoundException() {
        super("There are no items added to this restaurant");
    }
}
