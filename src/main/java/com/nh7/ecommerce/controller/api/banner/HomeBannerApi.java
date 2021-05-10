package com.nh7.ecommerce.controller.api.banner;

import com.nh7.ecommerce.dto.banner.HomeBannerDto;
import com.nh7.ecommerce.service.banner.HomeBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@ControllerAdvice
@RequestMapping(value = "/api/home/home-banner")
public class HomeBannerApi{
    @Autowired
    private HomeBannerService homeBannerService;
    //----------GET METHOD---------//
    @GetMapping("")
    public ResponseEntity<HomeBannerDto> getBanner(){
       return new ResponseEntity<>( homeBannerService.findAll(), HttpStatus.OK);
    }
    //----------POST METHOD---------//
    @PostMapping("")
    public ResponseEntity<HomeBannerDto> postBanner(@RequestBody HomeBannerDto homeBannerDto){
        return new ResponseEntity<>( homeBannerService.create(homeBannerDto), HttpStatus.OK);
    }
    //----------UPDATE METHOD---------//
    //----------DELETE METHOD---------//
}
