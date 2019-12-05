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
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableAspectJAutoProxy
@MapperScan(basePackages = "cn.center.mapper.one")
public class HikariDataSourceOneConfig {

	@Primary
	@Bean(name = "dataSourceOne")
	@ConfigurationProperties("spring.datasource.hikari.one")
	public DataSource dataSourceOne() throws SQLException {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}

	@Primary
	@Bean(name = "sqlSessionFactoryOne")
	public SqlSessionFactory sqlSessionFactoryOne(@Qualifier("dataSourceOne") DataSource dataSourceOne) throws Exception {
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSourceOne);
		sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:cn/center/mapper/one/*.xml"));
		return sessionFactory.getObject();
	}

//	注入SqlSessionTemplate到spring容器做sql代理   与   事务管理transactionManager代理sql 都行
//	前者写法：注释加上@MapperScan(basePackages = "cn.center.mapper.datasource1",sqlSessionTemplateRef="app1SqlSessionTemplate")
//	后者加上注释@EnableTransactionManagement

//	@Bean("sqlsessiontemplateOne")
//	@Primary
//	public SqlSessionTemplate sqlsessiontemplateOne(@Qualifier("sqlSessionFactoryOne") SqlSessionFactory sessionfactory) {
//		return new SqlSessionTemplate(sessionfactory);
//	}

	@Primary
	@Bean("transactionManagerOne")
    public PlatformTransactionManager transactionManagerOne(DataSource dataSourceOne) throws SQLException {
        return new DataSourceTransactionManager(dataSourceOne);
    }
}
