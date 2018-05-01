package com.qianqian.infant.DTO;

import lombok.Data;

@Data
public class CartDTO {

    private String productName;

    private Integer productSize;

    private Integer productQuantity;

    public CartDTO(String productName, Integer productSize, Integer productQuantity) {
        this.productName = productName;
        this.productSize = productSize;
        this.productQuantity = productQuantity;
    }
}
