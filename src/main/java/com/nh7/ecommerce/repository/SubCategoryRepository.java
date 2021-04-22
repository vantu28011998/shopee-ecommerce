package com.nh7.ecommerce.repository;
import com.nh7.ecommerce.entity.SubCategory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface SubCategoryRepository extends CrudRepository<SubCategory,Long> {
    List<SubCategory> findAll();
    SubCategory findById(long id);
}