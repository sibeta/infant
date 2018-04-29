package com.qianqian.infant.controller;

import com.qianqian.infant.VO.CustomerVO;
import com.qianqian.infant.VO.ResultVO;
import com.qianqian.infant.VO.StockVO;
import com.qianqian.infant.entity.Customer;
import com.qianqian.infant.entity.Stock;
import com.qianqian.infant.enums.ResultEnum;
import com.qianqian.infant.exception.InfantException;
import com.qianqian.infant.form.CustomerForm;
import com.qianqian.infant.form.StockForm;
import com.qianqian.infant.service.CustomerService;
import com.qianqian.infant.service.StockService;
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
 * 进货相关
 */
@RestController
@RequestMapping("/api/stock")
@Slf4j
@CrossOrigin
public class StockController {

    @Autowired
    private StockService stockService;

    /**
     * 分页获取进货列表
     * @param page
     *          页码，从1开始
     * @param size
     *          每页条数
     * @return
     */
    @PostMapping(value = "/list")
    public ResultVO<Page<StockVO>> list(@RequestParam(value = "categoryId", defaultValue = "0") Integer categoryId,
                                           @RequestParam(value = "page", defaultValue = "1") Integer page,
                                           @RequestParam(value = "size", defaultValue = "7") Integer size) {
        PageRequest request = new PageRequest(page - 1, size);
        Page<Stock> stockPage = stockService.findList(categoryId, request);
        List<StockVO> stockVOList = new ArrayList<>();
        for(Stock stock : stockPage.getContent()) {
            StockVO stockVO = new StockVO();
            BeanUtils.copyProperties(stock, stockVO);
            stockVOList.add(stockVO);
        }

        Page<StockVO> stockVOPage = new PageImpl<StockVO>(stockVOList, request, stockPage.getTotalElements());
        return ResultVOUtil.success(stockVOPage);
    }

    /**
     * 添加/更新类目
     * @param stockForm
     *          stockForm
     * @param bindingResult
     *          bindingResult
     * @return
     */
    @PostMapping(value = "/update")
    public ResultVO<Boolean> update(@Valid StockForm stockForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.error("【进货管理】参数错误, error={}", bindingResult.getFieldError().getDefaultMessage());
            throw new InfantException(ResultEnum.PARAM_ERROR);
        }

        Stock stock = new Stock();
        BeanUtils.copyProperties(stockForm, stock);
        stockService.update(stock);

        return ResultVOUtil.success();
    }

    /**
     * 删除类目
     * @param stockId
     *          stockId
     * @return
     */
    @PostMapping(value = "/delete")
    public ResultVO delete(Integer stockId) {
        stockService.delete(stockId);
        return ResultVOUtil.success();
    }

}
