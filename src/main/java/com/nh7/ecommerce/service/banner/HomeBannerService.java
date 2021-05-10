package com.nh7.ecommerce.service.banner;

import com.nh7.ecommerce.dto.banner.BodyBannerDto;
import com.nh7.ecommerce.dto.banner.FooterBannerDto;
import com.nh7.ecommerce.dto.banner.HeaderBannerDto;
import com.nh7.ecommerce.dto.banner.HomeBannerDto;
import com.nh7.ecommerce.entity.banner.HomeBodyBanner;
import com.nh7.ecommerce.entity.banner.HomeFooterBanner;
import com.nh7.ecommerce.entity.banner.HomeHeaderBanner;
import com.nh7.ecommerce.entity.banner.HomeHeaderSlider;
import com.nh7.ecommerce.repository.banner.HomeBodyBannerRepository;
import com.nh7.ecommerce.repository.banner.HomeFooterBannerRepository;
import com.nh7.ecommerce.repository.banner.HomeHeaderBannerRepository;
import com.nh7.ecommerce.repository.banner.HomeHeaderSliderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class HomeBannerService {
    @Autowired
    private HomeHeaderSliderRepository homeHeaderSliderRepository;
    @Autowired
    private HomeHeaderBannerRepository homeHeaderBannerRepository;
    @Autowired
    private HomeBodyBannerRepository homeBodyBannerRepository;
    @Autowired
    private HomeFooterBannerRepository homeFooterBannerRepository;

    public HomeBannerDto findAll(){
       List<HomeHeaderSlider> homeHeaderSliders=homeHeaderSliderRepository.findAll();
       List<HomeHeaderBanner> homeHeaderBanners=homeHeaderBannerRepository.findAll();
       List<HomeBodyBanner> homeBodyBanners=homeBodyBannerRepository.findAll();
       List<HomeFooterBanner> homeFooterBanners=homeFooterBannerRepository.findAll();
       HomeBannerDto homeBannerDto = new HomeBannerDto();
       HeaderBannerDto headerBannerDto = new HeaderBannerDto();
            headerBannerDto.setHomeHeaderSliders(homeHeaderSliders);
            headerBannerDto.setHomeHeaderBanners(homeHeaderBanners);
       homeBannerDto.setHeaderBannerDto(headerBannerDto);
       BodyBannerDto bodyBannerDto = new BodyBannerDto();
            bodyBannerDto.setHomeBodyBanners(homeBodyBanners);
       homeBannerDto.setBodyBannerDto(bodyBannerDto);
       FooterBannerDto footerBannerDto = new FooterBannerDto();
            footerBannerDto.setHomeFooterBanners(homeFooterBanners);
       homeBannerDto.setFooterBannerDto(footerBannerDto);
       return homeBannerDto;
    }
    public HomeBannerDto create(HomeBannerDto homeBannerDto){
        HeaderBannerDto headerBannerDto = homeBannerDto.getHeaderBannerDto();
//        headerBannerDto.getHomeHeaderSliders()
//        homeHeaderSliderRepository.save()

        return null;
    }
}
