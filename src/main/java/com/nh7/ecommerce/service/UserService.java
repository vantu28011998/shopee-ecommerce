package com.nh7.ecommerce.service;

import com.nh7.ecommerce.entity.Account;
import com.nh7.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    Account getUserById(int id){return userRepository.findById(id);}
    List<Account> getAllUser(){
        return (List<Account>) userRepository.findAll();
    }
    boolean deleteUser(int id){
        try{
            userRepository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    boolean saveUser(Account user){
        try {
            userRepository.save(user);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
