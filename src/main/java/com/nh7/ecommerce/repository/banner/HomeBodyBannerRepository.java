package com.nh7.ecommerce.repository.banner;

import com.nh7.ecommerce.entity.banner.HomeBodyBanner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeBodyBannerRepository extends CrudRepository<HomeBodyBanner,Long> {
    @Override
    List<HomeBodyBanner> findAll();
}
