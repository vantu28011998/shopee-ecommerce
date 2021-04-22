package com.nh7.ecommerce.repository;

import com.nh7.ecommerce.entity.Shop;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ShopRepository extends CrudRepository<Shop, Integer> {
    Shop findById(long id);

    @Modifying
    @Query(value = "DELETE FROM shop WHERE shop.id=:id",nativeQuery = true)
    @Transactional
    void deleteById(long id);
    @Modifying
    @Query(value ="INSERT INTO shop(address,logo,name,phone_number,user_id) VALUES (:address,:logo,:name,:phone_number,:id)",nativeQuery = true)
    @Transactional
    void saveBySuper(@Param("id") long id,@Param("address") String address,@Param("logo")String logo,@Param("name") String name,@Param("phone_number") String phoneNumber);
}
