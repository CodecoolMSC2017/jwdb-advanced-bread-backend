package com.codecool.bread.service.simple;

import com.codecool.bread.model.Email;
import com.codecool.bread.model.Employee;
import com.codecool.bread.model.User;
import com.codecool.bread.service.EmailService;
import com.codecool.bread.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.mail.SendFailedException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

@Component
public class EmailServiceImpl extends AbstractService implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private UserService userService;


    public void sendSimpleMessage(Email email) throws SendFailedException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.getTo());
        message.setSubject(email.getSubject());
        message.setText(email.getText());
        emailSender.send(message);
    }

    public Email createPasswordChangeEmail(Map<String, String> userMap) {
        String email = userMap.get("email");
        User user = userService.getUserByEmail(email);
        String subject = "Change password";
        String url = "http://localhost:4200/changepassword?";
        String encodedUrl = /*URLEncoder.encode(*/"userId="+user.getId()+"&username="+ user.getUsername()/*,"UTF-8")*/;
        url += encodedUrl;
        String content = "Dear " + user.getUsername()+"!\n\nPlease follow this link to change your password: " + url;
        return new Email(email,subject,content);
    }


    public Email createEmail(Employee employee) throws UnsupportedEncodingException {
        String subject = "Registration from " + employee.getRestaurant().getName();
        // TODO this is the first part of the URL
        String url = "http://localhost:4200/registration?";
        String encodedUrl = /*URLEncoder.encode(*/"employeeId="+employee.getId()+"&restaurantId="+employee.getRestaurant().getId()/*,"UTF-8")*/;
        url += encodedUrl;
        String content = "Dear " + employee.getFirstName()+"!\n\nPlease follow this link to register: " + url;
        return new Email(employee.getEmail(),subject,content);
    }
}