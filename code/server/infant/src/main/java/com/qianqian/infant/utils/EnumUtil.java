package com.qianqian.infant.utils;

import com.qianqian.infant.enums.CodeEnum;

public class EnumUtil {

    public static <T extends CodeEnum> T getByCode(Object code, Class<T> enumClass) {
        for(T each : enumClass.getEnumConstants()) {
            if(code.equals(each.getCode())) {
                return each;
            }
        }

        return null;
    }
}
