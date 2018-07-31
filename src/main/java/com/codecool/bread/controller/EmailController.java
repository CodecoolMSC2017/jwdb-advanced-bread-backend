package com.codecool.bread.controller;

import com.codecool.bread.model.Email;
import com.codecool.bread.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.mail.SendFailedException;

@Controller
@RequestMapping("/send")
public class EmailController extends AbstractController {

    @PostMapping("/invite")
    public ResponseEntity<?> sendInvitation(@RequestBody Email email) {
        try {
            emailService.sendSimpleMessage(email);
            return ResponseEntity.ok("Success");
        } catch (SendFailedException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
