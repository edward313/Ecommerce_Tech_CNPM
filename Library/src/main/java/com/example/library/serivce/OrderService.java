package com.example.library.serivce;

import com.example.library.model.Order;
import com.example.library.model.ShoppingCart;

public interface OrderService {

    void saveOrder(ShoppingCart shoppingCart);

    Order Getorder(long id);

}
