package com.qianqian.infant.repository;

import com.qianqian.infant.entity.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface StockRepository extends JpaRepository<Stock, Integer> {

    Page<Stock> findByCategoryId(Integer categoryId, Pageable pageable);

}
