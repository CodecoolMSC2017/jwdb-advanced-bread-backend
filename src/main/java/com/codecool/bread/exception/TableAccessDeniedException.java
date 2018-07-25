package com.codecool.bread.exception;

public class TableAccessDeniedException extends RuntimeException {
    public TableAccessDeniedException() {
        super("This table is not accessible for you");
    }
}
