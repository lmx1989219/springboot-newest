package com.example.demo.api;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by limingxin on 2017/7/5.
 */
@Data
public class UserReq {
    @ApiModelProperty(value = "用户名", required = true)
    String userName;
    @ApiModelProperty(value = "密码", required = true,example="30,31",allowableValues = "666",notes = ".............")
    String pwd;
    @ApiModelProperty("年龄")
    int age;
}
