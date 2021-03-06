package com.nh7.ecommerce.service;

import com.nh7.ecommerce.dto.ProductCardDto;
import com.nh7.ecommerce.dto.VODto;
import com.nh7.ecommerce.dto.pageable.Pagination;
import com.nh7.ecommerce.dto.pageable.ResponsePageable;
import com.nh7.ecommerce.entity.Item;
import com.nh7.ecommerce.entity.Product;
import com.nh7.ecommerce.repository.ItemRepository;
import com.nh7.ecommerce.repository.ProductRepository;
import com.nh7.ecommerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    // (Vendor) for get All Items of User Shop
    public ResponsePageable<VODto> getAllItemsByShopId(long id, Pageable pageable) {
        int offset = (int) pageable.getOffset();
        int limit= pageable.getPageSize();
        System.out.println(offset + " " + limit);
        int page = pageable.getPageNumber();
        List<Item> itemList = itemRepository.getAllByShopId(id, limit, offset);
        System.out.println(itemList.size());
        int totalRow = itemList.size();
        List<VODto> voDtoList = new ArrayList<>();
        for (Item item : itemList) {
            VODto voDto = new VODto();
            voDto.setItemId(item.getId());
            voDto.setCustomerName("null");
            voDto.setProductName(item.getProduct().getProductName());
            voDto.setPostTitle(item.getProduct().getPost().getPostTitle());
            voDto.setProductQuantity(item.getProductQuantity());
            voDto.setItemStatus(item.getItemStatus());
            voDto.setCreatedAt(item.getCreatedAt());
            voDtoList.add(voDto);
        }
        ResponsePageable<VODto> responsePageable = new ResponsePageable<>();
        responsePageable.setItems(voDtoList);
        Pagination pagination = new Pagination();
        pagination.setLimit(limit);
        pagination.setPage(page);
        pagination.setTotalRow(totalRow);
        responsePageable.setPagination(pagination);
        return responsePageable;
    }

    // (Vendor) for get All Items of User Shop By Status
    public ResponsePageable<VODto> getItemsOfShopByStatus(long id, Pageable pageable, String status) {
        int offset = (int) pageable.getOffset();
        int limit= pageable.getPageSize();
        System.out.println(offset + " " + limit);
        int page = pageable.getPageNumber();
        List<Item> itemList = itemRepository.getItemOfShopByStatus(id, limit, offset,status);
        System.out.println(itemList.size());
        int totalRow = itemList.size();
        List<VODto> voDtoList = new ArrayList<>();
        for (Item item : itemList) {
            VODto voDto = new VODto();
            voDto.setItemId(item.getId());
            voDto.setCustomerName("null");
            voDto.setProductName(item.getProduct().getProductName());
            voDto.setPostTitle(item.getProduct().getPost().getPostTitle());
            voDto.setProductQuantity(item.getProductQuantity());
            voDto.setItemStatus(item.getItemStatus());
            voDto.setCreatedAt(item.getCreatedAt());
            voDtoList.add(voDto);
        }
        ResponsePageable<VODto> responsePageable = new ResponsePageable<>();
        responsePageable.setItems(voDtoList);
        Pagination pagination = new Pagination();
        pagination.setLimit(limit);
        pagination.setPage(page);
        pagination.setTotalRow(totalRow);
        responsePageable.setPagination(pagination);
        return responsePageable;
    }

}
