package com.qianqian.infant.repository;

import com.qianqian.infant.entity.Category;
import com.qianqian.infant.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Page<Customer> findByCustomerNameIsLike(String customerName, Pageable pageable);

}
