package com.qianqian.infant.repository;

import com.qianqian.infant.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Override
    List<Category> findAll();

}
