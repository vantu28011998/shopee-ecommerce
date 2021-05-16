package com.nh7.ecommerce.repository;

import com.nh7.ecommerce.entity.Permission;
import org.springframework.data.repository.CrudRepository;

public interface PermissionRepository extends CrudRepository<Permission,Long> {
}
