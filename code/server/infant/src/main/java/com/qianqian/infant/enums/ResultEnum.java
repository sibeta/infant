package com.qianqian.infant.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {

    PARAM_ERROR(1, "参数错误"),

    CATEGORY_NOT_EXIST(20, "品类不存在"),
    CUSTOMER_NOT_EXIST(21, "客户不存在"),
    STOCK_NOT_EXIST(22, "进货记录不存在"),
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
