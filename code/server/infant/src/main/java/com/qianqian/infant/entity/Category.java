package com.qianqian.infant.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue
    private Integer categoryId;

    private String categoryName;

    private String categoryDesc;

    private Date createTime;

    private Date updateTime;

}
