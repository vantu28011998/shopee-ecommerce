package com.nh7.ecommerce.dto.banner;
import com.nh7.ecommerce.entity.banner.HomeHeaderBanner;
import com.nh7.ecommerce.entity.banner.HomeHeaderSlider;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
@Getter
@Setter
public class HeaderBannerDto {
    private List<HomeHeaderSlider> homeHeaderSliders;
    private List<HomeHeaderBanner> homeHeaderBanners;
}
