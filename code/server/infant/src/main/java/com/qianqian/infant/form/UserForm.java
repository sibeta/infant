package com.qianqian.infant.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;

@Data
public class UserForm {

    private Integer userId;

    @NotEmpty(message = "用户名称必填")
    private String username;

    @NotEmpty(message = "用户别名必填")
    private String useralias;

    @NotEmpty(message = "密码必填")
    private String password;

    private Integer role;

    private String avatar;

    private Date createTime;

    private Date updateTime;

}
