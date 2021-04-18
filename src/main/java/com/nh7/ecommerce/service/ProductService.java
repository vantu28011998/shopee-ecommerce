package com.nh7.ecommerce.service;

import com.nh7.ecommerce.dto.ProductCardDto;
import com.nh7.ecommerce.model.ProductCardModel;
import com.nh7.ecommerce.repository.ProductCardRepository;
import com.nh7.ecommerce.repository.ProductCardRepositoryImp;
import com.nh7.ecommerce.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductCardRepositoryImp productCardRepository;
    @Autowired
    private ModelMapperUtil modelMapperUtil;
    public List<ProductCardModel> getProductCardByCategoryId(Long id){
        List<ProductCardModel> productCardModels = productCardRepository.findProductCardByCategoryId(id);
        return productCardModels;
    }
}
