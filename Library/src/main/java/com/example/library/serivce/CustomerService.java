package com.example.library.serivce;

import com.example.library.dto.CustomerDto;
import com.example.library.model.Customer;

import java.util.List;

public interface CustomerService {

    CustomerDto save(CustomerDto customerDto);

    Customer findByUsername(String username);

    Customer saveInfor(Customer customer);

    List<Customer> getCustommer();
}