package com.nh7.ecommerce.controller.api;

import com.nh7.ecommerce.dto.ShopDto;
import com.nh7.ecommerce.dto.UserDto;
import com.nh7.ecommerce.entity.Shop;
import com.nh7.ecommerce.entity.User;
import com.nh7.ecommerce.model.ProductCardModel;
import com.nh7.ecommerce.service.ShopService;
import com.nh7.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/home/users")
@ControllerAdvice
@CrossOrigin
public class UserApi implements ICrudApi<UserDto, User> {
    @Autowired
    private UserService userService;

    @GetMapping(value = {"/","","/all"})
    @Override
    public ResponseEntity<List<UserDto>> getAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = {"/{id}"})
    @Override
    public ResponseEntity<UserDto> get(@PathVariable Long id) {
        return new ResponseEntity<>(userService.findById(id),HttpStatus.OK);
    }

    //----------POST METHOD---------//

    @PostMapping("/all")
    @Override
    public ResponseEntity<Object> createAll(@RequestBody List<User> items) {
        userService.saveAll(items);
        return new ResponseEntity<>("Users are created successfully",HttpStatus.CREATED);
    }

    @PostMapping(value = {"","/"})
    @Override
    public ResponseEntity<Object> create(@RequestBody User item) {
        userService.save(item);
        return new ResponseEntity<>("User is created successfully",HttpStatus.CREATED);
    }



    //----------PUT METHOD---------//

    @PutMapping("/{id}")
    @Override
    public ResponseEntity<Object> update(@PathVariable Long id,@RequestBody User item) {
        return null;
    }

    //----------DELETE METHOD---------//

    @DeleteMapping("/all")
    @Override
    public ResponseEntity<Object> deleteAll() {
        userService.deleteAll();
        return new ResponseEntity<>("Users are deleted successfully",HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        userService.delete(id);
        return new ResponseEntity<>("User is deleted successfully",HttpStatus.CREATED);
    }
}
