package com.nh7.ecommerce.repository;

import com.nh7.ecommerce.entity.Func;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FunctionRepository extends CrudRepository<Func,Long> {
}
