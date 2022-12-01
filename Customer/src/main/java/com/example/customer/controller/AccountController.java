package com.example.customer.controller;


import com.example.library.model.Customer;
import com.example.library.model.ShoppingCart;
import com.example.library.serivce.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class AccountController {

    @Autowired
    private CustomerService customerService;


    @GetMapping("/account")
    public String accountHome(Model model , Principal principal , HttpSession session){
        if(principal == null){
            return "redirect:/login";
        }
        model.addAttribute("KT","Login");
        String username = principal.getName();
        Customer customer = customerService.findByUsername(username);
        model.addAttribute("customer", customer);


        ShoppingCart cart = customer.getShoppingCart();
        session.setAttribute("totalItems", cart.getTotalItems());
        session.setAttribute("totalPrice", cart.getTotalPrices());
        return "account";
    }

    @RequestMapping(value = "/update-infor", method = {RequestMethod.GET, RequestMethod.PUT})
    public String updateCustomer(
            @ModelAttribute("customer") Customer customer,
            Model model,
            RedirectAttributes redirectAttributes,
            Principal principal){
        if(principal == null){
            return "redirect:/login";
        }
        model.addAttribute("KT","Login");
        Customer customerSaved = customerService.saveInfor(customer);

        redirectAttributes.addFlashAttribute("customer", customerSaved);

        return "redirect:/account";
    }
}
