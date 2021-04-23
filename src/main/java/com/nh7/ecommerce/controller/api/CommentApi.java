package com.nh7.ecommerce.controller.api;

import com.nh7.ecommerce.dto.CommentCardDto;
import com.nh7.ecommerce.entity.Comment;
import com.nh7.ecommerce.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/home")
@ControllerAdvice
@CrossOrigin
public class CommentApi implements ICrudApi<CommentCardDto, Comment> {

    @Autowired
    private CommentService commentService;
    @Override
    public ResponseEntity<List<CommentCardDto>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<CommentCardDto> get(Long id) {
        return null;
    }

    @Override
    @PostMapping("/comments/all")
    public ResponseEntity<Object> createAll(@RequestBody List<Comment> items) {
        if (commentService.saveAll(items)){
            return new ResponseEntity<>("Comment List create successfully", HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>("Comment List create failed", HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity<Object> create(Comment item) {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(Long id, Comment item) {
        return null;
    }

    @Override
    public ResponseEntity<Object> deleteAll() {
        return null;
    }

    @Override
    public ResponseEntity<Object> delete(Long id) {
        return null;
    }
}
