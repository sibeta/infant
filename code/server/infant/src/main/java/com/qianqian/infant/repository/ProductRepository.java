package com.qianqian.infant.repository;

import com.qianqian.infant.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Page<Product> findByProductNameIsLike(String productName, Pageable pageable);

    Product findByProductNameAndProductSize(String productName, Integer productSize);

}
