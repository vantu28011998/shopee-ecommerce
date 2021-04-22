package com.nh7.ecommerce.controller.api;

import com.nh7.ecommerce.dto.PostDto;
import com.nh7.ecommerce.entity.Post;
import com.nh7.ecommerce.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/home")
@ControllerAdvice
@CrossOrigin
public class PostApi implements ICrudApi<PostDto, Post>{
    @Autowired
    private PostService postService;

    @GetMapping(value = {"/posts/","/posts","/posts/all"})
    @Override
    public ResponseEntity<List<PostDto>> getAll() {
        return new ResponseEntity<>(postService.findAll(),HttpStatus.OK);
    }

    @GetMapping(value = {"/posts/{id}"})
    @Override
    public ResponseEntity<PostDto> get(@PathVariable Long id) {
        return new ResponseEntity<>(postService.findById(id),HttpStatus.OK);
    }

    //----------POST METHOD---------//

    @PostMapping("/posts/all")
    @Override
    public ResponseEntity<Object> createAll(@RequestBody List<Post> items) {
        postService.saveAll(items);
        return new ResponseEntity<>("Posts are created successfully",HttpStatus.CREATED);
    }

    @PostMapping(value = {"/posts","/posts/"})
    @Override
    public ResponseEntity<Object> create(@RequestBody Post item) {
        return new ResponseEntity<>("Post is created successfully",HttpStatus.CREATED);
    }

    //----------PUT METHOD---------//

    @PutMapping("/posts/{id}")
    @Override
    public ResponseEntity<Object> update(@PathVariable Long id,@RequestBody Post item) {
        return null;
    }

    //----------DELETE METHOD---------//

    @DeleteMapping("/posts/all")
    @Override
    public ResponseEntity<Object> deleteAll() {
        postService.deleteAll();
        return new ResponseEntity<>("Posts are deleted successfully",HttpStatus.OK);
    }
    @DeleteMapping("/posts/{id}")
    @Override
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        postService.delete(id);
        return new ResponseEntity<>("Post is deleted successfully",HttpStatus.OK);
    }


}
