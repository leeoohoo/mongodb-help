/*
 * @Description: 
 * @version: 
 * @Author: leeoohoo
 * @Date: 2020-04-24 16:27:35
 * @LastEditors: leeoohoo
 * @LastEditTime: 2020-04-26 10:41:13
 */
package com.learn.mongod.controller;

import java.util.List;

import com.learn.mongod.base.LMongo;
import com.learn.mongod.domian.MPageData;
import com.learn.mongod.domian.MyQueryResult;
import com.learn.mongod.entity.Test;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * testController
 * email leeoohoo@gmail.com
 * mongod-help
 * Created by lee on 2019-07-18.
 */
@RestController
public class TestController {

    @GetMapping("test")
    public Object findPage(ServerHttpRequest request) {
        LMongo.save(Test.builder().age(1).name("dd").nickName("fff").id(System.currentTimeMillis()).build());
        List<Test> tests = LMongo.find(Test.class)
                .page(new MPageData(request))
                .where(new MPageData(request))
                .findList();

        MyQueryResult<Test> myQueryResult = LMongo.find(Test.class)
        .page(new MPageData(request))
        .where(new MPageData(request))
        .findPage();
        return tests;

    }
}
