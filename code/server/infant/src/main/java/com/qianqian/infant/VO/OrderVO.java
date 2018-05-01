package com.qianqian.infant.VO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.qianqian.infant.entity.OrderDetail;
import com.qianqian.infant.enums.PayStatusEnum;
import com.qianqian.infant.serializer.DateTimeSerializer;
import com.qianqian.infant.serializer.OrderStatusSerializer;
import com.qianqian.infant.serializer.PayStatusSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderVO {

    private String orderId;

    /**
     * 客户名称
     */
    private String customerName;

    /**
     * 总价
     */
    private BigDecimal totalPrice;

    /**
     * 订单状态
     */
    @JsonSerialize(using = OrderStatusSerializer.class)
    private Integer orderStatus;

    /**
     * 支付状态
     */
    @JsonSerialize(using = PayStatusSerializer.class)
    private Integer payStatus;

    @JsonSerialize(using = DateTimeSerializer.class)
    private Date createTime;

    private Date updateTime;

    List<OrderDetailVO> orderDetailVOList;

}
