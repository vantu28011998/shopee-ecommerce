package com.nh7.ecommerce.service;


import com.nh7.ecommerce.dto.SubCategoryDto;
import com.nh7.ecommerce.dto.UserDto;
import com.nh7.ecommerce.entity.Role;
import com.nh7.ecommerce.entity.SubCategory;
import com.nh7.ecommerce.entity.User;
import com.nh7.ecommerce.entity.UserDetails;
import com.nh7.ecommerce.enums.AuthProviderEnum;
import com.nh7.ecommerce.repository.UserDetailsRepository;
import com.nh7.ecommerce.repository.UserRepository;
import com.nh7.ecommerce.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapperUtil modelMapperUtil;

    @Autowired
    private UserDetailsRepository userDetailsRepository;
    @Qualifier("passwordEncoder")
    @Autowired
    private PasswordEncoder bcryptEncoder;

    private void setRoleFromEntityToDto(User user,UserDto userDto){
        List<Role> roles = user.getRoles();
        List<String> roleNames = new ArrayList<>();
        for(Role role : roles){
            roleNames.add(role.getRoleName());
        }
        userDto.setRoleNames(roleNames);
    }
    public List<UserDto> findAll(){
        List<User> users= (List<User>) userRepository.findAll();
        List<UserDto> userDtos = modelMapperUtil.mapList(users,UserDto.class);
        for(int i=0;i<userDtos.size();i++){
            setRoleFromEntityToDto(users.get(i),userDtos.get(i));
        }
        return userDtos;
    }
    public UserDto findById(long id){
        User user = userRepository.findById(id);
        UserDto userDto = modelMapperUtil.map(user,UserDto.class);
        setRoleFromEntityToDto(user,userDto);
        return userDto;
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
        String username = user.getUsername();
        User checkUser = userRepository.findByUsername(username);
        if(checkUser != null){
            user.setUsername(null);
            return user;
        }
        List<Long> ids = userRepository.findIdsByEmailAddress(user.getEmailAddress());
        if(ids.size()>0){
            user.setEmailAddress(null);
            return user;
        }
        UserDetails userDetails = new UserDetails();
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        User saveUser = userRepository.save(user);
        userDetails.setUser(saveUser);
        userDetailsRepository.save(userDetails);
        return saveUser;
    }
    public boolean updateUsername(Long id,User user){
        Long userId = findIdByUsername(user.getUsername());
        if(userId == null){
            userRepository.saveUsername(id,user.getUsername());
            return true;
        }
        return false;
    }
    public void savePassword(Long id,User user){
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        userRepository.savePassword(id,user.getPassword());
    }
    public User saveNoBcrypt(User user){
        return userRepository.save(user);
    }
    public void deleteAll(){
        userDetailsRepository.deleteAll();
        userRepository.deleteAll();
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
