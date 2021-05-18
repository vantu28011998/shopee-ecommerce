package com.nh7.ecommerce.controller.api.developer;

import com.nh7.ecommerce.dto.developer.FunctionDto;
import com.nh7.ecommerce.entity.Func;
import com.nh7.ecommerce.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@ControllerAdvice
@RequestMapping(value = "/api/developer/functions")
public class FunctionApi {
    @Autowired
    private FunctionService functionService;
    @GetMapping("/all")
    public ResponseEntity<List<Func>> getAll(){
        return new ResponseEntity<>(functionService.findAll(), HttpStatus.OK);
    }
    @GetMapping("")
    public void get(){

    }
    @PostMapping("/all")
    public ResponseEntity<String> createAll(@RequestBody List<FunctionDto> functionDtos){
        functionService.saveAll(functionDtos);
        return new ResponseEntity<>("Functions are created", HttpStatus.OK);
    }
    @PostMapping("")
    public void create(@RequestBody Func functions){

    }
    @DeleteMapping("/all")
    public void deleteAll(@RequestBody List<Func> functions){

    }
    @DeleteMapping
    public void delete(@PathVariable Long id){

    }
}
