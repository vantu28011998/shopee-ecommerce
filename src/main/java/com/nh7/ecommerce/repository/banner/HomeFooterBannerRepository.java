package com.nh7.ecommerce.repository.banner;

import com.nh7.ecommerce.entity.banner.HomeFooterBanner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeFooterBannerRepository extends CrudRepository<HomeFooterBanner,Long> {
    @Override
    List<HomeFooterBanner> findAll();
}
