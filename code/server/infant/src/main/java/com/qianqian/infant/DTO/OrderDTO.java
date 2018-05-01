package com.qianqian.infant.DTO;

import com.qianqian.infant.entity.OrderDetail;
import com.qianqian.infant.enums.OrderStatusEnum;
import com.qianqian.infant.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {

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
    private Integer orderStatus;

    /**
     * 支付状态
     */
    private Integer payStatus;

    private Date createTime;

    private Date updateTime;

    List<OrderDetail> orderDetailList;

}
