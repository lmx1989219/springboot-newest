package com.example.demo;

import feign.Feign;
import feign.Param;
import feign.RequestLine;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.StringDecoder;
import feign.gson.GsonDecoder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by limingxin on 2017/7/5.
 */
@Slf4j
public class FeignTest {
    interface GitHub {
        @RequestLine("GET /repos/{owner}/{repo}/contributors")
        List<Contributor> contributors(@Param("owner") String owner, @Param("repo") String repo);

        @RequestLine("GET /")
        String get();
    }

    static class Contributor {
        String login;
        int contributions;
    }

    @Test
    public void testFeign() {
        GitHub github = Feign.builder().requestInterceptor(new BasicAuthRequestInterceptor("admin", "123"))
                .decoder(new GsonDecoder())
                .target(GitHub.class, "https://api.github.com");

        List<Contributor> contributors = github.contributors("OpenFeign", "feign");
        for (Contributor contributor : contributors) {
            log.info(contributor.login + " (" + contributor.contributions + ")");
        }
    }

    @Test
    public void testAuthFeign() {
        GitHub github = Feign.builder().requestInterceptor(new BasicAuthRequestInterceptor("admin", "123456"))
                .decoder(new StringDecoder())
                .target(GitHub.class, "http://47.88.35.216");

        log.info("auth server resp {}",github.get());
    }

    @Test
    public void testList() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            Integer e = it.next();
            if (e == 3)
                it.remove();
        }
        log.info("remaining list {}", list);
    }
}
