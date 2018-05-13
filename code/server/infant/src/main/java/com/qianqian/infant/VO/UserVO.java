package com.qianqian.infant.VO;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.qianqian.infant.serializer.FakePasswordSerializer;
import com.qianqian.infant.serializer.RoleSerializer;
import lombok.Data;

import java.util.Date;

@Data
public class UserVO {

    private Integer userId;

    private String username;

    private String useralias;

    @JsonSerialize(using = FakePasswordSerializer.class)
    private String password;

    @JsonSerialize(using = RoleSerializer.class)
    private Integer role;

    private String avatar;

    private Date createTime;

    private Date updateTime;

}
