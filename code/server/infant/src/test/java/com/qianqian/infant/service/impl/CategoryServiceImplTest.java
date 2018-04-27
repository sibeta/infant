package com.qianqian.infant.service.impl;

import com.qianqian.infant.entity.Category;
import com.qianqian.infant.service.CategoryService;
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
public class CategoryServiceImplTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void findListTest() throws Exception {
        PageRequest request = new PageRequest(0, 2);
        Page<Category> categoryPage = categoryService.findList(request);
        Assert.assertNotNull(categoryPage);
    }

    @Test
    public void findOneTest() throws Exception {
        Category category = categoryService.findOne(3);
        Assert.assertNotNull(category);
    }

    @Test
    public void updateTest() throws Exception {
        Category category = categoryService.findOne(3);
        if(category == null) {
            category = new Category();
            category.setCategoryId(3);
            category.setCreateTime(new Date());
        }
        category.setCategoryName("哈哈哈");
        category.setCategoryDesc("111111");
        Category result = categoryService.update(category);
        Assert.assertNotNull(result);
    }

    @Test
    public void deleteTest() throws Exception {
        categoryService.delete(3);
        Category category = categoryService.findOne(3);
        Assert.assertNull(category);
    }

}