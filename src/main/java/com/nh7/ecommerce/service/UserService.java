package com.nh7.ecommerce.service;


import com.nh7.ecommerce.dto.SubCategoryDto;
import com.nh7.ecommerce.dto.UserDto;
import com.nh7.ecommerce.entity.SubCategory;
import com.nh7.ecommerce.entity.User;
import com.nh7.ecommerce.enums.AuthProviderEnum;
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
    public Long findIdByEmailAddressAndAuthProvider(String emailAddress,AuthProviderEnum authProvider){
        String input = "LOCAL";
        switch (authProvider){
            case FACEBOOK_USER:
                input = "FACEBOOK_USER";
                break;
            case GOOGLE_USER:
                input = "GOOGLE_USER";
                break;
            default:
                input = "LOCAL";
                break;
        }
        return userRepository.findIdByEmallAddressAndAuthProvider(emailAddress,input);
    }
    public List<Long> findIdsByEmailAddress(String emailaddress){
        return userRepository.findIdsByEmailAddress(emailaddress);
    }
    public Long findIdByUsername(String username){
        return userRepository.findIdByUsername(username);
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
    public void saveUsername(Long id,User user){
        userRepository.saveUsername(id,user.getUsername());
    }
    public void savePassword(Long id,User user){
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        userRepository.savePassword(id,user.getPassword());
    }
    public User saveNoBcrypt(User user){
        return userRepository.save(user);
    }
    public void deleteAll(){
        userRepository.deleteAll();
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
