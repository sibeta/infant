package com.qianqian.infant.service;

import com.qianqian.infant.DTO.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    OrderDTO create(OrderDTO orderDTO);

    OrderDTO findOne(String orderId);

    Page<OrderDTO> findList(String customerName, Pageable pageable);

    OrderDTO cancel(OrderDTO orderDTO);

    OrderDTO finish(OrderDTO orderDTO);
}
