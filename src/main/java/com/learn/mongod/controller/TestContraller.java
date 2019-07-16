package com.learn.mongod.controller;

import com.learn.mongod.domian.MPageData;
import com.learn.mongod.service.TestService;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.SslInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.InetSocketAddress;


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
    public Object findPage(ServerHttpRequest request) throws Exception {
        InetSocketAddress remoteAddress = request.getRemoteAddress();
        SslInfo sslInfo = request.getSslInfo();
        String hostName = remoteAddress.getHostName();
        InetAddress address = remoteAddress.getAddress();
        String hostString = remoteAddress.getHostString();

        Object result = testService.findPage(new MPageData(request));
        return result;
    }




}
