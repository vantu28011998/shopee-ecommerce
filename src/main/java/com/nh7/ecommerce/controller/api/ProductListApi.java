package com.nh7.ecommerce.controller.api;

import com.nh7.ecommerce.dto.ProductCardDto;
import com.nh7.ecommerce.entity.Category;
import com.nh7.ecommerce.entity.Product;
import com.nh7.ecommerce.model.ProductCardModel;
import com.nh7.ecommerce.service.ProductService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/home/categories")
@ControllerAdvice
@CrossOrigin
public class ProductListApi{
    @Autowired
    private ProductService productService;
    //---------GET METHOD----------//
    @GetMapping("/{category_id}/products")
    public ResponseEntity<List<ProductCardModel>> getProducts(@PathVariable(name = "category_id") Long id){
        return new ResponseEntity<>(productService.getProductCardByCateId(id),HttpStatus.OK);
    }
    //---------POST METHOD----------//
    //Post 1 product
    @PostMapping("/products")
    public ResponseEntity<Object> createCategory(@RequestBody Category category){

        return new ResponseEntity<>("Product is created successfully",HttpStatus.CREATED);
    }
    //Post 1 list product
    @PostMapping("/product-list")
    public ResponseEntity<Object> createCategory(@RequestBody List<Category> categoryList){

        return new ResponseEntity<>("Category is created successfully",HttpStatus.CREATED);
    }
}
