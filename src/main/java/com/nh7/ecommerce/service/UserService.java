package com.nh7.ecommerce.service;


import com.nh7.ecommerce.dto.UserDto;
import com.nh7.ecommerce.entity.Role;
import com.nh7.ecommerce.dto.UserInfoDto;
import com.nh7.ecommerce.dto.VendorDto;
import com.nh7.ecommerce.entity.Shop;
import com.nh7.ecommerce.entity.User;
import com.nh7.ecommerce.entity.UserDetails;
import com.nh7.ecommerce.enums.AuthProviderEnum;
import com.nh7.ecommerce.repository.*;
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

    @Autowired
    private ShopRepository shopRepository;

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return bcryptEncoder.matches(rawPassword, encodedPassword);
    }

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

    public User saveLocal(User user){
        String username = user.getUsername();
        User checkUser = userRepository.findByUsername(username);
        List<Long> ids = userRepository.findIdsByEmailAddress(user.getEmailAddress());
        if(checkUser != null || ids.size()>0) return null;
        UserDetails userDetails = new UserDetails();
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        User saveUser = userRepository.save(user);
        userDetails.setUser(saveUser);
        userDetailsRepository.save(userDetails);
        Shop shop = new Shop();
        shop.setUser(saveUser);
        shopRepository.save(shop);
        return saveUser;
    }
    public User saveSocial(User user){
        User saveUser = userRepository.save(user);
        Shop shop = new Shop();
        shop.setUser(saveUser);
        shopRepository.save(shop);
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

    // (Admin) get All for Admin
    public List<User> getAllForAdmin(){
        return (List<User>) userRepository.findAll();
    }

    // (Admin) lock user by Id
    public boolean lockUserById(long id) {
        return userRepository.lockUserById(id);
    }
    // (Admin) unlock user by id
    public boolean unlockUserById(long id) {
        return userRepository.unlockUserById(id);
    }
    // (Admin) count account in system
    public int countAccount() {
        return userRepository.countById();
    }
    // (Admin) set Role user by id


}
