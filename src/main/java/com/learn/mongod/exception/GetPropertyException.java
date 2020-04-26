/*
 * @Description: 
 * @version: 
 * @Author: leeoohoo
 * @Date: 2020-03-26 11:18:12
 * @LastEditors: leeoohoo
 * @LastEditTime: 2020-03-26 18:42:41
 */
package com.learn.mongod.exception;

/**
 * project name: my-basic-api
 * package: com.leeoohoo.mybasicapi.exception
 * description:
 * create time: 2020-03-19
 *
 * @author terrydu terrydu@vip.qq.com
 */
public class GetPropertyException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public GetPropertyException(String message) {
        super(message);
    }
}
