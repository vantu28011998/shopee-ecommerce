package com.nh7.ecommerce.controller.api;

import com.nh7.ecommerce.dto.SubCategoryDto;
import com.nh7.ecommerce.entity.Category;
import com.nh7.ecommerce.entity.SubCategory;
import com.nh7.ecommerce.service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@ControllerAdvice
@RequestMapping(value = "/api/home/categories")
public class SubCategoryApi implements ICrudApi<SubCategoryDto, SubCategory>{

    @Autowired
    private SubCategoryService subCategoryService;

    //----------GET METHOD---------//

    @GetMapping(value = {"/subcategories","/subcategories/","/subcategories/all"})
    @Override
    public ResponseEntity<List<SubCategoryDto>> getAll() {
        return new ResponseEntity<>(subCategoryService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = {"/subcategories/{id}"})
    @Override
    public ResponseEntity<SubCategoryDto> get(@PathVariable Long id) {
        return new ResponseEntity<>(subCategoryService.findById(id),HttpStatus.OK);
    }

    //----------POST METHOD---------//

    @PostMapping("/subcategories/all")
    @Override
    public ResponseEntity<Object> createAll(@RequestBody List<SubCategory> items) {
        if (subCategoryService.saveAll(items)){
            return new ResponseEntity<>("Subcategories are created successfully",HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Subcategories didn't create", HttpStatus.OK);
        }
    }

    @PostMapping(value = {"/subcategories","/subcategories/"})
    @Override
    public ResponseEntity<Object> create(@RequestBody SubCategory item) {
        subCategoryService.save(item);
        return new ResponseEntity<>("Subcategory is created successfully",HttpStatus.CREATED);
    }

    //----------PUT METHOD---------//

    @PutMapping("/subcategories/{id}")
    @Override
    public ResponseEntity<Object> update(@PathVariable Long id,@RequestBody SubCategory item) {
        return null;
    }

    //----------DELETE METHOD---------//

    @DeleteMapping("/subcategories/all")
    @Override
    public ResponseEntity<Object> deleteAll() {
        subCategoryService.deleteAll();
        return new ResponseEntity<>("Subcategories are deleted successfully",HttpStatus.OK);
    }
    @DeleteMapping("/subcategories/{id}")
    @Override
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        subCategoryService.delete(id);
        return new ResponseEntity<>("Subcategory is deleted successfully",HttpStatus.OK);
    }
}
