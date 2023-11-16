package com.opera.system;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestApplication {

    // 使用原生注解@Value()导入配置
    @Value("${user.id}")
    private String id;

    @Test
    void test1(){
        System.out.println("id:"+id);
    }
}
