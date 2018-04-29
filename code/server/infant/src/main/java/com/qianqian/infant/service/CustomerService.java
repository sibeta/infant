package com.qianqian.infant.service;

import com.qianqian.infant.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    public Page<Customer> findByCustomerName(String customerName, Pageable pageable);

    Customer findOne(Integer customerId);

    Customer update(Customer customer);

    void delete(Integer customerId);
}
