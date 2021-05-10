package com.nh7.ecommerce.repository.banner;

import com.nh7.ecommerce.entity.banner.HomeHeaderSlider;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface HomeHeaderSliderRepository extends CrudRepository<HomeHeaderSlider,Long> {
    @Override
    List<HomeHeaderSlider> findAll();
}
