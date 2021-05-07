package com.nh7.ecommerce.service;


import com.nh7.ecommerce.dto.SubCategoryDto;
import com.nh7.ecommerce.dto.UserDto;
import com.nh7.ecommerce.entity.SubCategory;
import com.nh7.ecommerce.entity.User;
import com.nh7.ecommerce.repository.UserRepository;
import com.nh7.ecommerce.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapperUtil modelMapperUtil;

    @Qualifier("passwordEncoder")
    @Autowired
    private PasswordEncoder bcryptEncoder;

    public List<UserDto> findAll(){
        List<User> users= (List<User>) userRepository.findAll();
        return modelMapperUtil.mapList(users,UserDto.class);
    }
    public UserDto findById(long id){
        User user = userRepository.findById(id);
        return modelMapperUtil.map(user,UserDto.class);
    }

    public List<User> saveAll(List<User> users){
        for (User u : users){
            u.setPassword(bcryptEncoder.encode(u.getPassword()));
        }
        return (List<User>) userRepository.saveAll(users);
    }

    public User save(User user){
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public void deleteAll(){
        userRepository.deleteAll();
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
