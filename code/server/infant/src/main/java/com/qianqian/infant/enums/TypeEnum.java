package com.qianqian.infant.enums;

import lombok.Getter;

@Getter
public enum TypeEnum implements CodeEnum{

    NONE(0, "无"),
    S(1, "小码"),
    M(2, "中码"),
    L(3, "大码"),
    XL(4, "加大码"),
    XXL(5, "加加大码"),
    XXXL(5, "加加加大码"),
    ;

    private Integer code;

    private String message;

    TypeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
