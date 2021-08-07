package com.nh7.ecommerce.config;

import java.util.Properties;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;


@Configuration
@Configurable
public class MailConfig {
//    private String usernameGmail="shope.ecommerce.nh7@gmail.com";
//    private String passwordGmail="shope.ecommerce.28011998";
//    @Bean
//    public JavaMailSender getJavaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("smtp.gmail.com");
//        mailSender.setPort(587);
//
//        mailSender.setUsername(usernameGmail);
//        mailSender.setPassword(passwordGmail);
//
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.smtp.host", "mail.authsmtp.com");
//        props.put("mail.smtp.port", "mail.authsmtp.com");
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.debug", "true");
//
//        return mailSender;
//    }

    @Value("${email.sender.host}")
    private String host;

    @Value("${email.sender.port}")
    private String port;

    @Value("${email.sender.username}")
    private String username;

    @Value("${email.sender.password}")
    private String password;

    @Value("${email.transport.protocol}")
    private String protocol;

    @Value("${email.smtp.auth}")
    private String auth;

    @Value("${email.smtp.starttls.enable}")
    private String ttlsEnable;

    @Value("${email.debug}")
    private String debug;


    @Bean
    public JavaMailSender getJavaMailSender(){

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(Integer.valueOf(port));

        mailSender.setUsername(username);
        mailSender.setPassword(password);

        mailSender.setJavaMailProperties(getMailSenderProperties());

        return mailSender;
    }

    private Properties getMailSenderProperties(){
        Properties props = new Properties();
        props.put("mail.transport.protocol", protocol);
        props.put("mail.smtp.auth", auth);
        props.put("mail.smtp.starttls.enable", ttlsEnable);
        props.put("mail.debug", debug);

        return props;
    }
}
