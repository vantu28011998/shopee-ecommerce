package com.nh7.ecommerce.repository;


import com.nh7.ecommerce.entity.Category;
import com.nh7.ecommerce.entity.User;
import com.nh7.ecommerce.enums.AuthProviderEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
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

    @Query(value = "select count(u.id) from u \n" +
            "join user_role on u.id = user_role.user_id\n" +
            "join role on role.id = user_role.role_id\n" +
            "where role.role_name = 'VENDOR'\n" +
            "and date_part('month',u.created_at) = :month and date_part('year',u.created_at) = :year", nativeQuery = true)
    Integer getCountVendorInMonth(@Param(("month")) int month, @Param("year") int year);

    @Query(value = "select u.* from u \n" +
            "join user_role ur on u.id = ur.user_id\n" +
            "join role r on r.id = ur.role_id\n" +
            "where r.role_name = 'VENDOR'", nativeQuery = true)
    List<User> getAllVendors();

    @Query(value = "update u set enable = false where u.id = :id returning enable", nativeQuery = true)
    boolean lockUserById(@Param("id") long id);

    @Query(value = "update u set enable = true where u.id = :id returning enable", nativeQuery = true)
    boolean unlockUserById(@Param("id") long id);
}
