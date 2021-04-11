package com.nh7.ecommerce.service;

import com.nh7.ecommerce.entity.User;
import com.nh7.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    User getUserById(int id){return userRepository.findById(id);}
    List<User> getAllUser(){
        return (List<User>) userRepository.findAll();
    }
    boolean deleteUser(int id){
        try{
            userRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    boolean saveUser(User user){
        try {
            userRepository.save(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
