package com.qianqian.infant.VO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.qianqian.infant.serializer.CategorySerializer;
import com.qianqian.infant.serializer.TypeSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductVO {

    private Integer productId;

    /**
     * 产品名称
     */
    private String productName;

    /**
     * 产品价格
     */
    private BigDecimal productPrice;

    /**
     * 库存
     */
    private Integer productStock;

    /**
     * 尺寸
     */
    @JsonSerialize(using = TypeSerializer.class)
    private Integer productSize;

    /**
     * 产品描述
     */
    private String description;

    /**
     * 类目编号
     */
    @JsonSerialize(using = CategorySerializer.class)
    private Integer categoryId;

    private Date createTime;

    private Date updateTime;

}
