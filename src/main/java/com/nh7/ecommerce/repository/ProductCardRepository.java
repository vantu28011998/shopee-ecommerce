package com.nh7.ecommerce.repository;
import com.nh7.ecommerce.model.ProductCardModel;
import java.util.List;
public interface ProductCardRepository{
    List<ProductCardModel> findProductCardByCategoryId(long id);
}
