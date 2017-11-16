package com.example.demo;

import com.example.demo.dao.UserDao;
import com.example.demo.dao.UserMapper;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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
            redisTemplate.opsForValue().set("test1" + i, "hello1");
            redisTemplate.opsForValue().set("test2" + i, "hello2");
            log.info("cost {}ms", System.currentTimeMillis() - start);
        }
    }

    @Test
    public void mybatisTest() {
        log.info("result={},{}", userService.findById(1), userService.getById(1));
    }

}
