package com.qianqian.infant.service;

import com.qianqian.infant.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    public Page<Category> findList(Pageable pageable);

    Category findOne(Integer categoryId);

    Category update(Category category);

    void delete(Integer categoryId);
}
