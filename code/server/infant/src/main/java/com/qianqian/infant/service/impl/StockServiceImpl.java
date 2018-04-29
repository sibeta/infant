package com.qianqian.infant.service.impl;

import com.qianqian.infant.entity.Stock;
import com.qianqian.infant.enums.ResultEnum;
import com.qianqian.infant.exception.InfantException;
import com.qianqian.infant.repository.StockRepository;
import com.qianqian.infant.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StockServiceImpl implements StockService{

    @Autowired
    private StockRepository stockRepository;

    @Override
    public Page<Stock> findList(Integer categoryId, Pageable pageable) {
        Page<Stock> stockPage = null;
        if(categoryId == 0) {
            stockPage = stockRepository.findAll(pageable);
        } else {
            stockPage = stockRepository.findByCategoryId(categoryId, pageable);
        }
        return stockPage;
    }

    @Override
    public Stock findOne(Integer stockId) {
        return stockRepository.findOne(stockId);
    }

    @Override
    public Stock update(Stock stock) {
        Stock result = stockRepository.save(stock);
        return result;
    }

    @Override
    public void delete(Integer stockId) {
        Stock stock = stockRepository.findOne(stockId);
        if(stock == null) {
            log.error("【进货管理】进货记录不存在,stockId={}", stockId);
            throw new InfantException(ResultEnum.STOCK_NOT_EXIST);
        }

        stockRepository.delete(stockId);
    }
}
