package com.qianqian.infant.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class OrderForm {

    @NotNull(message = "客户名称不能为空")
    private String customerName;

    @NotNull(message = "购物车不能为空")
    private String items;

    private Date createTime;

    private Date updateTime;

}
