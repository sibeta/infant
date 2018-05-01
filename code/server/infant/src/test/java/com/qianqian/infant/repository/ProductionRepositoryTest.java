package com.qianqian.infant.repository;

import com.qianqian.infant.entity.Product;
import com.qianqian.infant.enums.TypeEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductionRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void insertTest() {
        Product product = new Product();
        product.setProductName("纸尿裤");
        product.setProductPrice(new BigDecimal(59));
        product.setProductSize(TypeEnum.L.getCode());
        product.setProductStock(100);
        product.setDescription("纸尿裤裤");
        product.setCategoryId(1);

        Product result = productRepository.save(product);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByProductNameIsLikeTest() {
        PageRequest request = new PageRequest(0, 2);
        Page<Product> productPage = productRepository.findByProductNameIsLike("%纸%", request);
        Assert.assertTrue(productPage.getContent().size() > 0);
    }
}