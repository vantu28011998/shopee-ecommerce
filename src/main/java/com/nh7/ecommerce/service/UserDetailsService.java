package com.nh7.ecommerce.service;

import com.nh7.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {
    @Autowired
    private UserRepository userRepository;
}
