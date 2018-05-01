package com.qianqian.infant.controller;

import com.qianqian.infant.DTO.OrderDTO;
import com.qianqian.infant.VO.ResultVO;
import com.qianqian.infant.converter.OrderForm2OrderDTOConverter;
import com.qianqian.infant.enums.ResultEnum;
import com.qianqian.infant.exception.InfantException;
import com.qianqian.infant.form.OrderForm;
import com.qianqian.infant.service.OrderService;
import com.qianqian.infant.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 订单相关
 */
@RestController
@RequestMapping("/api/order")
@Slf4j
@CrossOrigin
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 分页获取进货列表
     * @param page
     *          页码，从1开始
     * @param size
     *          每页条数
     * @return
     */
    @PostMapping(value = "/list")
    public ResultVO<Page<OrderDTO>> list(@RequestParam(value = "customerName", defaultValue = "%") String customerName,
                                           @RequestParam(value = "page", defaultValue = "1") Integer page,
                                           @RequestParam(value = "size", defaultValue = "7") Integer size) {
        PageRequest request = new PageRequest(page - 1, size);
        if(!customerName.contains("%")) {
            customerName = "%" + customerName + "%";
        }
        Page<OrderDTO> orderDTOPage = orderService.findList(customerName, request);

        return ResultVOUtil.success(orderDTOPage);
    }

    /**
     * 添加/更新
     * @param orderForm
     *          orderForm
     * @param bindingResult
     *          bindingResult
     * @return
     */
    @PostMapping(value = "/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.error("【订单管理】参数错误, error={}", bindingResult.getFieldError().getDefaultMessage());
            throw new InfantException(ResultEnum.PARAM_ERROR);
        }

        OrderDTO orderDTO = OrderForm2OrderDTOConverter.convert(orderForm);
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new InfantException(ResultEnum.CART_EMPTY);
        }

        OrderDTO result = orderService.create(orderDTO);

        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderId());
        return ResultVOUtil.success(map);
    }

}
