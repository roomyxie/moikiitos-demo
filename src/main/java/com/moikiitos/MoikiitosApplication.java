package com.moikiitos;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@MapperScan(basePackages = "com.moikiitos.dao.mapper")
@MapperScan(basePackages = "com.moikiitos.dao.mapper")
@SpringBootApplication
public class MoikiitosApplication {
    public static void main(String[] args) {
        SpringApplication.run(MoikiitosApplication.class, args);
    }

}
