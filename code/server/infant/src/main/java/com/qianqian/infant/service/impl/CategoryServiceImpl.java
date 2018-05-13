package com.qianqian.infant.service.impl;

import com.qianqian.infant.entity.Category;
import com.qianqian.infant.enums.ResultEnum;
import com.qianqian.infant.exception.InfantException;
import com.qianqian.infant.repository.CategoryRepository;
import com.qianqian.infant.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Page<Category> findList(Pageable pageable) {
        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        return categoryPage;
    }

    @Override
    public Category findOne(Integer categoryId) {
        return categoryRepository.findOne(categoryId);
    }

    @Override
    public Category update(Category category) {
        try {
            return categoryRepository.save(category);
        } catch (Exception e) {
            throw new InfantException(ResultEnum.SYSTEM_ERROR.getCode(), e.getMessage());
        }
    }

    @Override
    public void delete(Integer categoryId) {
        Category category = categoryRepository.findOne(categoryId);
        if(category == null) {
            log.error("【品类管理】品类不存在,categoryId={}", categoryId);
            throw new InfantException(ResultEnum.CATEGORY_NOT_EXIST);
        }

        categoryRepository.delete(categoryId);
    }

}
