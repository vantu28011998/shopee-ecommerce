package com.nh7.ecommerce.repository;

import com.nh7.ecommerce.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT count(*) FROM product",nativeQuery = true)
    Integer countAll();
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
    @Query(value = "select count(id) from product where date_part('month',created_at) = :month and date_part('year',created_at) = :year", nativeQuery = true)
    int countProductCreatedAtMonth(@Param("month") int month, @Param("year") int year);

    // (Admin) for get Product Best Sell
    @Query(value = "select pr.* from product pr\n" +
            "join item on pr.id = item.product_id\n" +
            "where item_status = 'COMPLETED' and date_part('month',item.created_at) = :currentMonth and date_part('year',item.created_at) = :currentYear\n " +
            "group by pr.id\n" +
            "order by sum(item.product_quantity) desc\n" +
            "limit :limit", nativeQuery = true)
    List<Product> getProductsBestSell(@Param("currentMonth") int currentMonth, @Param("currentYear") int currentYear, @Param("limit") int limit);

    @Query(value = "select sum(item.product_quantity) from product pr\n" +
            "join item on pr.id = item.product_id\n" +
            "where item_status = 'COMPLETED' and date_part('month',item.created_at) = :currentMonth and date_part('year',item.created_at) = :currentYear\n" +
            "group by pr.id\n" +
            "order by sum(item.product_quantity) desc\n" +
            "limit 5", nativeQuery = true)
    List<Integer> getSumQuantityOfPrBestSell(@Param("currentMonth") int currentMonth, @Param("currentYear") int currentYear);

    // (User) for search product at home
    @Query(value = "select pr.* from product pr \n" +
            "join post po on pr.id = po.product_id\n" +
            "join sub_category scg on pr.subcategory_id = scg.id\n" +
            "join category cg on scg.category_id = cg.id\n" +
            "where pr.product_name like %:searchArg%\n" +
            "or po.post_title like %:searchArg%\n" +
            "or scg.sub_category_name like %:searchArg%\n" +
            "or cg.category_name like %:searchArg% \n" +
            "limit :limit offset :offset", nativeQuery = true)
    List<Product> searchProductAtHomePage(@Param("searchArg") String searchArg, @Param("limit") int limit, @Param("offset") int offset);

}
