package com.nh7.ecommerce.controller.api;

import com.nh7.ecommerce.dto.CategoryDto;
import com.nh7.ecommerce.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@ControllerAdvice
@RequestMapping(value = "/api/home")
public class RecommenderApi {
    @Autowired
    private RecommendationService recommendationService;
    @GetMapping(value = {"/users/{id}/recommender"})
    public ResponseEntity<String> getProductsByRecommender(@PathVariable Long id,@RequestParam("page") int page,@RequestParam("limit") int limit) {
        Pageable pageable= PageRequest.of(page,limit);
        recommendationService.recommendFor(id,pageable);
        return new ResponseEntity<>("EXECUTED", HttpStatus.OK);
    }
}
