package cn.jt;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringCloudApplication
@EnableFeignClients
public class RunAppJtSso {

	public static void main(String[] args) {
		SpringApplication.run(RunAppJtSso.class, args);
	}

}
