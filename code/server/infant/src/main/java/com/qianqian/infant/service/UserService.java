package com.qianqian.infant.service;

import com.qianqian.infant.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    public Page<User> findByUsername(String username, Pageable pageable);

    User findOne(Integer userId);

    User update(User user);

    boolean login(String username, String password);

    void delete(Integer userId);

}
