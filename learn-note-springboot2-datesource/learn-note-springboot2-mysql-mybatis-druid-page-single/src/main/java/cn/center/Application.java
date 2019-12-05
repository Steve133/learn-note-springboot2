package cn.center;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(value = {"cn.center"})
@MapperScan("cn.center.mapper")
//都注释了也能回滚，这是为啥？？--------猜测：已经注入了SqlSessionTemplate到spring容器做sql代理--------但是SqlSessionTemplate又不支持事务回滚。。。搞不懂
//@EnableTransactionManagement
//@EnableAspectJAutoProxy
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}