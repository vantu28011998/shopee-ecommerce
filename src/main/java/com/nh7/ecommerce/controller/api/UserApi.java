package com.nh7.ecommerce.controller.api;

import com.nh7.ecommerce.dto.UserDto;
import com.nh7.ecommerce.entity.User;
import com.nh7.ecommerce.service.UserService;
import com.nh7.ecommerce.util.ModelMapperUtil;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/home/users")
@ControllerAdvice
@CrossOrigin
public class UserApi implements ICrudApi<UserDto, User> {
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapperUtil modelMapperUtil;
    @GetMapping(value = {"/","","/all"})
//    @PreAuthorize("@appAuthorizer.authorize(authentication,'VIEW',this)")
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
        User user = userService.save(item);
        if(user.getUsername()==null) {
            return new ResponseEntity<>("User doesn't create because username have existed",HttpStatus.OK);
        }
        if(user.getEmailAddress()==null) {
            return new ResponseEntity<>("User doesn't create because email address have existed",HttpStatus.OK);
        }
        UserDto userDto = modelMapperUtil.map(user,UserDto.class);
        return new ResponseEntity<>(userDto,HttpStatus.CREATED);
    }



    //----------PUT METHOD---------//


    @PutMapping("/{id}")
    @Override
    public ResponseEntity<Object> update(@PathVariable(name = "id") Long id,@RequestBody User item) {
        return null;
    }
    @PutMapping(value = "/{id}/username")
    public ResponseEntity<String> updateUsername(@PathVariable Long id,@RequestBody User user){
        if(userService.updateUsername(id,user)){
            return new ResponseEntity<>("Username is updated successfully",HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Username updated fail",HttpStatus.OK);
        }
    }
    //----------DELETE METHOD---------//

    @DeleteMapping("/all")
    @Override
    public ResponseEntity<Object> deleteAll() {
        System.out.println("CALL");
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
