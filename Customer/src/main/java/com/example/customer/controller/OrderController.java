package com.example.customer.controller;

import com.example.library.model.Customer;
import com.example.library.model.ShoppingCart;
import com.example.library.serivce.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class OrderController {

    @Autowired
    private CustomerService customerService;


    @GetMapping("/check-out")
    public String checkout(Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);

//        System.out.println(customer.getPhoneNumber().trim().isEmpty() );
//        System.out.println(customer.getAddress().trim().isEmpty());
//        System.out.println(customer.getCity().trim().isEmpty());
//        System.out.println(customer.getCountry().trim().isEmpty());
        if(customer.getPhoneNumber() == null){

            model.addAttribute("customer", customer);
            model.addAttribute("error", "You must fill the information after checkout!");
            return "account";
        }

//        if(username != null){
//
//            model.addAttribute("customer", customer);
//            model.addAttribute("error", "You must fill the information after checkout!");
//            return "account";
//        }else{
            model.addAttribute("customer", customer);
            ShoppingCart cart = customer.getShoppingCart();
            model.addAttribute("cart", cart);

        return "checkout";
    }


    @GetMapping("/order")
    public String order(){
        return "order";
    }

}