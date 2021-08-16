package com.nh7.ecommerce.controller.api.developer;

import com.nh7.ecommerce.dto.PermissionDto;
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
    public ResponseEntity<List<PermissionDto>> getAll(){
        return new ResponseEntity<>(permissionService.findAll(), HttpStatus.OK);
    }
    @GetMapping("")
    public void get(){

    }
    @PostMapping("/all")
    public ResponseEntity<String> createAll(@RequestBody List<PermissionDto> permissionDtos){

        permissionService.saveAll(permissionDtos);
        return new ResponseEntity<>("Permission is created",HttpStatus.OK);
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
