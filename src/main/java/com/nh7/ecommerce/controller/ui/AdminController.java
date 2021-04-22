package com.nh7.ecommerce.controller.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/")
    String getAdmin() {
        return "admin";
    }
}
