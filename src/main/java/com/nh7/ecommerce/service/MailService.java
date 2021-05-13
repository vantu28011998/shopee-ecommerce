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
    public void sendEmail(String emailAddress, Integer generatedNum) throws MailException {
        String title="SHOPEE ECOMMERCE NH7";
        String content="MÃ XÁC MINH ĐĂNG KÝ TÀI KHOẢN CỦA BẠN LÀ " + generatedNum +".Có hiệu lực trong 1 phút. Vui lòng KHÔNG chia sẻ mã này với người khác, kể cả nhân viên shopee.";
        SimpleMailMessage mail=new SimpleMailMessage();
        mail.setTo(emailAddress);
        mail.setSubject(title);
        mail.setText(content);
        javaMailSender.send(mail);
    }

}
