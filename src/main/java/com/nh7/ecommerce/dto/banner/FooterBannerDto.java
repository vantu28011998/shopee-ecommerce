package com.nh7.ecommerce.dto.banner;
import com.nh7.ecommerce.entity.banner.HomeFooterBanner;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class FooterBannerDto {
    List<HomeFooterBanner> homeFooterBanners;
}
