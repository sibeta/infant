package com.qianqian.infant.enums;

import lombok.Getter;

@Getter
public enum RoleEnum implements CodeEnum{

    SUPER_ADMIN(0, "超级管理员"),
    ADMIN(1, "普通管理员"),
    USER(2, "普通用户"),
    ;

    private Integer code;

    private String message;

    RoleEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
