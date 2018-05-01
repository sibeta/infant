package com.qianqian.infant.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class OrderDetail {

    @Id
    private String detailId;

    /**
     * 订单Id
     */
    private String orderId;

    /**
     * 商品Id
     */
    private Integer productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 类型
     */
    private Integer productSize;

    /**
     * 商品价格
     */
    private BigDecimal productPrice;

    /**
     * 商品数量
     */
    private Integer productQuantity;

    private Date createTime;

    private Date updateTime;

}
