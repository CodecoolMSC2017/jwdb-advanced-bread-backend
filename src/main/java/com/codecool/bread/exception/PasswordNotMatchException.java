package com.codecool.bread.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class PasswordNotMatchException extends RuntimeException {
    public PasswordNotMatchException(){
        super("Password and confirmation needs to be the same!");
    }
}
