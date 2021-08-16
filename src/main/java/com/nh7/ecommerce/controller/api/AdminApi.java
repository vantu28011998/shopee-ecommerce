package com.nh7.ecommerce.controller.api;

import com.nh7.ecommerce.dto.admin.RevenueShopDto;
import com.nh7.ecommerce.dto.admin.UserDto;
import com.nh7.ecommerce.entity.*;
import com.nh7.ecommerce.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@ControllerAdvice
@RequestMapping(value = "/api/admin")
public class AdminApi {
    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ShopService shopService;

    final int currentMonth = LocalDate.now().getMonthValue();
    final int currentYear = LocalDate.now().getYear();

//    @GetMapping("/categories/{id}/getById")
//    public ResponseEntity<Object> getCategoryById(@PathVariable(name = "id") long id){
//        return new ResponseEntity<>(categoryService.getById(id), HttpStatus.OK);
//    }

//    @GetMapping("/subcategories/{id}/getById")
//    public ResponseEntity<Object> getSubCategoryById(@PathVariable(value = "id") long id){
//        SubCategory subCategory = subCategoryService.getById(id);
//        SubCategoryDto subCategoryDto = new SubCategoryDto();
//        subCategoryDto.setId(id);
//        subCategoryDto.setSubCategoryName(subCategory.getSubCategoryName());
//        subCategoryDto.setCategoryId(subCategory.getCategory().getId());
//        subCategoryDto.setCategoryName(subCategory.getCategory().getCategoryName());
//        return new ResponseEntity<>(subCategoryDto, HttpStatus.OK);
//    }

