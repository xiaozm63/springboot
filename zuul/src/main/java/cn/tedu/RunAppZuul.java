package cn.tedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy		//执行zuul
public class RunAppZuul {
	public static void main(String[] args) {
		SpringApplication.run(RunAppZuul.class, args);
	}
}
