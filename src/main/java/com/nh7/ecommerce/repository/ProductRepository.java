package com.nh7.ecommerce.repository;

import com.nh7.ecommerce.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    Product findById(long id);
    @Query(value = "SELECT pr.id FROM product pr",nativeQuery = true)
    List<Long> findAllId();
    @Query(value = "SELECT pr.* \n" +
            "FROM product pr\n" +
            "JOIN SUB_CATEGORY sub_ca ON pr.subcategory_id=sub_ca.id\n" +
            "JOIN category ca ON ca.id=sub_ca.category_id\n" +
            "WHERE ca.id=:id", nativeQuery = true)
    List<Product> findByCategoryId(@Param("id") long id);
    Page<Product> findAll(Pageable pageable);

    @Query(value = "SELECT products.*\n" +
            "FROM(\n" +
            "\tSELECT pr.*\n" +
            "    FROM product pr JOIN sub_category sub ON sub.id = pr.subcategory_id\n" +
            "    WHERE pr.subcategory_id=:id\n" +
            ") as products\n" +
            "LIMIT :limit OFFSET :offset ;",nativeQuery = true)
    List<Product> findProductsBySubCategoryAndId(@Param("id") long id,@Param("limit") int limit,@Param("offset") int offset);

    @Query(value = "SELECT products.*\n" +
            "FROM(\n" +
            "\tSELECT pr.*\n" +
            "    FROM product pr JOIN sub_category sub ON sub.id = pr.subcategory_id\n" +
            " JOIN category ca ON ca.id=sub.category_id"+
            "    WHERE ca.id=:id\n" +
            ") as products\n" +
            "LIMIT :limit OFFSET :offset ;",nativeQuery = true)
    List<Product> findProductsByCategoryAndId(@Param("id") long id,@Param("limit") int limit,@Param("offset") int offset);
    @Modifying
    @Query(value = "DELETE FROM product WHERE product.id=:id",nativeQuery = true)
    @Transactional
    void deleteById(long id);

    @Query(value = "select count(pr.id) from product pr\n" +
            "join post po on pr.id = po.product_id\n" +
            "join u on u.id = po.user_id\n" +
            "join shop sh on sh.user_id = u.id\n" +
            "where sh.id = :id", nativeQuery = true)
    Integer countProductByPostUserShop(@Param("id") long id);

    @Query(value = "SELECT count(*) \n" +
            "FROM product pr\n" +
            "JOIN SUB_CATEGORY sub_ca ON pr.subcategory_id=sub_ca.id\n" +
            "JOIN category ca ON ca.id=sub_ca.category_id\n" +
            "WHERE ca.id=:id", nativeQuery = true)
    int countProductsByCategoryId(@Param("id") long id);

    @Query(value = "SELECT count(*) \n" +
            "FROM product pr\n" +
            "JOIN SUB_CATEGORY sub_ca ON pr.subcategory_id=sub_ca.id\n" +
            "WHERE sub_ca.id=:id", nativeQuery = true)
    int countProductsBySubCategoryId(@Param("id") long id);

    // (Admin) for get Products has been in month of year
    @Query(value = "select count(id) from product where month(created_at) = :month year(created_at) = :year", nativeQuery = true)
    Integer countProductCreatedAtMonth(@Param("month") int month, @Param("year") int year);
}
