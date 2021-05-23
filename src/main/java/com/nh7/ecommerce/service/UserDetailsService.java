package com.nh7.ecommerce.service;

import com.nh7.ecommerce.entity.UserDetails;
import com.nh7.ecommerce.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsService {
    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public UserDetails save(UserDetails userDetails){
        return userDetailsRepository.save(userDetails);
    }

    public List<UserDetails> findAll(){
        return (List<UserDetails>) userDetailsRepository.findAll();
    }

    public List<UserDetails> saveAll(List<UserDetails> userDetailsList) {
        try {
            return userDetailsRepository.saveAll(userDetailsList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
