package com.qianqian.infant.handler;

import com.qianqian.infant.VO.ResultVO;
import com.qianqian.infant.exception.InfantException;
import com.qianqian.infant.utils.ResultVOUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class InfantExceptionHandler {

    @ExceptionHandler(value = InfantException.class)
    @ResponseBody
    public ResultVO handlePayException(InfantException e) {
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }

}
