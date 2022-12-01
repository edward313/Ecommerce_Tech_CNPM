package com.example.library.repository;


import com.example.library.model.Admin;
import com.example.library.model.OrderDetail;
import com.example.library.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {

    @Query("select c from OrderDetail c where c.order=?1")
    List<OrderDetail> getOrderDetailById(long id);
}
