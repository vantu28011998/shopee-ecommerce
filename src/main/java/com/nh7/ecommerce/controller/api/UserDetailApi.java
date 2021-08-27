package com.nh7.ecommerce.controller.api;

import com.nh7.ecommerce.dto.UserInfoDto;
import com.nh7.ecommerce.entity.UserDetails;
import com.nh7.ecommerce.service.UserDetailsService;
import com.nh7.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/home/user-details")
@ControllerAdvice
@CrossOrigin
public class UserDetailApi implements ICrudApi<UserDetails,UserDetails> {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserService userService;
    @GetMapping(value = {"/","","/all"})
    @Override
    public ResponseEntity<List<UserDetails>> getAll() {
        return new ResponseEntity<>(userDetailsService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getUserInfoById(@PathVariable(name = "id") long id) {
        return new ResponseEntity<>(userDetailsService.getUserInfoByUserId(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDetails> get(Long id) {
        return null;
    }

    @Override
    @PostMapping("/all")
    public ResponseEntity<Object> createAll(@RequestBody List<UserDetails> items) {
        if (userDetailsService.saveAll(items) != null) {
            return new ResponseEntity<>("Oke roi nha", HttpStatus.OK);
        }
        return new ResponseEntity<>("Chua Ok nha", HttpStatus.OK);
    }

    // (USER) for update user details
    @PutMapping(value = "")
    public ResponseEntity<Object> updateUserDetails(@RequestBody UserInfoDto userInfoDto) {
        if (userService.updateUserDetails(userInfoDto)) {
            return new ResponseEntity<>("Update user details success.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Update user details fail", HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Object> create(UserDetails item) {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(Long id, UserDetails item) {
        return null;
    }

    @Override
    public ResponseEntity<Object> deleteAll() {
        return null;
    }

    @Override
    public ResponseEntity<Object> delete(Long id) {
        return null;
    }
}
