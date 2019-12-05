package cn.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = {"cn.center"})
@EnableCaching//开启缓存
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}