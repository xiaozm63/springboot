package cn.tedu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("cn.tedu.ssm.mapper")
@EnableEurekaClient
public class RunAppProviderUser {

	public static void main(String[] args) {
		SpringApplication.run(RunAppProviderUser.class, args);
	}

}
