package com.nh7.ecommerce.repository;


import com.nh7.ecommerce.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    boolean existsByUsernameAndPassword(String var1, String var2);
    User findById(int id);
}
