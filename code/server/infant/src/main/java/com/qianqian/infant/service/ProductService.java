package com.qianqian.infant.service;

import com.qianqian.infant.DTO.CartDTO;
import com.qianqian.infant.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    public Page<Product> findList(String productName, Pageable pageable);

    Product findOne(Integer productId);

    Product findByProductNameAndSize(String productName, Integer productSize);

    Product update(Product product);

    void delete(Integer productId);

    void increaseStock(List<CartDTO> cartDTOList);

    void increaseStock(Product product, Integer quantity);

    void decreaseStock(List<CartDTO> cartDTOList);
}
