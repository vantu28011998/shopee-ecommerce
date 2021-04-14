package com.nh7.ecommerce.controller.api;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductListApi{
    @GetMapping("/categories/{category_id}")
    public String getProducts(@PathVariable(name = "category_id") Long id){

        return null;
    }

}
