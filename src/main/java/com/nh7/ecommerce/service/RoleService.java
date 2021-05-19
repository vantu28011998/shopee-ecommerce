package com.nh7.ecommerce.service;

import com.nh7.ecommerce.entity.Permission;
import com.nh7.ecommerce.entity.Role;
import com.nh7.ecommerce.repository.PermissionRepository;
import com.nh7.ecommerce.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    public Role save(Role item){
        List<Permission> permissions =item.getPermissions();
        List<Permission> addPermissions = new ArrayList<>();
        if(permissions.size()>0){
            for(Permission permission : permissions){
                addPermissions.add(permissionRepository.findById(permission.getId()).get());
            }
        }
        return (Role) roleRepository.save(item);
    }
    public List<Role> findAll(){
        return (List<Role>) roleRepository.findAll();
    }
}
