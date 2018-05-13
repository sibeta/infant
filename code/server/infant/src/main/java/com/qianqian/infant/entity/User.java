package com.qianqian.infant.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue
    private Integer userId;

    private String username;

    private String useralias;

    private String password;

    private Integer role;

    private String avatar;

    private Date createTime;

    private Date updateTime;

}
