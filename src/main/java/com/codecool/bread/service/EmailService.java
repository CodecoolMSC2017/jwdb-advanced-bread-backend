package com.codecool.bread.service;

import com.codecool.bread.model.Email;
import com.codecool.bread.model.Employee;

import javax.mail.SendFailedException;
import java.io.UnsupportedEncodingException;

public interface EmailService {

    void sendSimpleMessage(Email email) throws SendFailedException;

    Email createEmail(Employee employee) throws UnsupportedEncodingException;
}
