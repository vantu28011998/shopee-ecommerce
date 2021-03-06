package com.nh7.ecommerce.controller.api;

import com.nh7.ecommerce.dto.UserDto;
import com.nh7.ecommerce.security.jwt.JwtTokenProvider;
import com.nh7.ecommerce.model.jwt.JwtRequest;
import com.nh7.ecommerce.model.jwt.JwtResponse;
import com.nh7.ecommerce.service.JwtUserDetailsService;
import com.nh7.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@ControllerAdvice
@RequestMapping(value = "/api/login")
public class LoginApi {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    @Autowired
    private UserService userService;

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = jwtUserDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenProvider.generateToken(userDetails);
        Long id =userService.findIdByUsername(authenticationRequest.getUsername());
        UserDto userDto = userService.findById(id);
        return ResponseEntity.ok(new JwtResponse(userDto,token));
    }
}
