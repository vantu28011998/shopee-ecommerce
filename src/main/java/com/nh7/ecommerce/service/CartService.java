package com.nh7.ecommerce.service;

import com.nh7.ecommerce.entity.Cart;
import com.nh7.ecommerce.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public List<Cart> getAllCarts() {return (List<Cart>) cartRepository.findAll();}

    public Cart getCartId(int id){return cartRepository.findById(id);}
}
