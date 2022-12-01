package com.example.library.serivce;

import com.example.library.model.OrderDetail;

import java.util.List;

public interface OrderDetailService {

    List<OrderDetail> GetOrderDetailByID(long id);
}
