package com.codecool.bread.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoTablesFoundException extends RuntimeException {
    public NoTablesFoundException() {
        super("No tables found");
    }
}
