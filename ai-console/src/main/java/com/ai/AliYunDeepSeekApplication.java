package com.ai;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.ai")
@MapperScan(basePackages = {"com.ai.mapper"})
public class AliYunDeepSeekApplication {
    public static void main(String[] args) {
        SpringApplication.run(AliYunDeepSeekApplication.class, args);
        System.out.println("Hello world!");
    }
}
