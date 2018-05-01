package com.qianqian.infant.VO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.qianqian.infant.serializer.TypeSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderDetailVO {

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
    @JsonSerialize(using = TypeSerializer.class)
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
