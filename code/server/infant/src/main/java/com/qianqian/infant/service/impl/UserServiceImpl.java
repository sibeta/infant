package com.qianqian.infant.service.impl;

import com.qianqian.infant.entity.User;
import com.qianqian.infant.enums.ResultEnum;
import com.qianqian.infant.exception.InfantException;
import com.qianqian.infant.repository.UserRepository;
import com.qianqian.infant.service.UserService;
import com.qianqian.infant.utils.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public Page<User> findByUsername(String username, Pageable pageable) {
        Page<User> userPage = userRepository.findByUsernameIsLike(username, pageable);
        return userPage;
    }

    @Override
    public User findOne(Integer userId) {
        return userRepository.findOne(userId);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean login(String username, String password) {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            log.error("【用户登录】用户不存在,username={}", username);
            throw new InfantException(ResultEnum.USER_NOT_EXIST);
        }

        String encryptedPwd = MD5Util.MD5EncodeUtf8(password);
        if(!encryptedPwd.equals(user.getPassword())) {
            log.error("【密码错误】");
            throw new InfantException(ResultEnum.PASSWORD_ERROR);
        }

        return true;
    }

    @Override
    public void delete(Integer userId) {
        User user = userRepository.findOne(userId);
        if(user == null) {
            log.error("【删除用户】用户不存在,userId={}", userId);
            throw new InfantException(ResultEnum.USER_NOT_EXIST);
        }

        userRepository.delete(userId);
    }

}
