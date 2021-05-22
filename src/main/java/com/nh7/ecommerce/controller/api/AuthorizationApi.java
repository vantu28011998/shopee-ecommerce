package com.nh7.ecommerce.controller.api;

import com.nh7.ecommerce.dto.UserRoleDto;
import com.nh7.ecommerce.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@ControllerAdvice
@RequestMapping(value = "/api/home/authorization")
public class AuthorizationApi {
    @Autowired
    private AuthorizationService authorizationService;
    @PostMapping("")
    public ResponseEntity<UserRoleDto> authorizer(@RequestBody UserRoleDto userRoleDto){
        return  new ResponseEntity<>(authorizationService.authorizer(userRoleDto),HttpStatus.OK);
    }
}
