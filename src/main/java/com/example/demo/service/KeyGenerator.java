package com.example.demo.service;

import com.google.common.base.Joiner;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.nio.charset.Charset;

/**
 * Created by limingxin on 2017/11/15.
 */
@Component("keyGenerator")
@Slf4j
public class KeyGenerator implements org.springframework.cache.interceptor.KeyGenerator {
    @Override
    public Object generate(Object arg0, Method arg1, Object... arg2) {
        String className = arg0.getClass().getSimpleName();
        String methodName = arg1.getName();
        if (arg2 != null && arg2.length > 0) {
            HashCode param = Hashing.md5().hashString(Joiner.on(",").join(arg2), Charset.forName("utf8"));
            String key = className + "." + methodName + "(" + param.toString() + ")";
            log.info("cache key={}", key);
            return key;
        }
        String key = className + "." + methodName + "()";
        log.info("cache key={}", key);
        return key;
    }
}
