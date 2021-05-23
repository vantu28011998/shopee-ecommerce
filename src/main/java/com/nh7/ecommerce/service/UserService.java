package com.nh7.ecommerce.service;


import com.nh7.ecommerce.dto.UserDto;
import com.nh7.ecommerce.entity.Role;
import com.nh7.ecommerce.dto.UserInfoDto;
import com.nh7.ecommerce.dto.VendorDto;
import com.nh7.ecommerce.entity.User;
import com.nh7.ecommerce.entity.UserDetails;
import com.nh7.ecommerce.enums.AuthProviderEnum;
import com.nh7.ecommerce.repository.UserDetailsRepository;
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
        userDetailsRepository.deleteAll();
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
