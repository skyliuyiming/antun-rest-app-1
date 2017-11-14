package com.zdjy.bigdata.antun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableScheduling
@MapperScan("com.zdjy.bigdata.antun.mapper")
public class AntunRun {
	public static void main(String[] args) {
		SpringApplication.run(AntunRun.class, args);
	}
}
