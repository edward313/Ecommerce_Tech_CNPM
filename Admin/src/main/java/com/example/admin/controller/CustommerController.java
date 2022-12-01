package com.example.admin.controller;


import com.example.library.model.Category;
import com.example.library.model.Customer;
import com.example.library.serivce.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class CustommerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/custommer")
    public String categories(Model model, Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        List<Customer> customer = customerService.getCustommer();
        model.addAttribute("customer", customer);

        model.addAttribute("size", customer.size());
        return "customer";
    }

}


