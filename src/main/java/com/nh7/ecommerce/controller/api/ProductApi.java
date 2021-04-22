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
public abstract class ProductApi implements ICrudApi<ProductCardDto,Product>, IGetBySuperApi<ProductCardDto>,IPostBySuperApi<Product> {
    @Autowired
    private ProductService productService;

    //---------GET METHOD----------//
    @GetMapping(value = {"/products","/products/all","/product/"})
    @Override
    public ResponseEntity<List<ProductCardDto>> getAll() {
        return null;
    }
    @GetMapping("/products/{id}")
    @Override
    public ResponseEntity<ProductCardDto> get(@PathVariable(name = "id") Long id) {
        return null;
    }
    //GET ALL PRODUCTS BY CATEGORY ID
    @GetMapping("/{super-id}/products")
    @Override
    public ResponseEntity<List<ProductCardDto>> getAllBySuper(@PathVariable(name = "super-id") Long superId) {
        return new ResponseEntity<>(productService.getProductCardByCategoryId(superId), HttpStatus.OK);
    }
    @GetMapping("/{super-id}/products/{id}")
    @Override
    public ResponseEntity<ProductCardDto> getBySuper(@PathVariable(name = "super-id") Long superId,@PathVariable(name="id") Long id) {
        return null;
    }

    //----------POST METHOD---------//

    @PostMapping("/products/all")
    @Override
    public ResponseEntity<Object> createAll(@RequestBody List<Product> items) {
        productService.saveAll(items);
        return new ResponseEntity<>("Products are created successfully",HttpStatus.CREATED);
    }

//    @PostMapping("/products")
//    @Override
//    public ResponseEntity<Object> create(@RequestBody Product item) {
//        productService.save(item);
//        return new ResponseEntity<>("Product is created successfully",HttpStatus.CREATED);
//    }

    @PostMapping("/subcategories/{id}/products")
    @Override
    public ResponseEntity<Object> postBySuper(@PathVariable Long id,@RequestBody Product item) {
        productService.saveBySuper(id,item);
        return new ResponseEntity<>("Products  are created by category have id "+id+" successfully",HttpStatus.CREATED);
    }
    @PostMapping("/subcategories/{id}/products/all")
    @Override
    public ResponseEntity<Object> postAllBySuper(@PathVariable Long id,@RequestBody List<Product> items) {
        productService.saveAllBySuper(id,items);
        return new ResponseEntity<>("Products  are created by category have id "+id+" successfully",HttpStatus.CREATED);
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
