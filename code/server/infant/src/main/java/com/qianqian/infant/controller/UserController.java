package com.qianqian.infant.controller;

import com.qianqian.infant.VO.ResultVO;
import com.qianqian.infant.VO.UserVO;
import com.qianqian.infant.config.Constant;
import com.qianqian.infant.entity.User;
import com.qianqian.infant.enums.ResultEnum;
import com.qianqian.infant.exception.InfantException;
import com.qianqian.infant.form.LoginForm;
import com.qianqian.infant.form.UserForm;
import com.qianqian.infant.service.UserService;
import com.qianqian.infant.utils.MD5Util;
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
 * 用户相关
 */
@RestController
@RequestMapping("/api/user")
@Slf4j
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 分页获取用户列表
     * @param page
     *          页码，从1开始
     * @param size
     *          每页条数
     * @return
     */
    @PostMapping(value = "/list")
    public ResultVO<Page<UserVO>> list(@RequestParam(value = "username", defaultValue = "%") String username,
                                           @RequestParam(value = "page", defaultValue = "1") Integer page,
                                           @RequestParam(value = "size", defaultValue = "7") Integer size) {
        PageRequest request = new PageRequest(page - 1, size);
        if(!username.contains("%")) {
            username = "%" + username + "%";
        }
        Page<User> userPage = userService.findByUsername(username, request);
        List<UserVO> userVOList = new ArrayList<>();
        for(User user : userPage.getContent()) {
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user, userVO);
            userVOList.add(userVO);
        }

        Page<UserVO> userVOPage = new PageImpl<UserVO>(userVOList, request, userPage.getTotalElements());
        return ResultVOUtil.success(userVOPage);
    }

    /**
     * 添加/更新
     * @param userForm
     *          userForm
     * @param bindingResult
     *          bindingResult
     * @return
     */
    @PostMapping(value = "/update")
    public ResultVO<Boolean> update(@Valid UserForm userForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.error("【客户管理】参数错误, error={}", bindingResult.getFieldError().getDefaultMessage());
            throw new InfantException(ResultEnum.PARAM_ERROR);
        }

        User user = new User();
        BeanUtils.copyProperties(userForm, user);

        Integer userId = user.getUserId();
        String password = user.getPassword();
        log.info("【更新用户】,userId={}", userId);
        if(userId == null || !Constant.FAKE_PASSWORD.equals(password)) {
            String encryptedPwd = MD5Util.MD5EncodeUtf8(password);
            user.setPassword(encryptedPwd);
        } else {
            User dbUser = userService.findOne(userId);
            if(dbUser == null) {
                log.error("【更新用户】用户不存在,userId={}", userId);
                throw new InfantException(ResultEnum.USER_NOT_EXIST);
            }

            user.setPassword(dbUser.getPassword());
        }

        userService.update(user);

        return ResultVOUtil.success();
    }

    @PostMapping(value = "/login")
    public ResultVO<Boolean> login(@Valid LoginForm loginForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String errMsg = bindingResult.getFieldError().getDefaultMessage();
            log.error("【用户管理】参数错误, error={}", errMsg);
            throw new InfantException(ResultEnum.PARAM_ERROR.getCode(), errMsg);
        }

        boolean result = userService.login(loginForm.getUsername(), loginForm.getPassword());
        return ResultVOUtil.success(result);
    }

    /**
     * 删除
     * @param userId
     * @return
     */
    @PostMapping(value = "/delete")
    public ResultVO delete(Integer userId) {
        userService.delete(userId);
        return ResultVOUtil.success();
    }

}
