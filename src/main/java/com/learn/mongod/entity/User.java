package com.learn.mongod.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 用户实体
 * email leeoohoo@gmail.com
 * mongod-help
 * Created by lee on 2019-07-01.
 */
@Data
@AllArgsConstructor
public class User {
    private Long id;

    private String name;//

    private Integer age;

    private List<Role> roles;

    public User(){

    }

    public User(long l, String ddd, int i) {
        this.id = l;
        this.name = ddd;
        this.age = i;
    }
}
