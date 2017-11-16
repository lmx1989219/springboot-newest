package com.example.demo;

import com.example.demo.dao.UserDao;
import com.example.demo.dao.UserMapper;
import com.example.demo.entity.UserEntity;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DemoApplicationTests {
    @Autowired
    UserService userService;
    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Test
    public void redisTest() {
        for (int i = 1000; i < 2000; ++i) {
            long start = System.currentTimeMillis();
            redisTemplate.opsForValue().set("demo:test" + i, "hello");
            log.info("cost {}ms", System.currentTimeMillis() - start);
        }
    }

    @Test
    public void mybatisTest() {
        UserEntity user = new UserEntity();
        user.setUserName(UUID.randomUUID().toString());
        user.setAge(new Random().nextInt(30));
        userService.saveUser(user);
        log.info("result={}", userService.getById(5));
    }

    @Test
    public void jpaTest() {
        log.info("result={}", userService.findById(1));
    }

}
