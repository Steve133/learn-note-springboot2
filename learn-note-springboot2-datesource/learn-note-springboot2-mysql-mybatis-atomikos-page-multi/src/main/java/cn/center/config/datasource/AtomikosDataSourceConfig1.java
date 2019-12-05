package cn.center.config.datasource;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.github.pagehelper.PageHelper;
import com.mysql.cj.jdbc.MysqlXADataSource;

@Configuration
@EnableConfigurationProperties(value = { DBConfig1.class })
@MapperScan(basePackages = "cn.center.mapper.datasource1", sqlSessionTemplateRef = "app1SqlSessionTemplate")
public class AtomikosDataSourceConfig1 {

	@Primary
	@Bean(name = "app1DataSource")
	public DataSource app1DataSource(DBConfig1 ds) throws SQLException {
		MysqlXADataSource mysqlxadatasource = new MysqlXADataSource();
		mysqlxadatasource.setUrl(ds.getUrl());
		mysqlxadatasource.setPassword(ds.getPassword());
		mysqlxadatasource.setUser(ds.getUsername());
		mysqlxadatasource.setPinGlobalTxToPhysicalConnection(true);
		
		AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
		xaDataSource.setXaDataSource(mysqlxadatasource);
		
		xaDataSource.setMinPoolSize(ds.getMinPoolSize());//#连接池中保留的最小连接数
		xaDataSource.setMaxPoolSize(ds.getMaxPoolSize());//连接池中保留的最大连接数
		xaDataSource.setMaxLifetime(ds.getMaxLifetime());
		xaDataSource.setBorrowConnectionTimeout(ds.getBorrowConnectionTimeout());
		xaDataSource.setLoginTimeout(ds.getLoginTimeout());
		xaDataSource.setMaintenanceInterval(ds.getMaintenanceInterval());
		xaDataSource.setMaxIdleTime(ds.getMaxIdleTime());//最大空闲时间,60秒内未使用则连接被丢弃。若为0则永不丢弃。Default: 0
		xaDataSource.setUniqueResourceName("app1DataSource");
		
		return xaDataSource;
	}

	@Bean(name = "app1SqlSessionFactory")
	@Primary
	public SqlSessionFactory app1SqlSessionFactory(@Qualifier("app1DataSource") DataSource datasource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(datasource);
		
		// mybatis分页
        PageHelper pageHelper = new PageHelper();
        Properties props = new Properties();
//        oracle,mysql,mariadb,sqlite,hsqldb,postgresql,db2,sqlserver,informix,h2,sqlserver2012,derby
//        特别注意：使用 SqlServer2012 数据库时，需要手动指定为 sqlserver2012，否则会使用 SqlServer2005 的方式进行分页。
        props.setProperty("dialect", "mysql");
        props.setProperty("reasonable", "true");
        props.setProperty("supportMethodsArguments", "true");
        props.setProperty("returnPageInfo", "check");
        props.setProperty("params", "count=countSql");
        pageHelper.setProperties(props); // 添加插件
        bean.setPlugins(new Interceptor[]{pageHelper});
		bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:cn.center.mapper.datasource1/*.xml"));
		return bean.getObject();
	}

	@Bean("app1SqlSessionTemplate")
	@Primary
	public SqlSessionTemplate app1sqlsessiontemplate(@Qualifier("app1SqlSessionFactory") SqlSessionFactory sessionfactory) {
		return new SqlSessionTemplate(sessionfactory);
	}

}
