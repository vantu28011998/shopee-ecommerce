package com.nh7.ecommerce.controller.api;

import com.nh7.ecommerce.dto.ShopDto;
import com.nh7.ecommerce.entity.Shop;
import com.nh7.ecommerce.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/home")
@ControllerAdvice
@CrossOrigin
public class ShopApi implements ICrudApi<ShopDto,Shop>,IPostBySuperApi<Shop>{
    @Autowired
    private ShopService shopService;

    @GetMapping(value = {"/shops/","","/shops/all","/shops"})
    @Override
    public ResponseEntity<List<ShopDto>> getAll() {
        return new ResponseEntity<>(shopService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = {"/shops/{id}"})
    @Override
    public ResponseEntity<ShopDto> get(@PathVariable Long id) {
        return new ResponseEntity<>(shopService.findById(id),HttpStatus.OK);
    }

    //----------POST METHOD---------//

    @PostMapping("/shops/all")
    @Override
    public ResponseEntity<Object> createAll(@RequestBody List<Shop> items) {
        shopService.saveAll(items);
        return new ResponseEntity<>("Shops are created successfully",HttpStatus.CREATED);
    }

    @PostMapping(value = {"/shops","/shops/"})
    @Override
    public ResponseEntity<Object> create(@RequestBody Shop item) {
        shopService.save(item);
        return new ResponseEntity<>("Shop is created successfully",HttpStatus.CREATED);
    }
    @PostMapping("/users/{id}/shops")
    @Override
    public ResponseEntity<Object> postBySuper(@PathVariable Long id,@RequestBody Shop item) {
        shopService.saveBySuper(id,item);
        return new ResponseEntity<>("Shop is created successfully",HttpStatus.CREATED);
    }
    @PostMapping("/users/{id}/shops/all")
    @Override
    public ResponseEntity<Object> postAllBySuper(@PathVariable Long id,@RequestBody List<Shop> items) {
        shopService.saveAllBySuper(id,items);
        return new ResponseEntity<>("Shops are created successfully",HttpStatus.CREATED);
    }



    //----------PUT METHOD---------//

    @PutMapping("/shops/{id}")
    @Override
    public ResponseEntity<Object> update(@PathVariable Long id,@RequestBody Shop item) {
        return null;
    }

    //----------DELETE METHOD---------//

    @DeleteMapping("/shops/all")
    @Override
    public ResponseEntity<Object> deleteAll() {
        shopService.deleteAll();
        return new ResponseEntity<>("Shops are deleted successfully",HttpStatus.OK);
    }
    @DeleteMapping("/shops/{id}")
    @Override
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        shopService.delete(id);
        return new ResponseEntity<>("Shop is deleted successfully",HttpStatus.OK);
    }


}
