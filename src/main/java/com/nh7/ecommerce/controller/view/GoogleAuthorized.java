package com.nh7.ecommerce.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GoogleAuthorized {
    @GetMapping("/")
    public String authorizeGoogle(Model model) {
        return "googlef5ffcba406dd90c7"; // Trả về file index.html
    }
    @GetMapping("/googlef5ffcba406dd90c7.html")
    public String authorizeGoogle2(Model model) {
        return "googlef5ffcba406dd90c7"; // Trả về file index.html
    }
}
