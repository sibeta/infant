package com.qianqian.infant.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class LoginForm {

    private Integer userId;

    @NotEmpty(message = "用户名称不能为空")
    private String username;

    @NotEmpty(message = "密码不能为空")
    private String password;

}
