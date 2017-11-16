package com.example.demo.dao;

import com.example.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Administrator on 2017/6/26.
 */
public interface UserDao extends JpaRepository<UserEntity, Long> {

    UserEntity findByAge(int id);
}
