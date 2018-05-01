package com.qianqian.infant.repository;

import com.qianqian.infant.entity.Stock;
import com.qianqian.infant.enums.TypeEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StockRepositoryTest {

    @Autowired
    private StockRepository stockRepository;

    @Test
    public void findAllTest() throws Exception {
        List<Stock> stockList = stockRepository.findAll();
        Assert.assertTrue(stockList.size() > 0);
    }

    @Test
    public void findByProductNameTest() throws Exception {
        PageRequest request = new PageRequest(0, 2);
        Page<Stock> stockPage = stockRepository.findByProductNameIsLike("%纸尿%", request);
        Assert.assertTrue(stockPage.getContent().size() > 0);
    }

    @Test
    public void findOneTest() throws Exception {
        Stock stock = stockRepository.findOne(1);
        Assert.assertNotNull(stock);
    }

    @Test
    public void insertTest() throws Exception {
        Stock stock = new Stock();
        stock.setProductSize(TypeEnum.L.getCode());
        stock.setQuantity(20);
        stock.setProductName("纸尿裤");
        stock.setExtraCharges(0.0);
        stock.setUnitPrice(20.0);
        stock.setStockDate(new Date());
        stock.setNote("大码");

        Stock result = stockRepository.save(stock);
        Assert.assertNotNull(result);
    }

    @Test
    public void updateTest() throws Exception {
        Stock stock = stockRepository.findOne(1);
        stock.setProductSize(TypeEnum.M.getCode());
        stock.setNote("给vip");

        Stock result = stockRepository.save(stock);
        Assert.assertNotNull(result);
    }

}