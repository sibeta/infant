package com.qianqian.infant.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    PARAM_ERROR(1, "参数错误"),
    SYSTEM_ERROR(2, "系统错误"),

    CATEGORY_NOT_EXIST(20, "品类不存在"),
    CUSTOMER_NOT_EXIST(21, "客户不存在"),
    STOCK_NOT_EXIST(22, "进货记录不存在"),
    PRODUCT_NOT_EXIST(23, "产品信息不存在"),
    PRODUCT_STOCK_ERROR(24, "库存错误"),
    ORDER_NOT_EXIST(25, "订单不存在"),
    CART_EMPTY(26, "购物车为空"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
