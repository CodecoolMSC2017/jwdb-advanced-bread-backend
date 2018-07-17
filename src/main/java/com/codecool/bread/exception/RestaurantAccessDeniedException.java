package com.codecool.bread.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class RestaurantAccessDeniedException extends RuntimeException {
    public RestaurantAccessDeniedException() {
        super("Access denied");
    }
}
