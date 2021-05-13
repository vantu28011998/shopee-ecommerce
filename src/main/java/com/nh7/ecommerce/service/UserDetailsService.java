package com.nh7.ecommerce.service;

import com.nh7.ecommerce.entity.User;
import com.nh7.ecommerce.entity.UserDetails;
import com.nh7.ecommerce.repository.UserDetailsRepository;
import com.nh7.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService {
    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public UserDetails save(UserDetails userDetails){
        return userDetailsRepository.save(userDetails);
    }
}
