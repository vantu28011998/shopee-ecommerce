package com.nh7.ecommerce.repository;

import com.nh7.ecommerce.entity.Action;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActionRepository extends CrudRepository<Action,Long> {
}
