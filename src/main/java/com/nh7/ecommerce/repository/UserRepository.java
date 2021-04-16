package com.nh7.ecommerce.repository;

import com.nh7.ecommerce.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Account, Integer> {
    boolean existsByUsernameAndPassword(String var1, String var2);
    Account findById(int id);
}
