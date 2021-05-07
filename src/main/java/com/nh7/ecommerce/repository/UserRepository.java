package com.nh7.ecommerce.repository;


import com.nh7.ecommerce.entity.Category;
import com.nh7.ecommerce.entity.User;
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
<<<<<<< HEAD
    User findByUsername(String var);
=======
    @Query(value = "SELECT u.id FROM u",nativeQuery = true)
    List<Long> findAllId();
>>>>>>> f42a70451a2273a0404bd5bec2630a153860c380
}
