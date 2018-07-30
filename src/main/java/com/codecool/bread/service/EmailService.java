package com.codecool.bread.service;

import com.codecool.bread.model.Email;

import javax.mail.SendFailedException;

public interface EmailService {

    void sendSimpleMessage(Email email) throws SendFailedException;
}
