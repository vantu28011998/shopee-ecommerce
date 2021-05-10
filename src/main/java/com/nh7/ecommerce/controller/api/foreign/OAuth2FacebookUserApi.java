package com.nh7.ecommerce.controller.api.foreign;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/home/us")
public class OAuth2FacebookUserApi {
    @GetMapping
    public Principal getUser(Principal user) {
        return user;
    }
}
