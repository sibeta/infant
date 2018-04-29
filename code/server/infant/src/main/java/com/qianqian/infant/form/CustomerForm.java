package com.qianqian.infant.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class CustomerForm {

    private Integer categoryId;

    @NotEmpty(message = "客户名称必填")
    private String customerName;

    private Integer customerLevel;

    private String wechatName;

    @NotEmpty(message = "手机号必填")
    @Pattern(regexp="^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$",message="请输入正确的手机号码")
    private String mobile;

    private String postAddress;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date babyBirthday;

    private String note;

    private Date createTime;

    private Date updateTime;

}
