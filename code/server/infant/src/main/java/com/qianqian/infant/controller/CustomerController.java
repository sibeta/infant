package com.qianqian.infant.controller;

import com.qianqian.infant.VO.CustomerVO;
import com.qianqian.infant.VO.ResultVO;
import com.qianqian.infant.entity.Customer;
import com.qianqian.infant.enums.ResultEnum;
import com.qianqian.infant.exception.InfantException;
import com.qianqian.infant.form.CustomerForm;
import com.qianqian.infant.service.CustomerService;
import com.qianqian.infant.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 客户相关
 */
@RestController
@RequestMapping("/api/customer")
@Slf4j
@CrossOrigin
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * 分页获取客户列表
     * @param page
     *          页码，从1开始
     * @param size
     *          每页条数
     * @return
     */
    @PostMapping(value = "/list")
    public ResultVO<Page<CustomerVO>> list(@RequestParam(value = "customerName", defaultValue = "%") String customerName,
                                           @RequestParam(value = "page", defaultValue = "1") Integer page,
                                           @RequestParam(value = "size", defaultValue = "7") Integer size) {
        PageRequest request = new PageRequest(page - 1, size);
        if(!customerName.contains("%")) {
            customerName = "%" + customerName + "%";
        }
        Page<Customer> customerPage = customerService.findByCustomerName(customerName, request);
        List<CustomerVO> customerVOList = new ArrayList<>();
        for(Customer customer : customerPage.getContent()) {
            CustomerVO customerVO = new CustomerVO();
            BeanUtils.copyProperties(customer, customerVO);
            customerVOList.add(customerVO);
        }

        Page<CustomerVO> customerVOPage = new PageImpl<CustomerVO>(customerVOList, request, customerPage.getTotalElements());
        return ResultVOUtil.success(customerVOPage);
    }

    /**
     * 添加/更新类目
     * @param customerForm
     *          customerForm
     * @param bindingResult
     *          bindingResult
     * @return
     */
    @PostMapping(value = "/update")
    public ResultVO<Boolean> update(@Valid CustomerForm customerForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.error("【客户管理】参数错误, error={}", bindingResult.getFieldError().getDefaultMessage());
            throw new InfantException(ResultEnum.PARAM_ERROR);
        }

        Customer customer = new Customer();
        BeanUtils.copyProperties(customerForm, customer);
        customerService.update(customer);

        return ResultVOUtil.success();
    }

    /**
     * 删除类目
     * @param customerId
     * @return
     */
    @PostMapping(value = "/delete")
    public ResultVO delete(Integer customerId) {
        customerService.delete(customerId);
        return ResultVOUtil.success();
    }

}
