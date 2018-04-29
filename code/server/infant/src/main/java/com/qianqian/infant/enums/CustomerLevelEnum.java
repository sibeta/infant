package com.qianqian.infant.enums;

import lombok.Getter;

@Getter
public enum CustomerLevelEnum implements CodeEnum{

    LEVEL_VIP(0, "总代"),
    LEVEL_BIG(1, "大米乐"),
    LEVEL_SMALL(2, "小米乐"),
    ;

    private Integer code;

    private String message;

    CustomerLevelEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
