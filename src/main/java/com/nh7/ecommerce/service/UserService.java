package com.nh7.ecommerce.service;


import com.nh7.ecommerce.dto.SubCategoryDto;
import com.nh7.ecommerce.dto.UserDto;
import com.nh7.ecommerce.entity.SubCategory;
import com.nh7.ecommerce.entity.User;
import com.nh7.ecommerce.repository.UserRepository;
import com.nh7.ecommerce.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapperUtil modelMapperUtil;

    public List<UserDto> findAll(){
        List<User> users= (List<User>) userRepository.findAll();
        return modelMapperUtil.mapList(users,UserDto.class);
    }
    public UserDto findById(long id){
        User user = userRepository.findById(id);
        return modelMapperUtil.map(user,UserDto.class);
    }

    public List<User> saveAll(List<User> users){
        return (List<User>) userRepository.saveAll(users);
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public void deleteAll(){
        userRepository.deleteAll();
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
