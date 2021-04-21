package com.nh7.ecommerce.service;

import com.nh7.ecommerce.dto.ProductCardDto;
import com.nh7.ecommerce.entity.Post;
import com.nh7.ecommerce.entity.Product;
import com.nh7.ecommerce.model.ProductCardModel;
import com.nh7.ecommerce.repository.*;
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
    public void creatProductCard(){
        Product pr = new Product();
        Post po = new Post();
        pr.setCategory(categoryRepository.findById(1));
        pr.setProductName("Áo dài");
        pr.setProductPrice(5.7);
        pr.setProductThumbnail("abcjd");
        pr.setQuantity(1900);
        po.setPostTitle("\uD83C\uDF53\uD83C\uDF53Áo Croptop dây đắp chéo siêu sexy\uD83C\uDF35Kèm hình thật [hình feedback]\uD83C\uDF35 vải mềm, vải đẹp");
        po.setUser(userRepository.findById(1));
        po.setPostDecription("Áo Croptop dây đắp chéo hot hit, vải đẹp giá rẻ\n" +
                "\n" +
                "\uD83C\uDF4CChất thun gân mềm mịn\n" +
                "\uD83C\uDF4CFreesize <55kg\n" +
                "\uD83C\uDF4Cđiều chỉnh dây theo sở thích \n" +
                "\uD83C\uDF4Crất dễ phối với quần Short Jean hoặc chân váy\n");
        postService.create(po);
        pr.setPost(po);
        productRepository.save(pr);
    }
    // END
}
