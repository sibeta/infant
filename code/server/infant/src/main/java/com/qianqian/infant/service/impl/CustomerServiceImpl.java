package com.qianqian.infant.service.impl;

import com.qianqian.infant.entity.Category;
import com.qianqian.infant.entity.Customer;
import com.qianqian.infant.enums.ResultEnum;
import com.qianqian.infant.exception.InfantException;
import com.qianqian.infant.repository.CategoryRepository;
import com.qianqian.infant.repository.CustomerRepository;
import com.qianqian.infant.service.CategoryService;
import com.qianqian.infant.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Page<Customer> findByCustomerName(String customerName, Pageable pageable) {
        Page<Customer> customerPage = customerRepository.findByCustomerNameIsLike(customerName, pageable);
        return customerPage;
    }

    @Override
    public Customer findOne(Integer categoryId) {
        return customerRepository.findOne(categoryId);
    }

    @Override
    public Customer update(Customer customer) {
        Customer result = customerRepository.save(customer);
        return result;
    }

    @Override
    public void delete(Integer customerId) {
        Customer customer = customerRepository.findOne(customerId);
        if(customer == null) {
            log.error("【客户管理】客户不存在,categoryId={}", customerId);
            throw new InfantException(ResultEnum.CUSTOMER_NOT_EXIST);
        }

        customerRepository.delete(customerId);
    }

}
