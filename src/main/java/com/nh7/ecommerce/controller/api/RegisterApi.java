package com.nh7.ecommerce.controller.api;

import com.nh7.ecommerce.dto.Otp;
import com.nh7.ecommerce.service.EmailService;

import com.nh7.ecommerce.service.UserService;
import com.nh7.ecommerce.util.VerifyNumberUtil;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;


@RestController
@CrossOrigin
@ControllerAdvice
@RequestMapping(value="/api/register")
public class RegisterApi {
//    @Autowired
//    private MailService notificationService;

    @Autowired
    private UserService userService;
    @Autowired
    private VerifyNumberUtil verifyNumberUtil;
//    @GetMapping
//    public ResponseEntity<Integer> sendEmail(@RequestParam(value="email-address") String emailAddress){
//        List<Long> idOfMail = userService.findIdsByEmailAddress(emailAddress);
//        System.out.println("SIZE EMAIL"+idOfMail.size());
//        if(idOfMail.size() == 0 ){
//            try {
//                Integer generatedNum = verifyNumberUtil.generate(5);
//                notificationService.sendEmail(emailAddress,generatedNum);
//                return new ResponseEntity<>(generatedNum, HttpStatus.OK);
//            } catch (MailException mailException) {
//                System.out.println(mailException);
//            }
//        }
//        return new ResponseEntity<>(-1, HttpStatus.OK);
//    }
    @Autowired
    private EmailService emailService;
    @RequestMapping(value = "" , method = RequestMethod.GET)
    public ResponseEntity<Otp> sendEmail(@RequestParam("email-address") String email){
        List<Long> idOfMail = userService.findIdsByEmailAddress(email);
        System.out.println(idOfMail.size());
        if(idOfMail.size() == 0){
            try {
                Integer generatedNum = verifyNumberUtil.generate(5);
                emailService.sendMail(email,"NH7 Ecommerce","MÃ XÁC MINH ĐĂNG KÝ TÀI KHOẢN CỦA BẠN LÀ "+generatedNum+" .Có hiệu lực trong 3 phút. Vui lòng KHÔNG chia sẻ mã này với người khác.");
                return new ResponseEntity<Otp>(new Otp(0,"Create OTP succesfully",generatedNum.toString(),email),HttpStatus.OK);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }if(idOfMail.size()>0){
            return new ResponseEntity<Otp>(new Otp(-1,"Email is existing","",""),HttpStatus.OK);
        }
        return new ResponseEntity<Otp>(new Otp(-1,"Create OTP fail","",""),HttpStatus.OK);
    }

}
