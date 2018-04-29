package com.qianqian.infant.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue
    private Integer customerId;

    private Integer customerLevel;

    private String customerName;

    private String wechatName;

    private String mobile;

    private String postAddress;

    private Date babyBirthday;

    private String note;

    private Date createTime;

    private Date updateTime;

}
