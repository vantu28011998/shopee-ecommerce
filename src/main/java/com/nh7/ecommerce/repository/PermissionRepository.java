package com.nh7.ecommerce.repository;

import com.nh7.ecommerce.entity.Permission;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends CrudRepository<Permission,Long> {
}
