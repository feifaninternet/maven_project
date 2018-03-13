package com.czh.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
/**
 * @author xfan
 * @date Created on 2018/2/7 -- 15:21
 * @desc
 */
@MapperScan(basePackages = "com.java.web.dao.mybatis")
@PropertySource("classpath:properties/system.properties")
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
