package cn.center.config.datasource;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import cn.center.config.datasource.DataSourceType.DataBaseType;

//将一个java类定义为切面类
@Aspect
@Component
public class DataSourceAop {
	private static Logger logger = LoggerFactory.getLogger(DataSourceAop.class);
	
	//execution(): 表达式主体
	//第一个*号：表示返回类型，*号表示所有的类型
	//包名：表示需要拦截的包名，后面的两个句点表示当前包和当前包的所有子包，cn.center.service.impl包、子孙包下所有类的方法。
	//第二个*号：表示类名，*号表示所有的类
	//*(..):最后这个星号表示方法名，*号表示所有的方法，后面括弧里面表示方法的参数，两个句点表示任何参数
	
	//cn.center.service.impl下当前包的所有子包中匹配TransactionTestService1*的所有save*方法
	@Before("execution(* cn.center.service1.impl..*.*(..))")//这里要写清楚DATABASE01过滤到的哪些service或方法，不然会与DATABASE02混淆
	public void setDataSource1database01() {
		logger.info("database01业务");
		DataSourceType.setDataBaseType(DataBaseType.DATABASE01);
	}
	
	@Before("execution(* cn.center.service2.impl..*.*(..))")
	public void setDataSource2database02() {
		logger.info("database02业务");
		DataSourceType.setDataBaseType(DataBaseType.DATABASE02);
	}
}
