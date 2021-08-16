package com.nh7.ecommerce.controller.api;

import com.nh7.ecommerce.dto.PostDto;
import com.nh7.ecommerce.dto.ProductDto;
import com.nh7.ecommerce.entity.Comment;
import com.nh7.ecommerce.entity.Post;
import com.nh7.ecommerce.entity.User;
import com.nh7.ecommerce.service.CommentService;
import com.nh7.ecommerce.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/home/posts")
@ControllerAdvice
@CrossOrigin
public class PostApi implements ICrudApi<PostDto, Post>{
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    @GetMapping("/all")
    @Override
    public ResponseEntity<List<PostDto>> getAll() {
        return new ResponseEntity<>(postService.findAll(),HttpStatus.OK);
    }

    @GetMapping(value = {"/products/{id}"})
    @Override
    public ResponseEntity<PostDto> get(@PathVariable Long id) {
        return new ResponseEntity<>(postService.findById(id),HttpStatus.OK);
    }
    @GetMapping(value = {"/products"})
    public ResponseEntity<List<PostDto>> getPostsByUserId(@RequestParam("userId") Long id) {
        return new ResponseEntity<>(postService.findPostsByUserId(id),HttpStatus.OK);
    }
    @GetMapping(value = {"/products/empty"})
    public ResponseEntity<List<PostDto>> getPostsEmptyProductsByUserId(@RequestParam("userId") Long id) {
        return new ResponseEntity<>(postService.findPostEmptyProductsByUserId(id),HttpStatus.OK);
    }
    @GetMapping(value = {"/products/disable"})
    public ResponseEntity<List<PostDto>> getPostsDisableProductByUserId(@RequestParam("userId") Long id) {
        return new ResponseEntity<>(postService.findPostsDisableProductsByUserId(id),HttpStatus.OK);
    }
    @GetMapping(value = {"/products/active"})
    public ResponseEntity<List<PostDto>> getPostsActiveProductByUserId(@RequestParam("userId") Long id) {
        return new ResponseEntity<>(postService.findPostsActiveProductsByUserId(id),HttpStatus.OK);
    }

    //----------POST METHOD---------//

    @PostMapping("/all")
    @Override
    public ResponseEntity<Object> createAll(@RequestBody List<Post> items) {
        postService.saveAll(items);
        return new ResponseEntity<>("Posts are created successfully",HttpStatus.CREATED);
    }

    @PostMapping
//    @PreAuthorize("@appAuthorizer.authorize(authentication,'CREATE',this)")
    @Override
    public ResponseEntity<Object> create(@RequestBody Post item) {
        postService.save(item);
        return new ResponseEntity<>("POST HAS JUST CREATED SUCCESSFULLY",HttpStatus.CREATED);
    }

    @PostMapping("/comments")
    public ResponseEntity<Object> commentAndRating(@RequestBody Comment comment){
        commentService.save(comment);
        return new ResponseEntity<>("ok",HttpStatus.OK);
    }
    //----------PUT METHOD---------//

    @Override
    public ResponseEntity<Object> update(@PathVariable Long id,@RequestBody Post item) {
        item.setId(id);
        return new ResponseEntity<>(item,HttpStatus.OK);
    }
    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updatePost(@PathVariable Long id,@RequestBody PostDto item) {
        item.setId(id);
        return new ResponseEntity<>(postService.updatePost(item),HttpStatus.OK);
    }

    //----------DELETE METHOD---------//

    @Override
    public ResponseEntity<Object> deleteAll() {
        postService.deleteAll();
        return new ResponseEntity<>("Posts are deleted successfully",HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        postService.delete(id);
        return new ResponseEntity<>("Post is deleted successfully",HttpStatus.OK);
    }


}
