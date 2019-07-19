package com.learn.mongod.entity;

import lombok.Builder;
import lombok.Data;

/**
 * TODO
 * email leeoohoo@gmail.com
 * mongod-help
 * Created by lee on 2019-07-19.
 */
@Data
@Builder
public class Test {
    private Long id;
    
    private String name;
    
    private Integer age;
    
    private String nickName;
}
