package com.nh7.ecommerce.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/home")
public class HomeApi {

    @GetMapping(value = "/categories")
    public String getCategory(){
        return "Hello world";
    }
}
