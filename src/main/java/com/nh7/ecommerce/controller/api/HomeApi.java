package com.nh7.ecommerce.controller.api;

import com.nh7.ecommerce.entity.Category;
import com.nh7.ecommerce.entity.Product;
import com.nh7.ecommerce.service.CategoryService;
import com.nh7.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/home")
public class HomeApi {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @GetMapping("/categories")
    public List<Category> getCategories(){
        return categoryService.findAll();
    }
    @GetMapping("/categories/{name}")
    public List<Category> getCategoriesByName(@PathVariable(value="name")String name){
        return categoryService.findByName(name);
    }
    @GetMapping("/product/{category_name}")
    public List<Product> getProductsByCategoryName(@PathVariable(value="category_name")String categoryName){
        return productService.getProductByCategoryName(categoryName);
    }
}
