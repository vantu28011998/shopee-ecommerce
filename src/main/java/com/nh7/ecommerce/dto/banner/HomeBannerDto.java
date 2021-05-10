package com.nh7.ecommerce.dto.banner;

import com.nh7.ecommerce.entity.banner.HomeBodyBanner;
import com.nh7.ecommerce.entity.banner.HomeFooterBanner;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HomeBannerDto {
    private HeaderBannerDto headerBannerDto;
    private BodyBannerDto bodyBannerDto;
    private FooterBannerDto footerBannerDto;
}
