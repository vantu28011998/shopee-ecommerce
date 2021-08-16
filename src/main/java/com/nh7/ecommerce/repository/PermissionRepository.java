package com.nh7.ecommerce.repository;

import com.nh7.ecommerce.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,Long> {
    @Modifying
    @Query(value = "INSERT INTO role_permission(role_id, permission_id) VALUES(:roleId,:permissionId);",nativeQuery = true)
    @Transactional
    void addRelateRolePermission(@Param("roleId") Long roleId, @Param("permissionId") Long permissionId);
    @Modifying
    @Query(value = "DELETE FROM role_permission WHERE role_id=:roleId",nativeQuery = true)
    @Transactional
    void deleteRelateRolePermission(@Param("roleId") Long roleId);
}
