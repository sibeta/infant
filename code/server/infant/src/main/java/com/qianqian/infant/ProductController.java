package com.qianqian.infant;

import com.qianqian.infant.VO.ProductVO;
import com.qianqian.infant.VO.ResultVO;
import com.qianqian.infant.entity.Product;
import com.qianqian.infant.enums.ResultEnum;
import com.qianqian.infant.exception.InfantException;
import com.qianqian.infant.form.ProductForm;
import com.qianqian.infant.service.ProductService;
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
 * 商品相关
 */
@RestController
@RequestMapping("/api/product")
@Slf4j
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 分页获取商品列表
     * @param page
     *          页码，从1开始
     * @param size
     *          每页条数
     * @return
     */
    @PostMapping(value = "/list")
    public ResultVO<Page<ProductVO>> list(@RequestParam(value = "productName", defaultValue = "%") String productName,
                                           @RequestParam(value = "page", defaultValue = "1") Integer page,
                                           @RequestParam(value = "size", defaultValue = "7") Integer size) {
        PageRequest request = new PageRequest(page - 1, size);
        log.info("productName={}", productName);
        if(!productName.contains("%")) {
            productName = "%" + productName + "%";
        }
        Page<Product> productPage = productService.findList(productName, request);
        List<ProductVO> productVOList = new ArrayList<>();
        for(Product product : productPage.getContent()) {
            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(product, productVO);
            productVOList.add(productVO);
        }

        Page<ProductVO> productVOPage = new PageImpl<ProductVO>(productVOList, request, productPage.getTotalElements());
        return ResultVOUtil.success(productVOPage);
    }

    /**
     * 添加/更新
     * @param productForm
     *          productForm
     * @param bindingResult
     *          bindingResult
     * @return
     */
    @PostMapping(value = "/update")
    public ResultVO<Boolean> update(@Valid ProductForm productForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.error("【商品管理】参数错误, error={}", bindingResult.getFieldError().getDefaultMessage());
            throw new InfantException(ResultEnum.PARAM_ERROR);
        }

        Product product = new Product();
        BeanUtils.copyProperties(productForm, product);
        productService.update(product);

        return ResultVOUtil.success();
    }

    /**
     * 删除
     * @param productId
     *          productId
     * @return
     */
    @PostMapping(value = "/delete")
    public ResultVO delete(Integer productId) {
        productService.delete(productId);
        return ResultVOUtil.success();
    }

}
