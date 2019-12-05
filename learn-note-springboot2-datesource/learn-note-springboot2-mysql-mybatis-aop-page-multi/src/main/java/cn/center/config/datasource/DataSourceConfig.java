package cn.center.config.datasource;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.github.pagehelper.PageHelper;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@MapperScan(basePackages = "cn.center.mapper", sqlSessionFactoryRef = "SqlSessionFactory")
public class DataSourceConfig {
	@Primary
	@Bean(name = "database1DataSource")
	@ConfigurationProperties(prefix = "spring.datasource.database1")
	public DataSource getDateSource1() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}

	@Bean(name = "database2DataSource")
	@ConfigurationProperties(prefix = "spring.datasource.database2")
	public DataSource getDateSource2() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}

	@Bean(name = "dynamicDataSource")
	public DynamicDataSource DataSource(@Qualifier("database1DataSource") DataSource database1DataSource, @Qualifier("database2DataSource") DataSource database2DataSource) {
		Map<Object, Object> targetDataSource = new HashMap<>();
		targetDataSource.put(DataSourceType.DataBaseType.DATABASE01, database1DataSource);
		targetDataSource.put(DataSourceType.DataBaseType.DATABASE02, database2DataSource);
		DynamicDataSource dataSource = new DynamicDataSource();
		dataSource.setTargetDataSources(targetDataSource);
		dataSource.setDefaultTargetDataSource(database1DataSource);
		return dataSource;
	}

	@Bean(name = "SqlSessionFactory")
	public SqlSessionFactory database1SqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dynamicDataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dynamicDataSource);
		
		// mybatis分页
        PageHelper pageHelper = new PageHelper();
        Properties props = new Properties();
//      oracle,mysql,mariadb,sqlite,hsqldb,postgresql,db2,sqlserver,informix,h2,sqlserver2012,derby
//      特别注意：使用 SqlServer2012 数据库时，需要手动指定为 sqlserver2012，否则会使用 SqlServer2005 的方式进行分页。
        props.setProperty("dialect", "mysql");
        props.setProperty("reasonable", "true");
        props.setProperty("supportMethodsArguments", "true");
        props.setProperty("returnPageInfo", "check");
        props.setProperty("params", "count=countSql");
        pageHelper.setProperties(props); // 添加插件
        bean.setPlugins(new Interceptor[]{pageHelper});
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:cn.center.mapper/*.xml"));
		return bean.getObject();
	}

}
