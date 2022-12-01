package com.example.customer.controller;

import com.example.library.model.Customer;
import com.example.library.model.Order;
import com.example.library.model.OrderDetail;

import com.example.library.serivce.OrderDetailService;
import com.example.library.serivce.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
@Controller
public class OrderDetailController {


    @Autowired
    OrderDetailService orderDetailService;

    @Autowired
    OrderService orderService;


    @GetMapping("/find-order/{id}")
    public String findProductById(@PathVariable("id") Long id, Model model){

        Order order = orderService.Getorder(4);
        List<OrderDetail> orderDetail = order.getOrderDetailList();
        model.addAttribute("orderDetail",orderDetail);
        return "view-order";
    }
}
