package com.nh7.ecommerce.service;

import com.nh7.ecommerce.dto.UserDto;
import com.nh7.ecommerce.dto.UserRoleDto;
import com.nh7.ecommerce.entity.Permission;
import com.nh7.ecommerce.entity.Role;
import com.nh7.ecommerce.entity.User;
import com.nh7.ecommerce.repository.RoleRepository;
import com.nh7.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorizationService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    public boolean checkPermission(Long id, String action,String url){
        User user = userRepository.findById(id).get();
        List<Role> roles = user.getRoles();
        if(roles.size()>0){
            for(Role role :roles){
                List<Permission> permissions = role.getPermissions();
                if(permissions.size()>0){
                    for(Permission permission : permissions){
                        if(permission.getAction().getName().equals(action) && permission.getFunc().getUrl().equals(url))
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public UserRoleDto authorizer(UserRoleDto userRoleDto){
        User user = userRepository.findById(userRoleDto.getUserId()).get();
        List<Role> roles = new ArrayList<>();
        for(Long roleId : userRoleDto.getRoleIds()){
            roles.add(roleRepository.findById(roleId).get());
        }
        user.setRoles(roles);
        User u = userRepository.save(user);
        UserRoleDto uDto = new UserRoleDto();
        uDto.setUserId(u.getId());
        List<Long> roleIds= new ArrayList<>();
        List<String> roleNames = new ArrayList<>();
        for(Role role : u.getRoles()){
            roleIds.add(role.getId());
            roleNames.add(role.getRoleName());
        }
        uDto.setRoleIds(roleIds);
        uDto.setRoleNames(roleNames);
        return uDto;
    }
}
