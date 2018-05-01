package com.qianqian.infant.service.impl;

import com.qianqian.infant.entity.Product;
import com.qianqian.infant.entity.Stock;
import com.qianqian.infant.enums.ResultEnum;
import com.qianqian.infant.exception.InfantException;
import com.qianqian.infant.repository.StockRepository;
import com.qianqian.infant.service.ProductService;
import com.qianqian.infant.service.StockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class StockServiceImpl implements StockService{

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ProductService productService;

    @Override
    public Page<Stock> findList(String productName, Pageable pageable) {
        return stockRepository.findByProductNameIsLike(productName, pageable);
    }

    @Override
    public Stock findOne(Integer stockId) {
        return stockRepository.findOne(stockId);
    }

    @Override
    @Transactional
    public Stock update(Stock stock) {
        Integer stockId = stock.getStockId();
        if(stockId == null) {
            Product product = productService.findByProductNameAndSize(stock.getProductName(), stock.getProductSize());
            productService.increaseStock(product, stock.getQuantity());
        }

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
