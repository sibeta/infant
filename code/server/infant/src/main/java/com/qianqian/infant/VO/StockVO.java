package com.qianqian.infant.VO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.qianqian.infant.serializer.CategorySerializer;
import com.qianqian.infant.serializer.DateSerializer;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
public class StockVO {

    private Integer stockId;

    /**
     * 类目Id
     */
    @JsonSerialize(using = CategorySerializer.class)
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
    @JsonSerialize(using = DateSerializer.class)
    private Date stockDate;

    /**
     * 到货时间
     */
    @JsonSerialize(using = DateSerializer.class)
    private Date aogDate;

    /**
     * 备注
     */
    private String note;

    private Date createTime;

    private Date updateTime;

}
