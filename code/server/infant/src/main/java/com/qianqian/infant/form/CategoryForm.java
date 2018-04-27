package com.qianqian.infant.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

@Data
public class CategoryForm {

    private Integer categoryId;

    @NotEmpty(message = "密码必填")
    private String categoryName;

    private String categoryDesc;

    private Date createTime;

    private Date updateTime;

}
