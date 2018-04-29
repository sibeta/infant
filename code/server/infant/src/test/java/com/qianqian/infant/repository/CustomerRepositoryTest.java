package com.qianqian.infant.repository;

import com.qianqian.infant.entity.Category;
import com.qianqian.infant.entity.Customer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void findAllTest() throws Exception {
        List<Customer> customerList = customerRepository.findAll();
        Assert.assertTrue(customerList.size() > 0);
    }

    @Test
    public void findByCustomerNameTest() throws Exception {
        PageRequest pageRequest = new PageRequest(0, 2);
        Page<Customer> customerList = customerRepository.findByCustomerNameIsLike("%浅浅%", pageRequest);
        Assert.assertTrue(customerList.getContent().size() > 0);
    }

    @Test
    public void findOneTest() throws Exception {
        Customer customer = customerRepository.findOne(1);
        Assert.assertNotNull(customer);
    }

    @Test
    public void insertTest() throws Exception {
        Customer customer = new Customer();
        customer.setCustomerName("浅浅");
        customer.setCustomerLevel(1);
        customer.setWechatName("浅浅");
        customer.setPostAddress("广州天河岑村");
        customer.setBabyBirthday(new Date());
        customer.setNote("VIP客户");
        customer.setMobile("15915749577");

        Customer result = customerRepository.save(customer);
        Assert.assertNotNull(result);
    }

    @Test
    public void updateTest() throws Exception {
        Customer customer = customerRepository.findOne(1);
        customer.setCustomerName("浅浅小姐");
        customer.setMobile("15918604398");

        Customer result = customerRepository.save(customer);
        Assert.assertNotNull(result);
    }

}