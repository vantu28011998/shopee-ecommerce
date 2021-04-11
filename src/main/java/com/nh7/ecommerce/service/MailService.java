package com.nh7.ecommerce.service;

import com.nh7.ecommerce.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    @Autowired
    private JavaMailSender javaMailSender;
    public void sendEmail(String emailAddress) throws MailException {
        SimpleMailMessage mail=new SimpleMailMessage();
        mail.setTo(emailAddress);
        mail.setSubject("Testing Mail Api");
        mail.setText("Hurray! You have done that due...");
        javaMailSender.send(mail);
    }

}
