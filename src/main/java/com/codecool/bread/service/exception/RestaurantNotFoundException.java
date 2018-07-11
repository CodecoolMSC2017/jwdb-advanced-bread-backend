package com.codecool.bread.service.exception;

public class RestaurantNotFoundException extends Throwable {
    public RestaurantNotFoundException(String message) {
        super(message);
    }
}
