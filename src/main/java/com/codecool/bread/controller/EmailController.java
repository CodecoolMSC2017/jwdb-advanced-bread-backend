package com.codecool.bread.controller;

import com.codecool.bread.exception.EmailSendFailedException;
import com.codecool.bread.model.Email;
import com.codecool.bread.model.User;
import com.codecool.bread.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.SendFailedException;
import java.util.Map;

@RestController
@RequestMapping("/send")
public class EmailController extends AbstractController {

    @PostMapping("")
    public void sendPasswordChange(@RequestBody Map<String, String> user) {
        try {
            emailService.sendSimpleMessage(emailService.createPasswordChangeEmail(user));
        } catch (SendFailedException ex) {
            throw new EmailSendFailedException();
        }

    }
}
