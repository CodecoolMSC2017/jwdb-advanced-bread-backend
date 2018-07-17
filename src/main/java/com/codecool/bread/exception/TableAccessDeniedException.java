package com.codecool.bread.exception;

public class TableAccessDeniedException extends RuntimeException {
    public TableAccessDeniedException() {
        super("This tale is not accessible for you");
    }
}
