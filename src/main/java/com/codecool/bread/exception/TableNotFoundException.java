package com.codecool.bread.exception;

public class TableNotFoundException extends RuntimeException {
    public TableNotFoundException() {
        super("No table found with this id");
    }
}
