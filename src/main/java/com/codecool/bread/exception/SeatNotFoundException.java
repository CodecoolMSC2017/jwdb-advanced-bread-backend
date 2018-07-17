package com.codecool.bread.exception;

public class SeatNotFoundException extends RuntimeException {
    public SeatNotFoundException() {
        super("No seat found");
    }
}
