package com.qianqian.infant.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
public class StockForm {

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
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date stockDate;

    /**
     * 到货时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date aogDate;

    /**
     * 备注
     */
    private String note;

    private Date createTime;

    private Date updateTime;

}