    // api get admin info
    @GetMapping("/personal-info")
    public ResponseEntity<Object> getPersonalAdminInfo(@RequestParam(name = "id") long id) {
        User user = userService.getUserById(id);
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        if (user.getUserDetails()!=null) {
            userDto.setFullName(user.getUserDetails().getFullName());
            userDto.setPhoneNumber(user.getUserDetails().getPhoneNumber());
            userDto.setAddress(user.getUserDetails().getAddress());
            userDto.setDayOfBirth(user.getUserDetails().getDayOfBird());
        }
        userDto.setAvatarUrl(user.getAvatar());
        userDto.setEmail(user.getEmailAddress());
        userDto.setStatus(user.getEnable());
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    // api change personal admin info
    @PostMapping("/personal-info")
    public ResponseEntity<Object> changePersonalAdminInfo(@RequestBody UserDto userDto) {
        User user = userService.getUserById(userDto.getId());
        user.setAvatar(userDto.getAvatarUrl());
        user.getUserDetails().setFullName(userDto.getFullName());
        user.getUserDetails().setPhoneNumber(userDto.getPhoneNumber());
        user.getUserDetails().setAddress(userDto.getAddress());
        user.getUserDetails().setDayOfBird(userDto.getDayOfBirth());
        userService.saveNoBcrypt(user);
        return new ResponseEntity<>("Changed personal information", HttpStatus.OK);
    }

    // api get all account for [Manage Account]
    @GetMapping("/accounts")
    public ResponseEntity<Object> getAllUsers() {
        List<User> users = userService.getAllForAdmin();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setEmail(user.getEmailAddress());
            if (user.getUserDetails()!=null) {
                userDto.setAddress(user.getUserDetails().getAddress());
                userDto.setFullName(user.getUserDetails().getFullName());
                userDto.setPhoneNumber(user.getUserDetails().getPhoneNumber());
                userDto.setDayOfBirth(user.getUserDetails().getDayOfBird());
            } else  {
                userDto.setAddress("");
                userDto.setFullName("");
                userDto.setPhoneNumber("");
                userDto.setDayOfBirth("");
            }
            if (user.getShop()!=null) {
                userDto.setShopName(user.getShop().getName());
            } else userDto.setShopName("");
            userDto.setAvatarUrl(user.getAvatar());
            userDto.setRoles(user.getRoles());
            userDto.setStatus(user.getEnable());
            userDtos.add(userDto);
        }
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }
    // api lock user by id
    @PostMapping("/accounts/{id}/lock_user")
    public ResponseEntity<Object> lockUserById(@PathVariable(name = "id") long id) {
        User user = userService.getUserById(id);
        userService.lockUserById(id);
        return new ResponseEntity<>("Đã khóa tài khoản " + user.getUsername(),HttpStatus.OK);
    }
    // api unlock user by id
    @PostMapping("/accounts/{id}/unlock_user")
    public ResponseEntity<Object> unlockUserById(@PathVariable(name = "id") long id) {
        User user = userService.getUserById(id);
        userService.unlockUserById(id);
        return new ResponseEntity<>("Đã mở khóa tài khoản " + user.getUsername(),HttpStatus.OK);
    }
    // api set roles by id
    @PostMapping("/accounts/{id}")
    public ResponseEntity<Object> setRoles(List<String> roleList) {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }
    // api get all products
    @GetMapping("/products")
    public ResponseEntity<Object> getAllProducts() {
        return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
    }
    // api change admin info
    @PostMapping("/change-info/{id}")
    public ResponseEntity<Object> changeAdminInfo(@RequestBody UserDto userDto) {
        User user = userService.getUserById(userDto.getId());
        for (Role role : user.getRoles()) {
            if (role.getRoleName().equals("ADMIN")) {
                user.setEmailAddress(userDto.getEmail());
                user.getUserDetails().setFullName(userDto.getFullName());
                user.getUserDetails().setAddress(userDto.getAddress());
                user.getUserDetails().setPhoneNumber(userDto.getPhoneNumber());
                user.setAvatar(userDto.getAvatarUrl());
                userService.saveNoBcrypt(user);
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Unauthorized",  HttpStatus.UNAUTHORIZED);
    }
    // api get 10 Product Best Sell In Current Month
    @GetMapping("/products/best-sell/month")
    public ResponseEntity<Object> getProductsBestSell(@RequestParam(name = "limit") int limit) {
        return new ResponseEntity<>(productService.getProductBestSell(currentMonth, currentYear, limit), HttpStatus.OK);
    }
    // api get 5 Revenue Shop In Current Month
    @GetMapping("/revenue-top/shops/month")
    public ResponseEntity<Object> getRevenueShop(@RequestParam(name = "limit") int limit) {
        List<Shop> shops = shopService.getRevenueShop(currentMonth, currentYear, limit);
        System.out.println(shops);
        List<RevenueShopDto> revenueShopDtos = new ArrayList<>();
        for (Shop shop : shops) {
            RevenueShopDto r = new RevenueShopDto();
            r.setShop_id(shop.getId());
            r.setLogo(shop.getLogo());
            r.setShopName(shop.getName());
            if (shop.getUser().getUserDetails()!=null) {
                r.setOwnerName(shop.getUser().getUserDetails().getFullName());
            } else {
                r.setOwnerName(shop.getUser().getUsername());
            }
            int revenue = 0;
            int quantityProductSell = 0;
            for (Item i : shop.getItemList()) {
                if (i.getItemStatus().equals("COMPLETED")
                    && i.getCreatedAt().getMonthValue() == currentMonth
                    && i.getCreatedAt().getYear() == currentYear) {
                    revenue +=i.getItemPrice();
                    quantityProductSell += i.getProductQuantity();
                }
            }
            r.setRevenue(revenue);
            r.setQuantitySell(quantityProductSell);
            revenueShopDtos.add(r);
        }
        return new ResponseEntity<>(revenueShopDtos, HttpStatus.OK);
    }
    // api get overview for dashboard
    @GetMapping("/overview")
    public ResponseEntity<Object> getOverview() {
        Map<String, String> overview = new HashMap<>();
        overview.put("countAccount", String.valueOf(userService.countAccount()));
        overview.put("countShop", String.valueOf(shopService.countShop()));
        overview.put("countProduct", String.valueOf(productService.countProduct()));
        overview.put("sumAllRevenue", shopService.sumAllRevenue());
        return new ResponseEntity<>(overview, HttpStatus.OK);
    }
}
