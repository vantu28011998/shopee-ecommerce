package com.nh7.ecommerce.controller.api;
import com.nh7.ecommerce.dto.CategoryDto;
import com.nh7.ecommerce.entity.Category;
import com.nh7.ecommerce.entity.Product;
import com.nh7.ecommerce.service.CategoryService;
import com.nh7.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/api/home")
@ControllerAdvice
@CrossOrigin
public class HomeApi {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    //---------GET METHOD----------//
    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDto>> getCategories(){
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/categories/{name}")
    public List<Category> getCategoriesByName(@PathVariable(value="name")String name){
        return categoryService.findByName(name);
    }
    @GetMapping("/products/{category_name}")
    public List<Product> getProductsByCategoryName(@PathVariable(value="category_name")String categoryName){
        //productService.getProductByCategoryName(categoryName)
        return null;
    }
    //---------POST METHOD----------//
    //Post 1 category
    @PostMapping("/categories")
    public ResponseEntity<Object> createCategory(@RequestBody Category category){
        categoryService.save(category);
        return new ResponseEntity<>("Category is created successfully",HttpStatus.CREATED);
    }
    //Post 1 list category
    @PostMapping("/category-list")
    public ResponseEntity<Object> createCategory(@RequestBody List<Category> categoryList){
        categoryService.saveAll(categoryList);
        return new ResponseEntity<>("Category is created successfully",HttpStatus.CREATED);
    }
    //---------PUT METHOD----------//
    //---------DELETE METHOD----------//
    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Long id){
        categoryService.deleteCategory(id);
        return new ResponseEntity<>("Category is deleted successsfully",HttpStatus.OK);
    }
    @DeleteMapping("/category-list")
    public ResponseEntity<Object> deleteAllCategory(){
        categoryService.deleteAllCategory();
        return new ResponseEntity<>("All Categories is deleted successfully",HttpStatus.OK);
    }


}
