package com.nh7.ecommerce.service;

import com.nh7.ecommerce.dto.developer.PermissionDto;
import com.nh7.ecommerce.entity.Action;
import com.nh7.ecommerce.entity.Func;
import com.nh7.ecommerce.entity.Permission;
import com.nh7.ecommerce.repository.ActionRepository;
import com.nh7.ecommerce.repository.FunctionRepository;
import com.nh7.ecommerce.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionService {
    @Autowired
    private ActionRepository actionRepository;
    @Autowired
    private FunctionRepository functionRepository;
    @Autowired
    private PermissionRepository permissionRepository;
    public void saveAll(List<PermissionDto> permissionDtos){
        List<Permission> permissions = new ArrayList<>();
        for(PermissionDto permissionDto : permissionDtos){
            Permission permission = new Permission();
            permission.setPermissionName(permissionDto.getPermissionName());
            permission.setDescription(permissionDto.getDescription());
            Action action = actionRepository.findById(permissionDto.getActionId()).get();
            permission.setAction(action);
            Func func = functionRepository.findById(permissionDto.getFunctionId()).get();
            permission.setFunc(func);
            permissions.add(permission);
        }
        permissionRepository.saveAll(permissions);
    }
    public List<PermissionDto> findAll(){
        List<PermissionDto> permissionDtos = new ArrayList<>();
        List<Permission> permissions = (List<Permission>) permissionRepository.findAll();
        for(Permission permission : permissions ){
            PermissionDto permissionDto = new PermissionDto();
            permissionDto.setId(permission.getId());
            permissionDto.setPermissionName(permission.getPermissionName());
            permissionDto.setDescription(permission.getDescription());
            permissionDto.setActionId(permission.getAction().getId());
            permissionDto.setActionName(permission.getAction().getName());
            permissionDto.setFunctionId(permission.getFunc().getId());
            permissionDto.setFunctionName(permission.getFunc().getName());
            permissionDtos.add(permissionDto);
        }
        return permissionDtos;

    }
}
