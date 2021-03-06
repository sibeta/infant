package com.qianqian.infant.service;

import com.qianqian.infant.DTO.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    OrderDTO create(OrderDTO orderDTO);

    OrderDTO findOne(String orderId);

    Page<OrderDTO> findList(String customerName, Pageable pageable);

    void finish(String orderId);

    void cancel(String orderId);
}
