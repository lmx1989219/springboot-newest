package com.example.demo.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by limingxin on 2017/7/5.
 */
@Data
@ApiModel
public class CommonResp<T> {
    @ApiModelProperty("状态码")
    String code = "0000";
    @ApiModelProperty("消息")
    String msg = "成功";
    @ApiModelProperty("数据对象")
    T data;
}
