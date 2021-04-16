package com.nh7.ecommerce.service;

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
        String title="This is title";
        String content="This is content mail";
        SimpleMailMessage mail=new SimpleMailMessage();
        mail.setTo(emailAddress);
        mail.setSubject(title);
        mail.setText(content);
        javaMailSender.send(mail);
    }

}
