package cn.center.config;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy
@MapperScan(basePackages = "cn.center.mapper.two")
public class HikariDataSourceTwoConfig {
	
	@Bean(name = "dataSourceTwo")
	@ConfigurationProperties("spring.datasource.druid.two")
	public DataSource dataSourceTwo() throws SQLException {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}

	@Bean(name = "sqlSessionFactoryTwo")
	public SqlSessionFactory sqlSessionFactoryTwo(@Qualifier("dataSourceTwo")DataSource dataSourceTwo) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSourceTwo);
		sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:cn/center/mapper/two/*.xml"));
		return sessionFactory.getObject();
	}

	
//	注入SqlSessionTemplate到spring容器做sql代理   与   事务管理transactionManager代理sql 都行
//	前者写法：注释加上@MapperScan(basePackages = "cn.center.mapper.datasource1",sqlSessionTemplateRef="app1SqlSessionTemplate")
//	后者加上注释@EnableTransactionManagement
	
//	@Bean("sqlsessiontemplateTwo")
//	public SqlSessionTemplate sqlsessiontemplateTwo(@Qualifier("sqlSessionFactoryTwo") SqlSessionFactory sessionfactory) {
//		return new SqlSessionTemplate(sessionfactory);
//	}
	
	@Bean("transactionManagerTwo")
	public PlatformTransactionManager transactionManagerTwo(DataSource dataSourceTwo) throws SQLException {
		return new DataSourceTransactionManager(dataSourceTwo);
	}
	
}
