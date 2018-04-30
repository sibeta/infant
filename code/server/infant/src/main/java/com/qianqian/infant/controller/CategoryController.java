package com.qianqian.infant.controller;

import com.qianqian.infant.VO.ResultVO;
import com.qianqian.infant.entity.Category;
import com.qianqian.infant.enums.ResultEnum;
import com.qianqian.infant.exception.InfantException;
import com.qianqian.infant.form.CategoryForm;
import com.qianqian.infant.service.CategoryService;
import com.qianqian.infant.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 类目相关
 */
@RestController
@RequestMapping("/api/category")
@Slf4j
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = "/listcategory")
    public ResultVO<List<Category>> listCategory() {
        List<Category> categoryList = categoryService.findAll();
        return ResultVOUtil.success(categoryList);
    }

    /**
     * 分页获取类目列表
     * @param page
     *          页码，从1开始
     * @param size
     *          每页条数
     * @return
     */
    @GetMapping(value = "/list")
    public ResultVO<Page<Category>> list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                         @RequestParam(value = "size", defaultValue = "7") Integer size) {
        PageRequest request = new PageRequest(page - 1, size);
        Page<Category> categoryPage = categoryService.findList(request);

        return ResultVOUtil.success(categoryPage);
    }

    /**
     * 添加/更新类目
     * @param categoryForm
     *          categoryForm
     * @param bindingResult
     *          bindingResult
     * @return
     */
    @PostMapping(value = "/update")
    public ResultVO<Boolean> update(@Valid CategoryForm categoryForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.error("【品类管理】参数错误, categoryForm={}", categoryForm);
            throw new InfantException(ResultEnum.PARAM_ERROR);
        }

        Category category = new Category();
        BeanUtils.copyProperties(categoryForm, category);
        categoryService.update(category);

        return ResultVOUtil.success();
    }

    /**
     * 删除类目
     * @param categoryId
     * @return
     */
    @PostMapping(value = "/delete")
    public ResultVO delete(Integer categoryId) {
        categoryService.delete(categoryId);
        return ResultVOUtil.success();
    }

}
