package com.nh7.ecommerce.repository.banner;

import com.nh7.ecommerce.entity.banner.HomeHeaderBanner;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface HomeHeaderBannerRepository extends CrudRepository<HomeHeaderBanner,Long> {
    @Override
    List<HomeHeaderBanner> findAll();
}
