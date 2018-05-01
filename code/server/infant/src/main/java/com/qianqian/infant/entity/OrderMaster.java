package com.qianqian.infant.entity;

import com.qianqian.infant.enums.OrderStatusEnum;
import com.qianqian.infant.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    @Id
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
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    /**
     * 支付状态
     */
    private Integer payStatus = PayStatusEnum.NEW.getCode();

    private Date createTime;

    private Date updateTime;

}
