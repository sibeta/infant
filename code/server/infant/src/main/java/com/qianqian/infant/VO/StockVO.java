package com.qianqian.infant.VO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.qianqian.infant.serializer.CategorySerializer;
import com.qianqian.infant.serializer.DateSerializer;
import com.qianqian.infant.serializer.TypeSerializer;
import lombok.Data;

import java.util.Date;

@Data
public class StockVO {

    private Integer stockId;

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
     * 数量
     */
    private Integer quantity;

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
