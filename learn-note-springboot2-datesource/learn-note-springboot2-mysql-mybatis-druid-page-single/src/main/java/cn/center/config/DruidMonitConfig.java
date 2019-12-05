package cn.center.config;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;

/**
 * @author song
 * @title Druid监控配置
 * @projectName demo
 * @description TODO
 * @date 2019年11月20日 下午10:14:14
 */
@Configuration
public class DruidMonitConfig {
	private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
	/**
     * @ 白名单
     */
    private String allow;
    /**
     * @ 黑名单
     */
    private String deny;
    private String druid_username = "admin";
    private String druid_password = "123456";
    
    /**
     * @description: 注册 druid servlet
     * @return
     * @author song
     * @date 2019年11月20日 下午10:05:44
     */
	@Bean
	public ServletRegistrationBean druidStatViewServle() {
		//注册服务
		ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
		servletRegistrationBean.setServlet(new StatViewServlet());
		servletRegistrationBean.addUrlMappings("/druid/*");

		// 白名单(为空表示,所有的都可以访问,多个IP的时候用逗号隔开)
		if (!StringUtils.isEmpty(getAllow())) {
			servletRegistrationBean.addInitParameter("allow", getAllow()); // 白名单
		}
		// IP黑名单 (存在共同时，deny优先于allow) 
		if (!StringUtils.isEmpty(getDeny())) {
			servletRegistrationBean.addInitParameter("deny", getDeny()); // 黑名单
		}
		// 设置登录的用户名和密码
		servletRegistrationBean.addInitParameter("loginUsername", getDruid_username());
		servletRegistrationBean.addInitParameter("loginPassword", getDruid_password());
		// 是否能够重置数据.
		servletRegistrationBean.addInitParameter("resetEnable", "false");
		return servletRegistrationBean;
	}

	/**
	 * @description: 请求拦截器
	 * @return
	 * @author song
	 * @date 2019年11月20日 下午9:59:17
	 */
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		// 添加过滤规则
		filterRegistrationBean.addUrlPatterns("/*");
		// 添加不需要忽略的格式信息
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		logger.info("druid 监控 初始化成功!");
		return filterRegistrationBean;
	}

	
	
	/***************************************对请求方法进行监控******************************************/
	@Bean
	public DruidStatInterceptor druidStatInterceptor() {
		return new DruidStatInterceptor();
	}
	@Bean
	public JdkRegexpMethodPointcut druidStatPointcut() {
		JdkRegexpMethodPointcut druidStatPointcut = new JdkRegexpMethodPointcut();
		druidStatPointcut.setPatterns("cn.center.mapper.*","cn.center.service.*");
		return druidStatPointcut;
	}
	/**
	 * @description: 对请求方法进行监控
	 * @return
	 * @author song
	 * @date 2019年11月20日 下午9:59:00
	 */
	@Bean
	public Advisor druidStatAdvisor() {
		return new DefaultPointcutAdvisor(druidStatPointcut(), druidStatInterceptor());
	}
	/***************************************对请求方法进行监控end******************************************/
    
    public String getAllow() {
		return allow;
	}
	public void setAllow(String allow) {
		this.allow = allow;
	}
	public String getDeny() {
		return deny;
	}
	public void setDeny(String deny) {
		this.deny = deny;
	}
	public String getDruid_username() {
		return druid_username;
	}
	public void setDruid_username(String druid_username) {
		this.druid_username = druid_username;
	}
	public String getDruid_password() {
		return druid_password;
	}
	public void setDruid_password(String druid_password) {
		this.druid_password = druid_password;
	}
}
