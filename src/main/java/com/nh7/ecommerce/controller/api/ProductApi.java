package com.nh7.ecommerce.controller.api;
import com.nh7.ecommerce.dto.ProductCardDto;
import com.nh7.ecommerce.entity.Product;
import com.nh7.ecommerce.service.ProductDetailsService;
import com.nh7.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    @Autowired
    private ProductDetailsService productDetailsService;

    //---------GET METHOD----------//
    @GetMapping(value = {"/products","/products/all","/product/"})
    @Override
    public ResponseEntity<List<ProductCardDto>> getAll() {
        return new ResponseEntity<>(productService.getAll(),HttpStatus.OK);
    }
    //PAGEABLE ALL PRODUCT
    @GetMapping(value = {"/pageable-products"})
    public ResponseEntity<List<ProductCardDto>> getPageableProducts(@RequestParam("page") int page,@RequestParam("limit") int limit ) {
        Pageable pageable = PageRequest.of(page,limit);
        System.out.println("PAGE OFFSET "+  pageable.getOffset()+ "PAGE LIMIT"+  pageable.getPageSize());
        return new ResponseEntity<>(productService.getPageableProducts(pageable),HttpStatus.OK);
    }
  //PAGEABLE PRODUCT BY CATEGORY ID
    @GetMapping(value = {"/{id}/pageable-products"})
    public ResponseEntity<List<ProductCardDto>> getPageableProductsByCategoryId(@PathVariable Long id,@RequestParam int page,@RequestParam int limit){
        Pageable pageable = PageRequest.of(page,limit);
        return new ResponseEntity<>(productService.getPageableProductsByCategoryId(id,pageable),HttpStatus.OK);
    }
    //PAGEABLE PRODUCT BY SUBCATEGORY ID
    @GetMapping(value = {"/subcategories/{id}/pageable-products"})
    public ResponseEntity<List<ProductCardDto>> getPageableProductsBySubcategoryId(@PathVariable Long id,@RequestParam int page,@RequestParam int limit){
        Pageable pageable = PageRequest.of(page,limit);
        return new ResponseEntity<>(productService.getPageableProductsBySubcategoryId(id,pageable),HttpStatus.OK);
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
    @GetMapping("/product-details/{id}")
    public ResponseEntity<Object> getProductDetails(@PathVariable(name = "id") long id){
        return new ResponseEntity<>(productDetailsService.getProductDetailByProductId(id), HttpStatus.OK);
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
