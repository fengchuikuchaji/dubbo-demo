package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 包名:com.atguigu.controller
 *
 * @author Leevi
 * 日期2022-09-02  10:27
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @Reference(loadbalance = "roundrobin")
    private HelloService helloService;

    @GetMapping("/sayHello")
    public String sayHello(@RequestParam("name") String name){
        System.out.println("hello world!!!");
        return helloService.sayHello(name);
    }
}
