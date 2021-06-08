package com.nh7.ecommerce.controller.api;

import com.nh7.ecommerce.dto.SubCategoryDto;
import com.nh7.ecommerce.entity.SubCategory;
import com.nh7.ecommerce.service.CategoryService;
import com.nh7.ecommerce.service.ProductService;
import com.nh7.ecommerce.service.SubCategoryService;
import com.nh7.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@CrossOrigin
@RestController
@ControllerAdvice
@RequestMapping(value = "/api/admin")
public class AdminApi {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    final int currentMonth = LocalDate.now().getMonthValue();
    final int currentYear = LocalDate.now().getYear();

    @GetMapping("/categories/{id}/getById")
    public ResponseEntity<Object> getCategoryById(@PathVariable(name = "id") long id){
        return new ResponseEntity<>(categoryService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/subcategories/{id}/getById")
    public ResponseEntity<Object> getSubCategoryById(@PathVariable(value = "id") long id){
        SubCategory subCategory = subCategoryService.getById(id);
        SubCategoryDto subCategoryDto = new SubCategoryDto();
        subCategoryDto.setId(id);
        subCategoryDto.setSubCategoryName(subCategory.getSubCategoryName());
        subCategoryDto.setCategoryId(subCategory.getCategory().getId());
        subCategoryDto.setCategoryName(subCategory.getCategory().getCategoryName());
        return new ResponseEntity<>(subCategoryDto, HttpStatus.OK);
    }

    @GetMapping("/users/{id}/getById")
    public ResponseEntity<Object> getUserInfoById(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(userService.getUserInfoById(id), HttpStatus.OK);
    }

    // for get map Product Best Sell In Current Month
    @GetMapping("/home/line-chart")
    public ResponseEntity<Object> getProductsBestSell() {
        return new ResponseEntity<>(productService.getProductBestSell(currentMonth,currentYear), HttpStatus.OK);
    }
}
