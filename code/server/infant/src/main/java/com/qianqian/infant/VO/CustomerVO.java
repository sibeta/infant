package com.qianqian.infant.VO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.qianqian.infant.serializer.CustomerLevelSerializer;
import com.qianqian.infant.serializer.DateSerializer;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
public class CustomerVO {

    private Integer customerId;

    @JsonSerialize(using = CustomerLevelSerializer.class)
    private Integer customerLevel;

    private String customerName;

    private String wechatName;

    private String mobile;

    private String postAddress;

    @JsonSerialize(using = DateSerializer.class)
    private Date babyBirthday;

    private String note;

    private Date createTime;

    private Date updateTime;

}
