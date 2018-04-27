package com.qianqian.infant.repository;

import com.qianqian.infant.entity.Category;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findAllTest() throws Exception {
        List<Category> categoryList = categoryRepository.findAll();
        Assert.assertTrue(categoryList.size() > 0);
    }

    @Test
    public void findOneTest() throws Exception {
        Category category = categoryRepository.findOne(0);
        Assert.assertNotNull(category);
    }

    @Test
    public void insertTest() throws Exception {
        Category category = new Category();
        category.setCategoryName("类目1");
        category.setCategoryDesc("类目1111");
        category.setCreateTime(new Date());
        Category result = categoryRepository.save(category);
        Assert.assertNotNull(result);
    }

    @Test
    public void updateTest() throws Exception {
        Category category = categoryRepository.findOne(3);
        category.setCategoryName("类目2");
        category.setCategoryDesc("类目2222");
        category.setCreateTime(new Date());
        Category result = categoryRepository.save(category);
        Assert.assertNotNull(result);
    }

}