package com.codecool.bread.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ItemAccessDeniedException extends RuntimeException {

    public ItemAccessDeniedException() {
        super("This item is not accessible for you!");
    }
}
