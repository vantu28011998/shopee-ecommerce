package com.nh7.ecommerce.controller.api;

import com.nh7.ecommerce.dto.ProductCardDto;
import com.nh7.ecommerce.model.ProductCardModel;
import com.nh7.ecommerce.service.ProductService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/api/categories")
public class ProductListApi{
    @Autowired
    private ProductService productService;
    @GetMapping("/{category_id}/products")
    public ResponseEntity<List<ProductCardModel>> getProducts(@PathVariable(name = "category_id") Long id){
        return new ResponseEntity<>(productService.getProductCardByCategoryId(id),HttpStatus.OK);
    }
}
