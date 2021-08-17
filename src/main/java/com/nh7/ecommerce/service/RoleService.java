package com.nh7.ecommerce.service;

import com.nh7.ecommerce.dto.RoleDto;
import com.nh7.ecommerce.dto.PermissionDto;
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
    public RoleDto save(Role item){
        Long id = item.getId();
        if(id==null){
            Long have = roleRepository.findByRoleName(item.getRoleName());
            if(have != null ){
                return null;
            }
        }
        System.out.println("OKLA");
        List<Permission> permissions = item.getPermissions();
        List<Permission> savePermission = new ArrayList<>();
        for(Permission permission : permissions){
            Permission p = permissionRepository.findById(permission.getId()).get();
            savePermission.add(p);
        }
        item.setPermissions(savePermission);
        Role role = roleRepository.save(item);
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setRoleName(role.getRoleName());
        List<PermissionDto> pDtos = new ArrayList<>();
        for(Permission permission : role.getPermissions()){
            PermissionDto permissionDto = new PermissionDto();
            permissionDto.setId(permission.getId());
            permissionDto.setPermissionName(permission.getPermissionName());
            permissionDto.setActionId(permission.getAction().getId());
            permissionDto.setActionName(permission.getAction().getName());
//            permissionDto.setFunctionId(permission.getFunc().getId());
//            permissionDto.setFunctionName(permission.getFunc().getName());
            pDtos.add(permissionDto);
        }
        roleDto.setPermissions(pDtos);
        return roleDto;
    }
    public List<RoleDto> findAll(){
        List<Role> roles = (List<Role>) roleRepository.findAll();
        List<RoleDto> roleDtos = new ArrayList<>();
        for(Role role : roles){
            RoleDto roleDto = new RoleDto();
            roleDto.setId(role.getId());
            roleDto.setRoleName(role.getRoleName());
            List<PermissionDto> permissionDtos = new ArrayList<>();
            for(Permission permission : role.getPermissions()){
                PermissionDto permissionDto = new PermissionDto();
                permissionDto.setId(permission.getId());
                permissionDto.setPermissionName(permission.getPermissionName());
                permissionDto.setDescription(permission.getDescription());
                permissionDto.setActionId(permission.getAction().getId());
                permissionDto.setActionName(permission.getAction().getName());
//                permissionDto.setFunctionId(permission.getFunc().getId());
//                permissionDto.setFunctionName(permission.getFunc().getName());
                permissionDtos.add(permissionDto);
            }
            roleDto.setPermissions(permissionDtos);
            roleDtos.add(roleDto);
        }
        return roleDtos;
    }
    public boolean updatePermission(RoleDto role){
       try{
           Role dbRole = roleRepository.findById(role.getId()).get();
           permissionRepository.deleteRelateRolePermission(role.getId());
           for(PermissionDto permission : role.getPermissions()){
               permissionRepository.addRelateRolePermission(role.getId(),permission.getId());
           }
           return true;
       }catch (Exception e){
           return false;
       }
    }
    public void delete(Long id){
        Role role = roleRepository.findById(id).get();
        if(role != null )
        roleRepository.delete(role);
    }
}
