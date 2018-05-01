package com.qianqian.infant.repository;

import com.qianqian.infant.entity.OrderDetail;
import com.qianqian.infant.enums.TypeEnum;
import com.qianqian.infant.utils.KeyUtil;
import org.hibernate.criterion.Order;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Test
    public void insertTest() {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId(KeyUtil.genUniqueKey());
        orderDetail.setProductName("纸尿裤");
        orderDetail.setProductPrice(new BigDecimal(59));
        orderDetail.setProductQuantity(20);
        orderDetail.setProductSize(TypeEnum.L.getCode());
        orderDetail.setOrderId("1525073919918783901");

        OrderDetail result = orderDetailRepository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrOrderIdTest() throws Exception {
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId("1525073919918783901");
        Assert.assertTrue(orderDetailList.size() > 0);
    }

}