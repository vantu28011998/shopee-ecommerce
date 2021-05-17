package com.nh7.ecommerce.service;

import com.nh7.ecommerce.entity.Permission;
import com.nh7.ecommerce.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;
    public void save(Permission permission){

    }
    public List<Permission> findAll(){
        return (List<Permission>) permissionRepository.findAll();
    }
}
