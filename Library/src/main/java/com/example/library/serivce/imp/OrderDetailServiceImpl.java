package com.example.library.serivce.imp;


import com.example.library.model.Admin;
import com.example.library.model.OrderDetail;
import com.example.library.repository.OrderDetailRepository;
import com.example.library.serivce.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderDetailServiceImpl implements OrderDetailService {


    @Autowired
    private OrderDetailRepository orderDetailRepository;

    public  List<OrderDetail> GetOrderDetailByID(long id){
        return orderDetailRepository.getOrderDetailById(id);
    }



}
