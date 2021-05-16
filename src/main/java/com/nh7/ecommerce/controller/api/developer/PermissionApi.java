package com.nh7.ecommerce.controller.api.developer;

import com.nh7.ecommerce.entity.Permission;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@ControllerAdvice
@RequestMapping(value = "/api/developer/permissions")
public class PermissionApi {

    @GetMapping("/all")
    public void getAll(){

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
