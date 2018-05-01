package com.qianqian.infant.controller;

import com.qianqian.infant.DTO.OrderDTO;
import com.qianqian.infant.VO.OrderDetailVO;
import com.qianqian.infant.VO.OrderVO;
import com.qianqian.infant.VO.ResultVO;
import com.qianqian.infant.converter.OrderForm2OrderDTOConverter;
import com.qianqian.infant.entity.OrderDetail;
import com.qianqian.infant.enums.ResultEnum;
import com.qianqian.infant.exception.InfantException;
import com.qianqian.infant.form.OrderForm;
import com.qianqian.infant.service.OrderService;
import com.qianqian.infant.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public ResultVO<Page<OrderVO>> list(@RequestParam(value = "customerName", defaultValue = "%") String customerName,
                                        @RequestParam(value = "page", defaultValue = "1") Integer page,
                                        @RequestParam(value = "size", defaultValue = "7") Integer size) {
        PageRequest request = new PageRequest(page - 1, size);
        if(!customerName.contains("%")) {
            customerName = "%" + customerName + "%";
        }
        Page<OrderDTO> orderDTOPage = orderService.findList(customerName, request);

        List<OrderVO> orderVOList = new ArrayList<>();
        for(OrderDTO orderDTO : orderDTOPage.getContent()) {
            OrderVO orderVO = new OrderVO();
            BeanUtils.copyProperties(orderDTO, orderVO);
            orderVOList.add(orderVO);
        }

        Page<OrderVO> orderVOPage = new PageImpl<OrderVO>(orderVOList, request, orderDTOPage.getTotalElements());
        return ResultVOUtil.success(orderVOPage);
    }

    @PostMapping(value = "/detail")
    public ResultVO<OrderVO> detail(@RequestParam(value = "orderId") String orderId) {
        OrderDTO orderDTO = orderService.findOne(orderId);
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(orderDTO, orderVO);
        List<OrderDetailVO> orderDetailVOList = new ArrayList<>();
        for(OrderDetail orderDetail : orderDTO.getOrderDetailList()) {
            OrderDetailVO orderDetailVO = new OrderDetailVO();
            BeanUtils.copyProperties(orderDetail, orderDetailVO);
            orderDetailVOList.add(orderDetailVO);
        }
        orderVO.setOrderDetailVOList(orderDetailVOList);
        return ResultVOUtil.success(orderVO);
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

    /**
     * 完结订单
     * @param orderId
     *          orderId
     * @return
     */
    @PostMapping(value = "/finish")
    public ResultVO<Boolean> finish(@RequestParam(value = "orderId") String orderId) {
        orderService.finish(orderId);
        return ResultVOUtil.success();
    }

    /**
     * 取消订单
     * @param orderId
     *          orderId
     * @return
     */
    @PostMapping(value = "/cancel")
    public ResultVO<Boolean> cancel(@RequestParam(value = "orderId") String orderId) {
        orderService.cancel(orderId);
        return ResultVOUtil.success();
    }
}
