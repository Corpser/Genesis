package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


//@EnableDiscoveryClient
@SpringBootApplication
@EnableSwagger2
//@EnableFeignClients
@MapperScan("com.jiaxiaoxuan.*.mapper")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
