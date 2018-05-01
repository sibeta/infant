package com.qianqian.infant.service;

import com.qianqian.infant.entity.Customer;
import com.qianqian.infant.entity.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StockService {

    public Page<Stock> findList(String productName, Pageable pageable);

    Stock findOne(Integer stockId);

    Stock update(Stock stock);

    void delete(Integer stockId);
}
