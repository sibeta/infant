package com.qianqian.infant.service.impl;

import com.qianqian.infant.entity.Category;
import com.qianqian.infant.entity.Customer;
import com.qianqian.infant.service.CustomerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceImplTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void findByCustomerNameTest() throws Exception {
        PageRequest request = new PageRequest(0, 2);
        Page<Customer> categoryPage = customerService.findByCustomerName("%浅浅%", request);
        Assert.assertNotNull(categoryPage);
    }

    @Test
    public void findOneTest() throws Exception {
        Customer customer = customerService.findOne(1);
        Assert.assertNotNull(customer);
    }

    @Test
    public void updateTest() throws Exception {
        Customer customer = customerService.findOne(1);
        customer.setMobile("11111111");

        Customer result = customerService.update(customer);
        Assert.assertNotNull(result);
    }

    @Test
    public void deleteTest() throws Exception {
        customerService.delete(1);
        Customer result = customerService.findOne(1);

        Assert.assertNull(result);
    }

}