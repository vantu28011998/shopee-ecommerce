package com.nh7.ecommerce.service;

import com.nh7.ecommerce.dto.*;
import com.nh7.ecommerce.enums.ItemStatus;
import com.nh7.ecommerce.entity.BillingInfo;
import com.nh7.ecommerce.entity.Item;
import com.nh7.ecommerce.entity.Product;
import com.nh7.ecommerce.entity.UserOrder;
import com.nh7.ecommerce.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BillingInfoRepository billingInfoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    public UserOrder save(UserOrder userOrder) {
        try {
            return orderRepository.save(userOrder);
        } catch (Exception e) {
            return null;
        }
    }

    public UserOrder getById(long id) {
        try {
            return orderRepository.findById(id);
        } catch (Exception e) {
            return null;
        }
    }

    // (User) for Buy Products
    public OrderDto createNewOrder(OrderDto orderDto) {
        UserOrder newOrder = new UserOrder();
        long userId = orderDto.getUser().getId();
        newOrder = orderRepository.save(newOrder);
        List<Item> itemList = new ArrayList<>();
        for (ItemDto itemDto : orderDto.getItemList()) {
            Item item = new Item();
            long productId = itemDto.getProduct();
            item.setProduct(productRepository.findById(productId));
            item.setItemStatus(ItemStatus.AWAITING_FULFILLMENT.toString());
            item.setOrder(newOrder);
            item.setProductQuantity(itemDto.getQty());
            Product product = productRepository.findById(productId);
            item.setItemPrice(itemDto.getQty() * product.getProductPrice() * (100 - product.getDiscount()) / 100);
            item.setShop(productRepository.findById(productId).getPost().getUser().getShop());
            itemList.add(item);
        }
        newOrder.setItemList(itemList);
        BillingInfo billingInfo = new BillingInfo();
        billingInfo.setFullName(orderDto.getBillingInfo().getFullName());
        billingInfo.setPhoneNumber(orderDto.getBillingInfo().getPhoneNumber());
        String address = orderDto.getBillingInfo().getAddress();
        String ward = orderDto.getBillingInfo().getWard();
        String district = orderDto.getBillingInfo().getDistrict();
        String city = orderDto.getBillingInfo().getCity();
        billingInfo.setShipAddress(address + ", " + ward + ", " + district + ", " + city);
        billingInfo.setOrder(newOrder);
        billingInfo = billingInfoRepository.save(billingInfo);
        newOrder.setBillingInfo(billingInfo);
        newOrder.setUser(userRepository.findById(userId));
        newOrder.setOrderPrice(0D);
        orderRepository.save(newOrder);
        return orderDto;
    }

    // (Vendor) for update orderPrice when set itemStatus = "Success"
    public boolean updateItemStatus(long itemId, String newStatus) {
        Item item = itemRepository.findById(itemId);

        // Update Sold Quantity
        String oldStatus = item.getItemStatus();
        String completed = ItemStatus.COMPLETED.toString();
        if (oldStatus.equals(completed)) return false;
        if (!oldStatus.equals(completed) && newStatus.equals(completed)) {
            Product product = item.getProduct();
            int productQuantity = product.getQuantity();
            product.setQuantity(productQuantity + item.getProductQuantity());
            productRepository.save(product);
        }
        if (oldStatus.equals(completed) && !newStatus.equals(completed)) {
            Product product = item.getProduct();
            int productQuantity = product.getQuantity();
            product.setQuantity(productQuantity - item.getProductQuantity());
            productRepository.save(product);
        }
        // Update Item Status
        item.setItemStatus(newStatus);
        item = itemRepository.save(item);

        // Update Order Price
        UserOrder order = item.getOrder();
        Double orderPrice = 0D;
        for (Item it : order.getItemList()) {
            if (it.getItemStatus().equals(ItemStatus.COMPLETED.toString())) {
                orderPrice += it.getItemPrice();
            }
        }
        order.setOrderPrice(orderPrice);
        orderRepository.save(order);
        return true;
    }

    // (Admin) for get Recent Purchases in Week
    public List<UserOrder> getRecentPurchasesInWeek() {
        return orderRepository.getRecentPurchasesInWeek();
    }

    // (Admin) for get Revenue in Month Of Year
    public Long getRevenueInMonth(int month, int year) {
        return orderRepository.getRevenueInMonth(month, year);
    }

    // (Admin) for get count Order in Month of  Year
    public Integer getCountOrdersInMonth(int month, int year){ return orderRepository.getCountOrdersInMonth(month, year);}

    // (Admin) for get All for Admin
    public List<UserOrder> getAll() { return (List<UserOrder>) orderRepository.findAll();}

    // (User) for get orders by userId
    public List<OrderUDto> getOrdersByUserId(long userId) {
        List<OrderUDto> orderUDtoList = new ArrayList<>();
        List<UserOrder> orderList = userRepository.findById(userId).getUserOrderList();
        if (orderList == null) return orderUDtoList;
        for (UserOrder userOrder : orderList) {
            OrderUDto orderUDto = new OrderUDto();
            List<ItemUDto> itemDtoList = new ArrayList<>();
            for (Item i : userOrder.getItemList()) {
                ItemUDto itemUDto = new ItemUDto();
                itemUDto.setPostTitle(i.getProduct().getPost().getPostTitle());
                itemUDto.setItemPrice(i.getItemPrice());
                itemUDto.setProduct(i.getProduct().getId());
                itemUDto.setProductName(i.getProduct().getProductName());
                itemUDto.setProductThumbnail(i.getProduct().getProductThumbnail());
                itemUDto.setQty(i.getProductQuantity());
                itemUDto.setItemStatus(i.getItemStatus());
                itemDtoList.add(itemUDto);
            }
            orderUDto.setOrderPrice(userOrder.getOrderPrice());
            orderUDto.setItemList(itemDtoList);
            orderUDto.setReceiverName(userOrder.getBillingInfo().getFullName());
            orderUDto.setPhoneNumber(userOrder.getBillingInfo().getPhoneNumber());
            orderUDto.setShipAddress(userOrder.getBillingInfo().getShipAddress());
            orderUDtoList.add(orderUDto);
        }
        return orderUDtoList;
    }

}
