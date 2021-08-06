package com.nh7.ecommerce.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GoogleAuthorized {
    @GetMapping("/google2267c49c2ec726ab")
    public String authorizeGoogle(Model model) {
        return "google2267c49c2ec726ab"; // Trả về file index.html
    }
}
