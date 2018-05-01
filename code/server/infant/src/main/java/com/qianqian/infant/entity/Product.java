package com.qianqian.infant.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue
    private Integer productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品价格
     */
    private BigDecimal productPrice;

    /**
     * 库存
     */
    private Integer productStock;

    /**
     * 尺寸
     */
    private Integer productSize;

    /**
     * 产品描述
     */
    private String description;

    /**
     * 类目编号
     */
    private Integer categoryId;

    private Date createTime;

    private Date updateTime;

}
