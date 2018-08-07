package com.codecool.bread.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class EmailSendFailedException extends RuntimeException {
    public EmailSendFailedException() {
        super("Email not send! Please try again later!");
    }
}
