package com.qianqian.infant.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnum implements CodeEnum{

    NEW(0, "等待支付"),
    FINISHED(1, "支付成功")
    ;

    private Integer code;

    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
