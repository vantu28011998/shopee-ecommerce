package com.nh7.ecommerce.repository;
import com.nh7.ecommerce.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory,Long> {
    List<SubCategory> findAll();
    SubCategory findById(long id);
    @Query(value = "select sub_ca.* from sub_category sub_ca join category ca on  sub_ca.category_id=ca.id where ca.id=:id",nativeQuery = true)
    List<SubCategory> findAllByCategoryId(@Param("id") long id);
}