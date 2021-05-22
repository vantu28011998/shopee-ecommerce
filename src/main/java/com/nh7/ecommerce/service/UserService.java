package com.nh7.ecommerce.service;


import com.nh7.ecommerce.dto.SubCategoryDto;
import com.nh7.ecommerce.dto.UserDto;
import com.nh7.ecommerce.dto.UserInfoDto;
import com.nh7.ecommerce.dto.VendorDto;
import com.nh7.ecommerce.entity.SubCategory;
import com.nh7.ecommerce.entity.User;
import com.nh7.ecommerce.enums.AuthProviderEnum;
import com.nh7.ecommerce.repository.OrderRepository;
import com.nh7.ecommerce.repository.ProductRepository;
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
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

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
        userRepository.deleteAll();
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }

    public User getUserById(long id) { return userRepository.findById(id);}

    // (Admin)
    public Integer getCountVendorsInMonth(int month, int year) { return userRepository.getCountVendorInMonth(month, year);}

    // (Admin)
    public UserInfoDto getUserInfoById(long id) {
        User user = userRepository.findById(id);
        UserInfoDto userInfo = new UserInfoDto();
        userInfo.setId(id);
        userInfo.setAvatar(user.getAvatar());
        userInfo.setCreatedAt(user.getCreatedAt());
        userInfo.setUserName(user.getUsername());
        userInfo.setEmail(user.getEmailAddress());
        if (user.getUserDetails() != null) {
            userInfo.setFullName(user.getUserDetails().getFullName());
            userInfo.setAddress(user.getUserDetails().getAddress());
            userInfo.setPhoneNumber(user.getUserDetails().getPhoneNumber());
            userInfo.setGender(user.getUserDetails().getGender());
            userInfo.setDateofbirth(user.getUserDetails().getDayOfBird());
        }
        return userInfo;
    }

    // (Admin)
    public List<VendorDto> getAllVendors() {
        List<User> userList = userRepository.getAllVendors();
        List<VendorDto> vendorDtoList = new ArrayList<>();
        for (User user : userList) {
            VendorDto vendorDTO = new VendorDto();
            vendorDTO.setId(user.getId());
            vendorDTO.setCreatedAt(user.getCreatedAt());
            if (user.getUserDetails() != null) {
                vendorDTO.setVendorAvatar(user.getAvatar());
                vendorDTO.setVendorName(user.getUserDetails().getFullName());
            }
            if (user.getShop() != null) {
                vendorDTO.setShopName(user.getShop().getName());
                vendorDTO.setProducts(productRepository.countProductByPostUserShop(user.getShop().getId()));
                vendorDTO.setRevenue(orderRepository.getTotalRevenue(user.getShop().getId()));
            }
            vendorDtoList.add(vendorDTO);
        }
        return vendorDtoList;
    }

    // (Admin) get All for Admin
    public List<User> getAllForAdmin(){
        return (List<User>) userRepository.findAll();
    }

}
