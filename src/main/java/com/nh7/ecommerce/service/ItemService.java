package com.nh7.ecommerce.service;

import com.nh7.ecommerce.entity.Item;
import com.nh7.ecommerce.entity.Product;
import com.nh7.ecommerce.repository.ItemRepository;
import com.nh7.ecommerce.repository.ProductRepository;
import com.nh7.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;


    // for delete item by id
    public void deleteItemById(long id) {
        itemRepository.deleteById(id);
    }

    public Item save(Item item) {
        try {
            return itemRepository.save(item);
        } catch (Exception e){
            return null;
        }
    }

    public List<Item> saveAll(List<Item> items) {
        return (List<Item>) itemRepository.saveAll(items);
    }


}
