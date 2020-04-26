/*
 * @Description: 
 * @version: 
 * @Author: leeoohoo
 * @Date: 2020-04-24 16:27:35
 * @LastEditors: leeoohoo
 * @LastEditTime: 2020-04-26 10:24:21
 */
package com.learn.mongod.entity;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Test {
    private Long id;
    
    private String name;
    
    private Integer age;
    
    private String nickName;
}
