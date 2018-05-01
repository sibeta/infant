package com.qianqian.infant.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qianqian.infant.DTO.OrderDTO;
import com.qianqian.infant.entity.OrderDetail;
import com.qianqian.infant.entity.OrderMaster;
import com.qianqian.infant.enums.ResultEnum;
import com.qianqian.infant.exception.InfantException;
import com.qianqian.infant.form.OrderForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO convert(OrderForm orderForm) {
        Gson gson = new Gson();

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCustomerName(orderForm.getCustomerName());

        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (Exception e) {
            log.error("【对象转换】错误, string={}", orderForm.getItems());
            throw new InfantException(ResultEnum.PARAM_ERROR);
        }
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

}
