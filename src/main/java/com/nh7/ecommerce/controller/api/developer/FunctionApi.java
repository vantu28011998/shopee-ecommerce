package com.nh7.ecommerce.controller.api.developer;

import com.nh7.ecommerce.entity.Func;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@ControllerAdvice
@RequestMapping(value = "/api/developer/functions")
public class FunctionApi {
    @GetMapping("/all")
    public void getAll(){

    }
    @GetMapping("")
    public void get(){

    }
    @PostMapping("/all")
    public void createAll(@RequestBody List<Func> functions){

    }
    @PostMapping("")
    public void create(@RequestBody Func functions){

    }
    @DeleteMapping
    public void deleteAll(){

    }
    @DeleteMapping
    public void delete(@PathVariable Long id){

    }
}
