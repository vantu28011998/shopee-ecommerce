package com.nh7.ecommerce.repository;

import com.nh7.ecommerce.entity.BillingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillingInfoRepository extends JpaRepository<BillingInfo, Long> {
    BillingInfo findById(long id);
}
