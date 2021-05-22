package com.nh7.ecommerce.controller.api;

import com.nh7.ecommerce.dto.RoleDto;
import com.nh7.ecommerce.entity.Role;
import com.nh7.ecommerce.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@ControllerAdvice
@RequestMapping(value = "/api/home/roles")
public class RoleApi implements ICrudApi<RoleDto, Role> {
    @Autowired
    private RoleService roleService;
    @GetMapping("/all")
    @Override
    public ResponseEntity<List<RoleDto>> getAll() {
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }
    @GetMapping("")
    @Override
    public ResponseEntity<RoleDto> get(Long id) {
        return null;
    }
    @PostMapping("/all")
    @Override
    public ResponseEntity<Object> createAll(@RequestBody List<Role> items) {
        return null;
    }
    @PostMapping("")
    @Override
    public ResponseEntity<Object> create(@RequestBody Role item) {
        RoleDto roleDto = roleService.save(item);
        if(roleDto == null){
            return new ResponseEntity<>("Role is exist which couldn't create",HttpStatus.OK);
        }else
        return new ResponseEntity<>(roleDto, HttpStatus.OK);
    }
    @PutMapping("/all")
    @Override
    public ResponseEntity<Object> update(Long id, Role item) {
        return null;
    }
    @DeleteMapping("/all")
    @Override
    public ResponseEntity<Object> deleteAll() {
        return null;
    }
    @DeleteMapping
    @Override
    public ResponseEntity<Object> delete(Long id) {
        roleService.delete(id);
        return new ResponseEntity<>("OK",HttpStatus.OK);
    }
}
