package com.example.demo.dao;

import com.example.demo.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

/**
 * Created by limingxin on 2017/11/16.
 */
@Mapper
public interface UserMapper {

    UserEntity findById(@Param("id") long id);

}