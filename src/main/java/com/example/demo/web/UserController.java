package com.example.demo.web;

import com.example.demo.api.CommonResp;
import com.example.demo.api.UserReq;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.UUID;

/**
 * Created by Administrator on 2017/6/26.
 */
@RestController
@RequestMapping("user")
@Api("rest接口")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserDao userDao;

    @RequestMapping(value = "query", method = RequestMethod.GET)
    public CommonResp<UserEntity> getUser(@RequestParam(defaultValue = "1") int id) {
        UserEntity u = userService.findById(id);
        CommonResp resp = new CommonResp();
        resp.setData(u);
        return resp;
    }

    @RequestMapping(value = "update", method = RequestMethod.GET)
    public CommonResp<UserEntity> update(@RequestParam(defaultValue = "1") long id, int age) {
        userService.updateUserById(id, age);
        CommonResp resp = new CommonResp();
        return resp;
    }

    @RequestMapping(value = "list", method = RequestMethod.POST)
    @ApiOperation("查询全部")
    public @ApiResponses(@ApiResponse(code = 200, message = "响应成功"))
    CommonResp<UserReq> login(@ApiParam(value = "查询全部用户请求", required = true) @RequestBody UserReq userReq) {
        CommonResp resp = new CommonResp();
        resp.setData(userDao.findAll());
        return resp;
    }

    @RequestMapping(value = "save", method = RequestMethod.POST)
    public CommonResp save() {
        UserEntity user = new UserEntity();
        user.setUserName(UUID.randomUUID().toString());
        user.setAge(new Random().nextInt(30));
        userDao.save(user);
        CommonResp resp = new CommonResp();
        return resp;
    }


}
