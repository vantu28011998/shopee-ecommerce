package com.nh7.ecommerce.service;

import com.nh7.ecommerce.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {
    @Autowired
    private ShopRepository shopRepository;
}
