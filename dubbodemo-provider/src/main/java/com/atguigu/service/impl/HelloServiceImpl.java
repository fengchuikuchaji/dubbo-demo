package com.atguigu.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.atguigu.service.HelloService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 包名:com.atguigu.service.impl
 *
 * @author Leevi
 * 日期2022-09-02  10:11
 * 在提供者中配置声明式事务，会导致提供者无法发布到注册中心:
 * 1. 原因:
 *    1.1 声明式事务底层是使用动态代理技术,而因为我们没有手动指定使用JDK还是CGLIB的动态代理，所以Spring底层会根据我们HelloServiceImpl类
 *        实现了接口，从而采用JDK的动态代理技术创建代理对象。
 *    1.2 使用JDK的动态代理创建的代理对象的类型是 "com.sun.proxy.$Proxy18"
 *    1.3 而我们配置的dubbo服务注册包扫描它所扫描的是"com.atguigu",所以根本无法扫描到我们的那个代理对象,所以无法发布服务
 *
 * 2. 解决方案:
 *    2.1 手动指定使用CGLIB的动态代理:在开启事务注解驱动的时候，添加属性 proxy-target-class="true", 这时创建的代理对象的类型是
 *        com.atguigu.service.impl.HelloServiceImpl$$EnhancerBySpringCGLIB$$fe7e7bb6，它在"com.atguigu"包下，所以
 *        dubbo服务注册的包扫描能扫描到我们的代理对象
 *    2.2 我们要指定当前这个服务发布的时候，使用接口类型发布:在Dubbo的@Service中添加属性@Service(interfaceClass = HelloService.class)
 */
@Service(interfaceClass = HelloService.class)
public class HelloServiceImpl implements HelloService {

    @Transactional
    @Override
    public String sayHello(String name) {
        return "hello[8001]:"+name;
    }
}
