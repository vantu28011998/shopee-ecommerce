package com.nh7.ecommerce.controller.api.developer;


import com.nh7.ecommerce.dto.developer.ActionDto;
import com.nh7.ecommerce.entity.Action;
import com.nh7.ecommerce.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin
@RestController
@ControllerAdvice
@RequestMapping(value = "/api/developer/actions")
public class ActionApi {
    @Autowired
    private ActionService actionService;
    @GetMapping("/all")
    public ResponseEntity<List<ActionDto>> getAll(){
        return new ResponseEntity<>(actionService.findAll(),HttpStatus.OK);
    }
    @GetMapping("")
    public void get(){

    }
    @PostMapping("/all")
    public ResponseEntity<String> createAll(@RequestBody List<ActionDto> actions){
        actionService.saveAll(actions);
        return new ResponseEntity<>("Actions are created", HttpStatus.OK);
    }
    @PostMapping("")
    public void create(@RequestBody Action action){

    }
    @DeleteMapping("/all")
    public void deleteAll(@RequestBody List<Action> actions){

    }
    @DeleteMapping
    public void delete(@PathVariable Long id){

    }
}
