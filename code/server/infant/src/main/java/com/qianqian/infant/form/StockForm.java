package com.qianqian.infant.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class StockForm {

    private Integer stockId;

    /**
     * 商品名称
     */
    @NotNull(message = "商品名称不能为空")
    private String productName;

    /**
     * 型号
     */
    @NotNull(message = "商品型号不能为空")
    private Integer productSize;

    /**
     * 数量
     */
    @NotNull(message = "商品数量不能为空")
    private Integer quantity;

    /**
     * 单价
     */
    @NotNull(message = "商品名称不能为空")
    private Double unitPrice;

    /**
     * 额外费用
     */
    @NotNull(message = "商品名称不能为空")
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
