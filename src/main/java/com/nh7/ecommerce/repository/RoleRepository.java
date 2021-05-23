package com.nh7.ecommerce.repository;

import com.nh7.ecommerce.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query(value ="SELECT id FROM ecommerce.role WHERE role_name=:roleName" ,nativeQuery = true)
    Long findByRoleName(@Param("roleName") String roleName);
}
