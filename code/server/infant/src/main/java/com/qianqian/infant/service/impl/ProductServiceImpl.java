package com.qianqian.infant.service.impl;

import com.qianqian.infant.DTO.CartDTO;
import com.qianqian.infant.entity.Product;
import com.qianqian.infant.enums.ResultEnum;
import com.qianqian.infant.exception.InfantException;
import com.qianqian.infant.repository.ProductRepository;
import com.qianqian.infant.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Page<Product> findList(String productName, Pageable pageable) {
        return productRepository.findByProductNameIsLike(productName, pageable);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findOne(Integer productId) {
        return productRepository.findOne(productId);
    }

    @Override
    public Product findByProductNameAndSize(String productName, Integer productSize) {
        Product product = productRepository.findByProductNameAndProductSize(productName, productSize);
        if(product == null) {
            throw new InfantException(ResultEnum.PRODUCT_NOT_EXIST);
        }

        return product;
    }


    @Override
    public Product update(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void delete(Integer productId) {
        Product product = this.findOne(productId);
        if(product == null) {
            throw new InfantException(ResultEnum.PRODUCT_NOT_EXIST);
        }

        productRepository.delete(product);
    }

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO : cartDTOList) {
            Product product = productRepository
                    .findByProductNameAndProductSize(cartDTO.getProductName(), cartDTO.getProductSize());
            if(product == null) {
                throw new InfantException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            increaseStock(product, cartDTO.getProductQuantity());
        }

    }

    @Override
    public void increaseStock(Product product, Integer quantity) {
        Integer result = product.getProductStock() + quantity;
        product.setProductStock(result);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDTO> cartDTOList) {
        for(CartDTO cartDTO : cartDTOList) {
            Product product = productRepository
                    .findByProductNameAndProductSize(cartDTO.getProductName(), cartDTO.getProductSize());
            if(product == null) {
                throw new InfantException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            Integer result = product.getProductStock() - cartDTO.getProductQuantity();
            if(result < 0) {
                throw new InfantException(ResultEnum.PRODUCT_STOCK_ERROR);
            }

            product.setProductStock(result);
            productRepository.save(product);
        }
    }

}
