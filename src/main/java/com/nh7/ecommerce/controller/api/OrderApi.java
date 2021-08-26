package com.nh7.ecommerce.controller.api;

import com.nh7.ecommerce.enums.ItemStatus;
import com.nh7.ecommerce.dto.OrderDto;
import com.nh7.ecommerce.entity.UserOrder;
import com.nh7.ecommerce.service.ItemService;
import com.nh7.ecommerce.service.OrderService;
import com.nh7.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@ControllerAdvice
@RequestMapping(value = "/api/home/orders")
public class OrderApi implements ICrudApi<OrderDto, UserOrder> {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;

    // (Vendor) for set Status
    @PutMapping("/item/{itemId}/status/{itemStatus}")
    public ResponseEntity<Object> updateItem(@PathVariable(name = "itemId") long itemId,
                                             @PathVariable(name = "itemStatus") String itemStatus) {
        if (orderService.updateItemStatus(itemId,itemStatus)) {
            return new ResponseEntity<>("Successed change status", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Failed change status, this item is COMPLETED", HttpStatus.OK);
        }
    }

    // (Vendor) for get All Status
    @GetMapping("/itemStatus")
    public ResponseEntity<Object> getItemStatus() {
        List<String> itemStatus = new ArrayList<>();
        for (ItemStatus status : ItemStatus.values()) {
            itemStatus.add(status.toString());
        }
        return new ResponseEntity<>(itemStatus, HttpStatus.OK);
    }

    // (Vendor) for get All Order Info
    @GetMapping("/vendor/{shopId}/pageable-items")
    public ResponseEntity<Object> getAllOrderInfo(@PathVariable(name = "shopId") long shopId, @RequestParam int page, @RequestParam int limit) {
        Pageable pageable = PageRequest.of(page,limit);
        return new ResponseEntity<>(itemService.getAllItemsByShopId(shopId, pageable), HttpStatus.OK);
    }

    // (Vendor) for get Items Of Shop By Status
    @GetMapping("/vendor/{shopId}/pageable-items/{status}")
    public ResponseEntity<Object> getItemsOfShopByStatus(@PathVariable(name = "shopId") long shopId, @RequestParam int page, @RequestParam int limit, @PathVariable(name="status") String status) {
        Pageable pageable = PageRequest.of(page,limit);
        return new ResponseEntity<>(itemService.getItemsOfShopByStatus(shopId, pageable, status), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<OrderDto>> getAll() {
        return null;
    }

    @Override
    public ResponseEntity<OrderDto> get(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Object> createAll(List<UserOrder> items) {
        return null;
    }

    @Override
    public ResponseEntity<Object> create(UserOrder item) {
        return null;
    }

    @PostMapping("")
    public ResponseEntity<Object> createOrder(@RequestBody OrderDto orderDto) {
        return new ResponseEntity<>(orderService.createNewOrder(orderDto), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Object> update(Long id, UserOrder item) {
        return null;
    }

    @Override
    public ResponseEntity<Object> deleteAll() {
        return null;
    }

    @Override
    public ResponseEntity<Object> delete(Long id) {
        return null;
    }
}
