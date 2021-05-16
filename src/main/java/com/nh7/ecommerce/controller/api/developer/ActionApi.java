package com.nh7.ecommerce.controller.api.developer;


import com.nh7.ecommerce.entity.Action;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@ControllerAdvice
@RequestMapping(value = "/api/developer/actions")
public class ActionApi {
    @GetMapping("/all")
    public void getAll(){

    }
    @GetMapping("")
    public void get(){

    }
    @PostMapping("/all")
    public void createAll(@RequestBody List<Action> actions){

    }
    @PostMapping("")
    public void create(@RequestBody Action action){

    }
    @DeleteMapping
    public void deleteAll(){

    }
    @DeleteMapping
    public void delete(@PathVariable Long id){

    }
}
