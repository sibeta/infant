package com.qianqian.infant.exception;

import com.qianqian.infant.enums.ResultEnum;
import lombok.Getter;

@Getter
public class InfantException extends RuntimeException{

    private Integer code;

    public InfantException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public InfantException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
