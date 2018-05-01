package com.qianqian.infant.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class ProductForm {

    private Integer productId;

    /**
     * 产品名称
     */
    @NotNull(message = "商品名称不能为空")
    private String productName;

    /**
     * 产品价格
     */
    @NotNull(message = "商品价格不能为空")
    private BigDecimal productPrice;

    /**
     * 库存
     */
    @NotNull(message = "库存不能为空")
    private Integer productStock;

    /**
     * 尺寸
     */
    @NotNull(message = "尺寸不能为空")
    private Integer productSize;

    /**
     * 产品描述
     */
    private String description;

    /**
     * 类目编号
     */
    @NotNull(message = "类目编号不能为空")
    private Integer categoryId;

    private Date createTime;

    private Date updateTime;

}
