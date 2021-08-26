package com.nh7.ecommerce.service;

import com.nh7.ecommerce.dto.UserInfoDto;
import com.nh7.ecommerce.entity.User;
import com.nh7.ecommerce.entity.UserDetails;
import com.nh7.ecommerce.repository.UserDetailsRepository;
import com.nh7.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsService {
    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Autowired
    private UserRepository userRepository;

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

    public UserInfoDto getUserInfoByUserId(long userId) {
        UserInfoDto userInfoDto = new UserInfoDto();
        User user = userRepository.findById(userId);
        if (user.getUserDetails()==null) return userInfoDto;
        userInfoDto.setFullName(user.getUserDetails().getFullName());
        userInfoDto.setAvatar(user.getAvatar());
        userInfoDto.setEmail(user.getEmailAddress());
        userInfoDto.setAddress(user.getUserDetails().getAddress());
        userInfoDto.setCreatedAt(user.getCreatedAt());
        userInfoDto.setDateofbirth(user.getUserDetails().getDayOfBird());
        userInfoDto.setGender(user.getUserDetails().getGender());
        userInfoDto.setPhoneNumber(user.getUserDetails().getPhoneNumber());
        userInfoDto.setId(user.getId());
        userInfoDto.setUserName(user.getUsername());
        return userInfoDto;
    }
}
