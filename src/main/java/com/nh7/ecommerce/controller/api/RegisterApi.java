package com.nh7.ecommerce.controller.api;

import com.nh7.ecommerce.service.MailService;
import com.nh7.ecommerce.service.UserService;
import com.nh7.ecommerce.util.VerifyNumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@ControllerAdvice
@RequestMapping(value="/api/register")
public class RegisterApi {
    @Autowired
    private MailService notificationService;
    @Autowired
    private UserService userService;
    @Autowired
    private VerifyNumberUtil verifyNumberUtil;
    @GetMapping
    public ResponseEntity<Integer> sendEmail(@RequestParam(value="email-address") String emailAddress){
        List<Long> idOfMail = userService.findIdsByEmailAddress(emailAddress);
        System.out.println("SIZE EMAIL"+idOfMail.size());
        if(idOfMail.size() == 0 ){
            try {
                Integer generatedNum = verifyNumberUtil.generate(5);
                notificationService.sendEmail(emailAddress,generatedNum);
                return new ResponseEntity<>(generatedNum, HttpStatus.OK);
            } catch (MailException mailException) {
                System.out.println(mailException);
            }
        }
        return new ResponseEntity<>(-1, HttpStatus.OK);
    }

}
