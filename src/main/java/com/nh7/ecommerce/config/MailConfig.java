package com.nh7.ecommerce.config;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Configuration
@Configurable
public class MailConfig {
    private String usernameGmail="shope.ecommerce.nh7@gmail.com";
    private String passwordGmail="shope.ecommerce.28011998";
    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername(usernameGmail);
        mailSender.setPassword(passwordGmail);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.smtp.host", "mail.authsmtp.com");
        props.put("mail.smtp.port", "mail.authsmtp.com");
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }
}
