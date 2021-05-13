package com.nh7.ecommerce.repository;


import com.nh7.ecommerce.entity.Category;
import com.nh7.ecommerce.entity.User;
import com.nh7.ecommerce.enums.AuthProviderEnum;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByUsernameAndPassword(String var1, String var2);
    User findById(long id);
    List<User> findAll();
    @Modifying
    @Query(value = "DELETE FROM u WHERE u.id=:id",nativeQuery = true)
    @Transactional
    void deleteById(@Param("id") long id);
    User findByUsername(String var);
    @Query(value = "SELECT u.id FROM u",nativeQuery = true)
    List<Long> findAllId();
        @Query(value = "SELECT u.id FROM u WHERE u.email_address=:emailAddress AND u.auth_provider=:authProvider",nativeQuery = true)
    Long findIdByEmallAddressAndAuthProvider(@Param("emailAddress") String emailAddress, @Param("authProvider")String authProvider);
    @Query(value = "SELECT u.id FROM u WHERE u.email_address=:emailAddress",nativeQuery = true)
        List<Long> findIdsByEmailAddress(@Param("emailAddress") String emailAddress);
    @Query(value = "SELECT u.id FROM u WHERE u.username=:username",nativeQuery = true)
    Long findIdByUsername(@Param("username") String username);
    @Modifying
    @Query(value = "UPDATE u SET username=:username WHERE id=:id",nativeQuery = true)
    @Transactional
    void saveUsername(@Param("id") Long id,@Param("username") String username);
    @Modifying
    @Query(value = "UPDATE u SET password=:password WHERE id=:id",nativeQuery = true)
    @Transactional
    void savePassword(@Param("id") Long id,@Param("password") String password);

}
