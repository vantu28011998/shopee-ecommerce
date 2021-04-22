package com.nh7.ecommerce.service;

import com.nh7.ecommerce.dto.ProductCardDto;
import com.nh7.ecommerce.entity.Product;
import com.nh7.ecommerce.repository.*;
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
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostService postService;
    //
    @Autowired
    private ModelMapperUtil modelMapperUtil;

    private List<ProductCardDto> convert(List<Product> products){
        List<ProductCardDto> productCardDtos = new ArrayList<>();
        for (Product pr : products){
            ProductCardDto productCardDto = new ProductCardDto();
            productCardDto.setId(pr.getId());
            productCardDto.setProductPrice(pr.getProductPrice());
            productCardDto.setProductThumbnail(pr.getProductThumbnail());
            productCardDto.setAddress(pr.getPost().getUser().getShop().getAddress());
            productCardDto.setPostTitle(pr.getPost().getPostTitle());
            productCardDto.setSoldQuantity(pr.getPost().getSoldQuantity());
            productCardDto.setDiscount(pr.getDiscount());
            productCardDto.setSubCategoryId(pr.getSubCategory().getId());
            productCardDtos.add(productCardDto);
        }
        return productCardDtos;
    }
    public List<ProductCardDto> getProductCardByCategoryId(Long id){
        List<Product> products = productRepository.findByCategoryId(id);
        return convert(products);
    }

    public List<ProductCardDto> getAll() {
        List<Product> products = (List<Product>) productRepository.findAll();
        return convert(products);
    }

    public void deleteAll(){
        productRepository.deleteAll();
    }

    public void delete(Long id){
        productRepository.deleteById(id);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public List<Product> saveAll(List<Product> products){
        return (List<Product>) productRepository.saveAll(products);
    }
}
