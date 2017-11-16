package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.dao.UserMapper;
import com.example.demo.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by limingxin on 2017/11/15.
 */
@Service
@Slf4j
public class UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    UserMapper userMapper;

    /**
     * 增加缓存
     *
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "redis.cache.user", key = "#id"/*keyGenerator = "keyGenerator"*/)
    public UserEntity findById(long id) {
        return userDao.findOne(id);
    }

    /**
     * 清空缓存
     *
     * @param id
     * @param age
     */
    @CacheEvict(cacheNames = "redis.cache.user", key = "#id"/*keyGenerator = "keyGenerator"*/)
    public void updateUserById(long id, int age) {
        UserEntity u = userDao.findOne(id);
        u.setAge(age);
        userDao.save(u);
    }

    @Transactional
    public UserEntity getById(long id) {
        return userMapper.findById(id);
    }

    @Transactional
    public void saveUser(UserEntity userEntity) {
        userMapper.save(userEntity);
    }

}
