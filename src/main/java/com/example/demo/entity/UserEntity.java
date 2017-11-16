package com.example.demo.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * redis缓存必须实现序列化接口
 * Created by Administrator on 2017/6/26.
 */
@Entity
@Table(name = "user")
@Data
public class UserEntity implements Serializable {
    @Id
    @GeneratedValue
    long id;
    String userName;
    int age;
}
