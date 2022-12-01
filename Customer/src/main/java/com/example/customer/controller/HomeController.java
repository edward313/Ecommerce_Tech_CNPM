package com.example.customer.controller;

import com.example.library.dto.ProductDto;
import com.example.library.model.Category;
import com.example.library.model.Customer;
import com.example.library.model.ShoppingCart;
import com.example.library.serivce.CategoryService;
import com.example.library.serivce.CustomerService;
import com.example.library.serivce.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;
@Controller
public class HomeController {



    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CustomerService customerService;



    @RequestMapping(value = {"/index", "/","/home"  },method = RequestMethod.GET)
    public String index(Principal principal,Model model,HttpSession session){
        List<Category> categories = categoryService.findAll();
        List<ProductDto> productDtos = productService.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("products", productDtos);
        if(principal != null){
            session.setAttribute("username", principal.getName());
            Customer customer = customerService.findByUsername(principal.getName());
            ShoppingCart cart = customer.getShoppingCart();
            model.addAttribute("KT","Login");
            session.setAttribute("totalItems", cart.getTotalItems());
            session.setAttribute("totalPrice", cart.getTotalPrices());
        }

        return "index";
    }
}
