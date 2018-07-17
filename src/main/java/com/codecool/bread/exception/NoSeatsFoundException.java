package com.codecool.bread.exception;

public class NoSeatsFoundException extends RuntimeException {
    public NoSeatsFoundException() {
        super("No seats found");
    }
}
