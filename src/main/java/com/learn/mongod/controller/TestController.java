package com.learn.mongod.controller;

import com.learn.mongod.base.LMongo;
import com.learn.mongod.domian.MPageData;
import com.learn.mongod.entity.Test;
import org.springframework.data.domain.Page;
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
        Page page = LMongo.find(Test.class)
                .page(new MPageData(request))
                .where(new MPageData(request))
                .findPage();

        return page;

    }
}
