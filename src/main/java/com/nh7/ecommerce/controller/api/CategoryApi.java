package com.nh7.ecommerce.controller.api;
import com.nh7.ecommerce.dto.CategoryDto;
import com.nh7.ecommerce.entity.Category;
import com.nh7.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@ControllerAdvice
@RequestMapping(value = "/api/home/categories")
public class CategoryApi implements ICrudApi<CategoryDto,Category> {

    @Autowired
    private CategoryService categoryService;

    //----------GET METHOD---------//
    @Override
    @GetMapping(value = "")
    public ResponseEntity<List<CategoryDto>> getAll() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    @Override
    public ResponseEntity<CategoryDto> get(@PathVariable(name = "id") Long id) {
        return null;
    }

    //----------POST METHOD---------//
    @PostMapping("/all")
    @Override
    public ResponseEntity<Object> createAll(@RequestBody List<Category> items) {
        categoryService.saveAll(items);
        return new ResponseEntity<>("Categories is created successfully",HttpStatus.CREATED);
    }
    @PostMapping(value = {"/",""})
    @Override
    public ResponseEntity<Object> create(@RequestBody Category item) {
        categoryService.save(item);
        return new ResponseEntity<>("Category is created successfully",HttpStatus.CREATED);
    }



    //----------PUT METHOD---------//
    @PutMapping("/{id}")
    @Override
    public ResponseEntity<Object> update(Long id,@RequestBody Category item) {
        return null;
    }


    //----------DELETE METHOD---------//
    @DeleteMapping("/all")
    @Override
    public ResponseEntity<Object> deleteAll() {
        categoryService.deleteAllCategories();
        return new ResponseEntity<>("All Categories is deleted successfully",HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>("Category is deleted successsfully",HttpStatus.OK);
    }
}


