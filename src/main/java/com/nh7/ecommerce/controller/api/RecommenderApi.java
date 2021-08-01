package com.nh7.ecommerce.controller.api;

import com.nh7.ecommerce.dto.CategoryDto;
import com.nh7.ecommerce.dto.ProductCardDto;
import com.nh7.ecommerce.dto.pageable.ResponsePageable;
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
    @GetMapping("/users/{id}/recommender")
    public ResponseEntity<ResponsePageable<ProductCardDto>> getProductsByRecommender(@PathVariable Long id, @RequestParam("page") int page, @RequestParam("limit") int limit) {
        Pageable pageable= PageRequest.of(page,limit);
        return new ResponseEntity<>(recommendationService.recommendFor(id,pageable), HttpStatus.OK);
    }
    @GetMapping("/anonymous/recommender")
    public ResponseEntity<List<ProductCardDto>> getProductsByRecommenderForAnonymous(@RequestParam("page") int page,@RequestParam("limit") int limit){
        return new ResponseEntity<>(null,HttpStatus.OK);
    }
}
