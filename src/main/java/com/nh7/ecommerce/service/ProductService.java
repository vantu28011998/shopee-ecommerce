package com.nh7.ecommerce.service;

import com.nh7.ecommerce.dto.ProductCardDto;
import com.nh7.ecommerce.entity.Product;
import com.nh7.ecommerce.model.ProductCardModel;
import com.nh7.ecommerce.repository.ProductRepository;
import com.nh7.ecommerce.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapperUtil modelMapperUtil;
    public List<ProductCardDto> getProductCardByCategoryId(Long id){
        List<ProductCardModel> productCardModels = productRepository.findProductCardByCategoryId(id);
        for(ProductCardModel productCard : productCardModels){
            productCard.getPostTitle();
        }
        return modelMapperUtil.mapList(productCardModels,ProductCardDto.class);
    }
}
