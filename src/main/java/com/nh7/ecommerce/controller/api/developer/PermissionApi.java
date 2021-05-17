package com.nh7.ecommerce.controller.api.developer;

import com.nh7.ecommerce.entity.Permission;
import com.nh7.ecommerce.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@ControllerAdvice
@RequestMapping(value = "/api/developer/permissions")
public class PermissionApi {

    @Autowired
    private PermissionService permissionService;
    @GetMapping("/all")
    public ResponseEntity<List<Permission>> getAll(){
        System.out.println("PERMISSION");
        return new ResponseEntity<>(permissionService.findAll(), HttpStatus.OK);
    }
    @GetMapping("")
    public void get(){

    }
    @PostMapping("/all")
    public void createAll(@RequestBody List<Permission> permissions){

    }
    @PostMapping("")
    public void create(@RequestBody Permission permission){

    }
    @DeleteMapping("/all")
    public void deleteAll(){

    }
    @DeleteMapping
    public void delete(@PathVariable Long id){

    }

}
