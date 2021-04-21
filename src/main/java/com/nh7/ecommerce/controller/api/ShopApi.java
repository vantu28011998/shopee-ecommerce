package com.nh7.ecommerce.controller.api;

import com.nh7.ecommerce.dto.ShopDto;
import com.nh7.ecommerce.dto.SubCategoryDto;
import com.nh7.ecommerce.entity.Shop;
import com.nh7.ecommerce.entity.SubCategory;
import com.nh7.ecommerce.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/home/shops")
@ControllerAdvice
@CrossOrigin
public class ShopApi implements ICrudApi<ShopDto,Shop>{
    @Autowired
    private ShopService shopService;

    @GetMapping(value = {"/","","/all"})
    @Override
    public ResponseEntity<List<ShopDto>> getAll() {
        return new ResponseEntity<>(shopService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = {"/{id}"})
    @Override
    public ResponseEntity<ShopDto> get(@PathVariable Long id) {
        return new ResponseEntity<>(shopService.findById(id),HttpStatus.OK);
    }

    //----------POST METHOD---------//

    @PostMapping("/all")
    @Override
    public ResponseEntity<Object> createAll(@RequestBody List<Shop> items) {
        shopService.saveAll(items);
        return new ResponseEntity<>("Shops are created successfully",HttpStatus.CREATED);
    }

    @PostMapping(value = {"","/"})
    @Override
    public ResponseEntity<Object> create(@RequestBody Shop item) {
        shopService.save(item);
        return new ResponseEntity<>("Shop is created successfully",HttpStatus.CREATED);
    }



    //----------PUT METHOD---------//

    @PutMapping("/shops/{id}")
    @Override
    public ResponseEntity<Object> update(@PathVariable Long id,@RequestBody Shop item) {
        return null;
    }

    //----------DELETE METHOD---------//

    @DeleteMapping("/all")
    @Override
    public ResponseEntity<Object> deleteAll() {
        shopService.deleteAll();
        return new ResponseEntity<>("Shops are deleted successfully",HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        shopService.delete(id);
        return new ResponseEntity<>("Shop is deleted successfully",HttpStatus.OK);
    }
}
