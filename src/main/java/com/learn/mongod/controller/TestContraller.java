package com.learn.mongod.controller;

import com.learn.mongod.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试控制层
 * email leeoohoo@gmail.com
 * mongod-help
 * Created by lee on 2019-07-01.
 */
@RestController
@RequestMapping("test")
public class TestContraller {

    private final TestService testService;

    public TestContraller(TestService testService) {
        this.testService = testService;
    }


    @GetMapping("save")
    public Object save() {
        Object result = testService.save();
        return result;
    }

    @GetMapping("delete")
    public Object delete() {
        Object result = testService.delete();
        return result;
    }

    @GetMapping("update")
    public Object update() {
        Object result = testService.update();
        return result;
    }

    @GetMapping("find")
    public Object find() {
        Object result = testService.find();
        return result;
    }

    @GetMapping("find_page")
    public Object findPage() {
        Object result = testService.findPage();
        return result;
    }
}
