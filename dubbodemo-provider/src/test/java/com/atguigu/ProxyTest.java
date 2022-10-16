package com.atguigu;

import com.atguigu.service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * 包名:com.atguigu
 *
 * @author Leevi
 * 日期2022-09-02  14:21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext-service.xml")
public class ProxyTest {
    @Autowired
    private HelloService helloService;

    @Test
    public void test01(){
        System.out.println(helloService.getClass());
    }

    public static void main(String[] args) {
        Map<String,String> map = new HashMap<>();
        String v1 = map.put("username", "aobama");
        String v2 = map.put("username", "aolafu");
        System.out.println(v1);
        System.out.println(v2);
    }
}
