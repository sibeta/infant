package com.qianqian.infant.repository;

import com.qianqian.infant.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface UserRepository extends JpaRepository<User, Integer> {

    Page<User> findByUsernameIsLike(String username, Pageable pageable);

    User findByUsername(String username);

}
