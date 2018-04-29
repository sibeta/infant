package com.qianqian.infant.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
public class Stock {

    @Id
    @GeneratedValue
    private Integer stockId;

    /**
     * 类目Id
     */
    private Integer categoryId;

    /**
     * 类型
     */
    private Integer type;

    /**
     * 数量
     */
    private Integer amount;

    /**
     * 单价
     */
    private Double unitPrice;

    /**
     * 额外费用
     */
    private Double extraCharges;

    /**
     * 进货日期
     */
    private Date stockDate;

    /**
     * 到货时间
     */
    private Date aogDate;

    /**
     * 备注
     */
    private String note;

    private Date createTime;

    private Date updateTime;

}
