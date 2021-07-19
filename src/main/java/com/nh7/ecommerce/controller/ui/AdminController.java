package com.nh7.ecommerce.controller.ui;

import com.nh7.ecommerce.dto.LoginDto;
import com.nh7.ecommerce.entity.Category;
import com.nh7.ecommerce.entity.SubCategory;
import com.nh7.ecommerce.entity.User;
import com.nh7.ecommerce.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SubCategoryService subCategoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    final int currentMonth = LocalDate.now().getMonthValue();
    final int currentYear = LocalDate.now().getYear();

    @RequestMapping("/home")
    public String showAllCategoies(Model model){
        model.addAttribute("orderList", orderService.getRecentPurchasesInWeek());
        model.addAttribute("totalProductsInMonth", productService.getCountProductInMonth(currentMonth, currentYear));
        model.addAttribute("revenueInMonth", orderService.getRevenueInMonth(currentMonth,currentYear));
        model.addAttribute("countOrdersInMonth", orderService.getCountOrdersInMonth(currentMonth,currentYear));
        model.addAttribute("countVendersInMonth", userService.getCountVendorsInMonth(currentMonth,currentYear));
        model.addAttribute("userLogin", model.asMap().get("userLogin"));
//        model.addAttribute("fullname", model.asMap().get("fullname"));
//        model.addAttribute("avatarUrl", model.asMap().get("avatarUrl"));
        return "admin";
    }

    @RequestMapping("/products")
    public String showListProducts(Model model) {
        model.addAttribute("productList",productService.getAllForAdmin());
        return "products";
    }

    @RequestMapping("/categories")
    public String showCategoryList(Model model) {
        model.addAttribute("categoryList",categoryService.findAll());
        return "categories";
    }

    @RequestMapping(value = "/categories/save", method = RequestMethod.POST)
    public String saveCategory(Category category) {
        categoryService.save(category);
        return "redirect:/admin/categories";
    }

    @RequestMapping(value = "/categories/{id}/delete")
    public String deleteCategory(@PathVariable(name = "id") long id) {
        categoryService.deleteCategory(id);
        return "redirect:/admin/categories";
    }

    @RequestMapping("/subcategories")
    public String showSubCategoryList(Model model) {
        model.addAttribute("subCategoryList",subCategoryService.getAllForAdmin());
        model.addAttribute("categoryList", categoryService.findAll());
        return "subcategories";
    }

    @RequestMapping(value = "/subcategories/save", method = RequestMethod.POST)
    public String addSubCategory(SubCategory subCategory, Model model) {
        if (subCategory.getCategory() != null) {
            subCategoryService.save(subCategory);
        }
        return "redirect:/admin/subcategories";
    }

    @RequestMapping(value = "/subcategories/{id}/delete")
    public String deleteSubCategory(@PathVariable(name = "id") long id) {
        subCategoryService.delete(id);
        return "redirect:/admin/subcategories";
    }

    @RequestMapping("/users")
    public String showUserList(Model model) {
        model.addAttribute("userList", userService.getAllForAdmin());
        return "users";
    }

    @RequestMapping("/vendors")
    public String showVendorList(Model model) {
        model.addAttribute("vendorList", userService.getAllVendors());
        return "vendors";
    }

    @RequestMapping("/orders")
    public String showOrders(Model model) {
        model.addAttribute("orderList", orderService.getAll());
        return "orders";
    }

    @RequestMapping("/order-details/{orderId}")
    public String showOrderDetails(@PathVariable(name = "orderId") long orderId,Model model) {
        model.addAttribute("order", orderService.getById(orderId));
        return "order-details";
    }

    @RequestMapping("")
    public String login(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String handleLogin(LoginDto loginDto, Model model, RedirectAttributes redir) {
        for (User user : userService.getAllForAdmin()) {
            if (user.getUsername().equals(loginDto.getUsername())
                && userService.checkPassword(loginDto.getPassword(), user.getPassword())) {
                redir.addFlashAttribute("userLogin", user);
//                redir.addFlashAttribute("fullname", user.getUserDetails().getFullName());
//                redir.addFlashAttribute("avatarUrl", user.getAvatar());
                return "redirect:/admin/home";
            }
        }
        model.addAttribute("message", "Tài khoản không tồn tại");
        return "login";
    }
}
