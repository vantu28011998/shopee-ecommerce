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
    private ProductRepository productRepository;

    // CODE BY HUY
    //MODIFIED BY VAN TU AT 4:44 P.M 21/04/2021
    //NOTE: DATABASE HAVE A NEW SUBCATEGORY TABLE
    public List<ProductCardDto> getProductCardByCategoryId(Long id){
        List<ProductCardDto> productCardDtos = new ArrayList<>();
        List<Product> products = productRepository.findByCategoryId(id);
        for (Product pr : products){
            ProductCardDto productCardDto = new ProductCardDto();
            productCardDto.setId(pr.getId());
            productCardDto.setProductPrice(pr.getProductPrice());
            productCardDto.setProductThumbnail(pr.getProductThumbnail());
            productCardDto.setAddress(pr.getPost().getUser().getShop().getAddress());
            productCardDto.setPostTitle(pr.getPost().getPostTitle());
            productCardDto.setSoldQuantity(pr.getPost().getSoldQuantity());
            productCardDto.setDiscount(pr.getDiscount());
            productCardDtos.add(productCardDto);
        }
        return productCardDtos;
    }
    // END
    public void deleteAll(){
        productRepository.deleteAll();
    }
    public void delete(Long id){
        productRepository.deleteById(id);
    }
    public void saveAllBySuper(Long id,List<Product> items){
        for(Product item:items){
            saveBySuper(id,item);
        }
    }
    public void saveBySuper(Long id,Product item){
        productRepository.saveBySuper(id,item.getAvgEvalute(),item.getDiscount(),item.getProductName(),item.getProductPrice(),item.getProductThumbnail(),item.getQuantity());
    }
    public Product save(Product product){
        return productRepository.save(product);
    }
    public List<Product> saveAll(List<Product> products){
        return (List<Product>) productRepository.saveAll(products);
    }
}
