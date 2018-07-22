package com.codecool.bread.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoEmployeeForRestaurantException extends RuntimeException {
    public NoEmployeeForRestaurantException() {
        super("No employee found for this restaurant");
    }
}
