package com.nh7.ecommerce.controller.api;

import com.nh7.ecommerce.entity.Category;
import com.nh7.ecommerce.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="api/registration")
public class RegistrationApi {
    @Autowired
    private MailService notificationService;
    @GetMapping
    public String sendEmail(@RequestParam(value="email-address") String emailAddress){
        try {
            notificationService.sendEmail(emailAddress);
        } catch (MailException mailException) {
            System.out.println(mailException);
        }
        return "Congratulations! Your mail has been send to the user.";
    }

}
