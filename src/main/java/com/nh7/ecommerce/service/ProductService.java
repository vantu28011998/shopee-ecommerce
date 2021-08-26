package com.nh7.ecommerce.service;

import com.nh7.ecommerce.dto.ProductCardDto;
import com.nh7.ecommerce.dto.admin.ProductBestSell;
import com.nh7.ecommerce.dto.pageable.Pagination;
import com.nh7.ecommerce.dto.pageable.ResponsePageable;
import com.nh7.ecommerce.entity.Item;
import com.nh7.ecommerce.entity.Product;
import com.nh7.ecommerce.repository.*;
import com.nh7.ecommerce.util.ModelMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RatingRepository ratingRepository;
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
//            productCardDto.setAddress(pr.getPost().getUser().getShop().getAddress());
            productCardDto.setPostTitle(pr.getPost().getPostTitle());
            productCardDto.setPostId(pr.getPost().getId());
            productCardDto.setSoldQuantity(pr.getPost().getSoldQuantity());
            productCardDto.setDiscount(pr.getDiscount());
            productCardDto.setSubcategoryId(pr.getSubCategory().getId());
            productCardDto.setAvgEvalute(ratingRepository.findAvgRating(pr.getId()));
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

    public List<ProductCardDto> getPageableProducts(Pageable pageable){
        Sort sort=Sort.by("product_name").ascending();
        List<Product> products = productRepository.findAll(pageable).getContent();
        return convert(products);
    }
    public ResponsePageable<ProductCardDto> getPageableProductsByCategoryId(long id,Pageable pageable){
        Sort sort = Sort.by("product_name").ascending();
        int offset = (int) pageable.getOffset();
        int limit= pageable.getPageSize();
        int page = pageable.getPageNumber();
        int totalRow = productRepository.countProductsByCategoryId(id);
        List<Product> products = productRepository.findProductsByCategoryAndId(id,limit,offset);
        List<ProductCardDto> productCardDtos = convert(products);
        ResponsePageable<ProductCardDto> responsePageable = new ResponsePageable<>();
        responsePageable.setItems(productCardDtos);
        Pagination pagination = new Pagination();
        pagination.setLimit(limit);
        pagination.setPage(page);
        pagination.setTotalRow(totalRow);
        responsePageable.setPagination(pagination);
        return responsePageable;
    }
    public List<ProductCardDto> getProductsByIds(Long[] ids){
        List<Product> products = new ArrayList<>();

        for(int i=0;i<ids.length;i++){
            System.out.println("IDS" + ids[i]);
            products.add(productRepository.findById(ids[i]).get());
        }
        for(Product product : products){
            System.out.println(product.getProductName());
        }
        System.out.println("PRODUCT SHOW");
        System.out.println(products.size());;
        return convert(products);
    }
    public ResponsePageable<ProductCardDto> getPageableProductsBySubcategoryId(long id,Pageable pageable){
        Sort sort = Sort.by("product_name").ascending();
        int offset = (int) pageable.getOffset();
        int limit= pageable.getPageSize();
        int page = pageable.getPageNumber();
        int totalRow = productRepository.countProductsBySubCategoryId(id);
        List<Product> products = productRepository.findProductsBySubCategoryAndId(id,limit,offset);
        System.out.println("DANG TEST CHO NAY");
        List<ProductCardDto> productCardDtos = convert(products);
        ResponsePageable<ProductCardDto> responsePageable = new ResponsePageable<>();
        responsePageable.setItems(productCardDtos);
        Pagination pagination = new Pagination();
        pagination.setLimit(limit);
        pagination.setPage(page);
        pagination.setTotalRow(totalRow);
        responsePageable.setPagination(pagination);
        return responsePageable;
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

    // (Admin) for get All for Admin
    public List<Product> getAllForAdmin() {
        return (List<Product>) productRepository.findAll();
    }

    // (Admin) for get Products best sell
    public List<ProductBestSell> getProductBestSell(int currentMonth, int currentYear, int limit) {
        List<ProductBestSell> productBS = new ArrayList<>();
        List<Product> productsBestSell = productRepository.getProductsBestSell(currentMonth, currentYear, limit);
        for (Product pr : productsBestSell) {
            ProductBestSell pbs = new ProductBestSell();
            pbs.setCreatedAt(pr.getCreatedAt());
            pbs.setDiscount(pr.getDiscount());
            pbs.setProductName(pr.getProductName());
            pbs.setProductPrice(pr.getProductPrice());
            pbs.setProductThumbnail(pr.getProductThumbnail());
            pbs.setSubcategoryName(pr.getSubCategory().getSubCategoryName());
            pbs.setCategoryName(pr.getSubCategory().getCategory().getCategoryName());
            int soldquantity = 0;
            for (Item i : pr.getItemList()) {
                soldquantity += i.getProductQuantity();
            }
            pbs.setSoldQuantity(soldquantity);
            productBS.add(pbs);
        }
        return productBS;
    }
    //(Admin) for get count all products in system
    public int countProduct() {
        return productRepository.countAll();
    }

    //(User) for search Product at homepage
    public ResponsePageable<ProductCardDto> searchProductAtHomePage(String searchArg, Pageable pageable) {
        Sort sort = Sort.by("product_name").ascending();
        int offset = (int) pageable.getOffset();
        int limit= pageable.getPageSize();
        int page = pageable.getPageNumber();
        List<Product> products = productRepository.searchProductAtHomePage(searchArg,limit,offset);
        int totalRow = products.size();
        List<ProductCardDto> productCardDtos = convert(products);
        ResponsePageable<ProductCardDto> responsePageable = new ResponsePageable<>();
        responsePageable.setItems(productCardDtos);
        Pagination pagination = new Pagination();
        pagination.setLimit(limit);
        pagination.setPage(page);
        pagination.setTotalRow(totalRow);
        responsePageable.setPagination(pagination);
        return responsePageable;
    }
}
