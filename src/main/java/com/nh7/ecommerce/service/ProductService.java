package com.nh7.ecommerce.service;

import com.nh7.ecommerce.dto.ProductCardDto;
import com.nh7.ecommerce.entity.Product;
import com.nh7.ecommerce.model.ProductCardModel;
import com.nh7.ecommerce.repository.ProductCardRepository;
import com.nh7.ecommerce.repository.ProductCardRepositoryImp;
import com.nh7.ecommerce.repository.ProductRepository;
import com.nh7.ecommerce.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductCardRepositoryImp productCardRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ModelMapperUtil modelMapperUtil;
    public List<ProductCardModel> getProductCardByCategoryId(Long id){
        List<ProductCardModel> productCardModels = productCardRepository.findProductCardByCategoryId(id);
        return productCardModels;
    }
    // CODE BY HUY
    public List<ProductCardModel> getProductCardByCateId(Long id){
        List<ProductCardModel> productCardModelList = new ArrayList<>();
        List<Product> productList = productRepository.findByCategoryId(id);
        for (Product pr : productList){
            ProductCardModel productCardModel = new ProductCardModel();
            productCardModel.setId(pr.getId());
            productCardModel.setProductPrice(pr.getProductPrice());
            productCardModel.setProductThumbnail(pr.getProductThumbnail());
            productCardModel.setAddress(pr.getPost().getUser().getShop().getAddress());
            productCardModel.setPostTitle(pr.getPost().getPostTitle());
            productCardModel.setSoldQuantity(pr.getPost().getSoldQuantity());
            productCardModelList.add(productCardModel);
        }
        return productCardModelList;
    }
    // END
}
