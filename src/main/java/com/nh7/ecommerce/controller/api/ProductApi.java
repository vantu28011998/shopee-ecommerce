package com.nh7.ecommerce.controller.api;
import com.nh7.ecommerce.dto.ProductCardDto;
import com.nh7.ecommerce.entity.Product;
import com.nh7.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/home/categories")
@ControllerAdvice
@CrossOrigin
public class ProductApi implements ICrudApi<ProductCardDto,Product>{
    @Autowired
    private ProductService productService;

    //---------GET METHOD----------//
    @GetMapping(value = {"/products","/products/all","/product/"})
    @Override
    public ResponseEntity<List<ProductCardDto>> getAll() {
        return new ResponseEntity<>(productService.getAll(),HttpStatus.OK);
    }
    @GetMapping("/products/{id}")
    @Override
    public ResponseEntity<ProductCardDto> get(@PathVariable(name = "id") Long id) {
        return null;
    }

    @GetMapping(value = {"/{id}/products","/{id}/products/all","/{id}/products/"})
    public ResponseEntity<List<ProductCardDto>> getAllByCategoryId(@PathVariable Long id){
        return new ResponseEntity<>(productService.getProductCardByCategoryId(id),HttpStatus.OK);
    }
    //----------POST METHOD---------//

    @PostMapping("/products/all")
    @Override
    public ResponseEntity<Object> createAll(@RequestBody List<Product> items) {
        productService.saveAll(items);
        return new ResponseEntity<>("Products are created successfully",HttpStatus.CREATED);
    }

    @PostMapping("/products")
    @Override
    public ResponseEntity<Object> create(@RequestBody Product item) {
        productService.save(item);
        return new ResponseEntity<>("Product is created successfully",HttpStatus.CREATED);
    }

    //----------PUT METHOD---------//
    @PutMapping("/products/{id}")
    @Override
    public ResponseEntity<Object> update(@PathVariable Long id, Product item) {
        return null;
    }

    //----------DELETE METHOD---------//
    @DeleteMapping("/products/all")
    @Override
    public ResponseEntity<Object> deleteAll() {
        productService.deleteAll();
        return new ResponseEntity<>("Products are deleted successfully",HttpStatus.OK);
    }
    @DeleteMapping("/products/{id}")
    @Override
    public ResponseEntity<Object> delete(@PathVariable  Long id) {
        productService.delete(id);
        return new ResponseEntity<>("Product is deleted successfully",HttpStatus.OK);
    }



}
