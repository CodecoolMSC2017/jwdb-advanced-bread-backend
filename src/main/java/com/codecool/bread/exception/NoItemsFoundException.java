package com.codecool.bread.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoItemsFoundException extends RuntimeException {
    public NoItemsFoundException() {
        super("There are no items added to this restaurant");
    }
}
