package com.qianqian.infant.service.impl;

import com.qianqian.infant.DTO.CartDTO;
import com.qianqian.infant.DTO.OrderDTO;
import com.qianqian.infant.converter.OrderMaster2OrderDTOConverter;
import com.qianqian.infant.entity.OrderDetail;
import com.qianqian.infant.entity.OrderMaster;
import com.qianqian.infant.entity.Product;
import com.qianqian.infant.enums.OrderStatusEnum;
import com.qianqian.infant.enums.PayStatusEnum;
import com.qianqian.infant.enums.ResultEnum;
import com.qianqian.infant.exception.InfantException;
import com.qianqian.infant.repository.OrderDetailRepository;
import com.qianqian.infant.repository.OrderMasterRepository;
import com.qianqian.infant.service.OrderService;
import com.qianqian.infant.service.ProductService;
import com.qianqian.infant.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductService productService;

    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        BigDecimal totalPrice = new BigDecimal(0);
        List<OrderDetail> orderDetailList = orderDTO.getOrderDetailList();
        String orderId = KeyUtil.genUniqueKey();
        for(OrderDetail orderDetail : orderDetailList) {
            Product product = productService.findByProductNameAndSize(orderDetail.getProductName(), orderDetail.getProductSize());
            if(product == null) {
                throw new InfantException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            totalPrice = orderDetail.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity())).add(totalPrice);

            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            orderDetail.setProductSize(product.getProductSize());
            orderDetail.setProductName(product.getProductName());
            orderDetail.setProductId(product.getProductId());

            orderDetailRepository.save(orderDetail);
        }

        orderDTO.setOrderId(orderId);
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDTO, orderMaster);
        orderMaster.setTotalPrice(totalPrice);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.NEW.getCode());

        orderMasterRepository.save(orderMaster);

        List<CartDTO> cartDTOList = orderDetailList.stream().map(e ->
            new CartDTO(e.getProductName(), e.getProductSize(), e.getProductQuantity())
        ).collect(Collectors.toList());

        productService.decreaseStock(cartDTOList);

        return orderDTO;
    }

    @Override
    public OrderDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if(orderMaster == null) {
            throw new InfantException(ResultEnum.ORDER_NOT_EXIST);
        }

        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)) {
            throw new InfantException(ResultEnum.CART_EMPTY);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster, orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);

        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String customerName, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByCustomerNameIsLike(customerName, pageable);
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTOConverter.convert(orderMasterPage.getContent());

        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList, pageable, orderMasterPage.getTotalElements());
        return orderDTOPage;
    }

    @Override
    public void finish(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if(orderMaster == null) {
            log.error("【完结订单】订单不存在, orderId={}", orderId);
            throw new InfantException(ResultEnum.ORDER_NOT_EXIST);
        }

        orderMaster.setPayStatus(PayStatusEnum.FINISHED.getCode());
        orderMaster.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        orderMasterRepository.save(orderMaster);
    }

    @Override
    @Transactional
    public void cancel(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if(orderMaster == null) {
            log.error("【取消订单】订单不存在, orderId={}", orderId);
            throw new InfantException(ResultEnum.ORDER_NOT_EXIST);
        }

        orderMaster.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        orderMasterRepository.save(orderMaster);

        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        for(OrderDetail orderDetail : orderDetailList) {
            Integer productId = orderDetail.getProductId();
            Product product = productService.findOne(productId);
            if(product == null) {
                log.error("【取消订单】商品不存在, productId={}", productId);
                throw new InfantException(ResultEnum.PRODUCT_NOT_EXIST);
            }

            productService.increaseStock(product, orderDetail.getProductQuantity());
        }
    }
}
