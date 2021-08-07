package com.nh7.ecommerce.service;

import com.nh7.ecommerce.dto.ShopDto;
import com.nh7.ecommerce.dto.UserDto;
import com.nh7.ecommerce.entity.Shop;
import com.nh7.ecommerce.entity.SubCategory;
import com.nh7.ecommerce.entity.User;
import com.nh7.ecommerce.repository.ShopRepository;
import com.nh7.ecommerce.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {
    @Autowired
    private ShopRepository shopRepository;
    @Autowired
    private ModelMapperUtil modelMapperUtil;

    public List<ShopDto> findAll(){
        List<Shop> shops= (List<Shop>) shopRepository.findAll();
        return modelMapperUtil.mapList(shops,ShopDto.class);
    }

    public ShopDto findById(long id){
        Shop shop = shopRepository.findById(id);
        return modelMapperUtil.map(shop,ShopDto.class);
    }

    public List<Shop> saveAll(List<Shop> shops){
        return (List<Shop>) shopRepository.saveAll(shops);
    }

    public Shop save(Shop shop){
        return shopRepository.save(shop);
    }

    public void deleteAll(){
        shopRepository.deleteAll();
    }

    public void delete(Long id){
        shopRepository.deleteById(id);
    }

    public List<Shop> getRevenueShop(int currentMonth, int currentYear, int limit) {
        return shopRepository.revenueShop(currentMonth, currentYear, limit);
    }

    public int countShop() {
        return shopRepository.countById();
    }

    public String sumAllRevenue() {
        return shopRepository.sumAllRevenue();
    }
}
