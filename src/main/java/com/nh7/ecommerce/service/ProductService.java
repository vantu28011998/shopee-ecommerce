package com.nh7.ecommerce.service;

import com.nh7.ecommerce.entity.Product;
import com.nh7.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public List<Product> getProductCardByCategoryId(Long id){
        return null;
    }
}
