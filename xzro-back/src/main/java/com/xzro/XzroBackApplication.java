package com.xzro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Xzro
 */
@SpringBootApplication
@MapperScan("com.xzro.mapper")
public class XzroBackApplication {
    public static void main(String[] args) {
        SpringApplication.run(XzroBackApplication.class, args);
    }

}
