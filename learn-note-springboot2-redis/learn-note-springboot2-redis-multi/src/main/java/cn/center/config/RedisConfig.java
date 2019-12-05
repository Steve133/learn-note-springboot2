package cn.center.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author song
 * @title redis配置<br>
 *        lettuce连接池
 * @projectName demo
 * @description TODO
 * @date 2019年11月19日 下午7:44:12
 */
@Configuration
@EnableCaching
public class RedisConfig {

	/**
	 * @description: 配置lettuce连接池
	 * @return
	 * @author song
	 * @date 2019年11月21日 上午9:59:22
	 */
	@Bean
	@ConfigurationProperties(prefix = "spring.redis.lettuce.pool")
	public GenericObjectPoolConfig redisPool() {
		return new GenericObjectPoolConfig<>();
	}

	/**
	 * @description: 配置第一个数据源的
	 * @param host
	 * @param port
	 * @param db
	 * @param password
	 * @return
	 * @author song
	 * @date 2019年11月21日 上午10:00:28
	 */
	@Bean
//    @ConfigurationProperties(prefix = "spring.redis")
	public RedisStandaloneConfiguration redisConfig(@Value("${spring.redis.host}") String host, @Value("${spring.redis.port}") int port, @Value("${spring.redis.database}") int db, @Value("${spring.redis.password}") String password) {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(host, port);
		redisStandaloneConfiguration.setDatabase(db);
		redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
		return redisStandaloneConfiguration;
	}

	/**
	 * @description: 配置第二个数据源
	 * @return
	 * @author song
	 * @date 2019年11月21日 上午10:01:40
	 */
	@Bean
	@ConfigurationProperties(prefix = "spring.redis2")
	public RedisStandaloneConfiguration redisConfig2() {
		return new RedisStandaloneConfiguration();
	}

	/**
	 * @description: 配置第一个数据源的连接工厂 这里注意：需要添加@Primary 指定bean的名称，目的是为了创建两个不同名称的LettuceConnectionFactory
	 * @param config
	 * @param redisConfig
	 * @return
	 * @author song
	 * @date 2019年11月21日 上午10:03:56
	 */
	@Bean("factory")
	@Primary
	public LettuceConnectionFactory factory(GenericObjectPoolConfig config, RedisStandaloneConfiguration redisConfig) {
		LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder().poolConfig(config).build();
		return new LettuceConnectionFactory(redisConfig, clientConfiguration);
	}

	@Bean("factory2")
	public LettuceConnectionFactory factory2(GenericObjectPoolConfig config, RedisStandaloneConfiguration redisConfig2) {
		LettuceClientConfiguration clientConfiguration = LettucePoolingClientConfiguration.builder().poolConfig(config).build();
		return new LettuceConnectionFactory(redisConfig2, clientConfiguration);
	}

	/**
	 * @description: 配置第一个数据源的RedisTemplate<br>
	 *               注意：这里指定使用名称=factory 的 RedisConnectionFactory<br>
	 *               并且标识第一个数据源是默认数据源 @Primary
	 * @param factory
	 * @return
	 * @author song
	 * @date 2019年11月21日 上午10:04:08
	 */
	@Bean("redisTemplate")
	@Primary
	public RedisTemplate<String, String> redisTemplate(@Qualifier("factory") RedisConnectionFactory factory) {
		return getStringStringRedisTemplate(factory);
	}

	/**
	 * @description: 配置第一个数据源的RedisTemplate<br>
	 *               注意：这里指定使用名称=factory2 的 RedisConnectionFactory
	 * @param factory2
	 * @return
	 * @author song
	 * @date 2019年11月21日 上午10:05:15
	 */
	@Bean("redisTemplate2")
	public RedisTemplate<String, String> redisTemplate2(@Qualifier("factory2") RedisConnectionFactory factory2) {
		return getStringStringRedisTemplate(factory2);
	}

	/**
	 * @description: 设置序列化方式
	 * @param factory
	 * @return
	 * @author song
	 * @date 2019年11月21日 上午10:07:09
	 */
	private RedisTemplate<String, String> getStringStringRedisTemplate(RedisConnectionFactory factory) {
		StringRedisTemplate template = new StringRedisTemplate(factory);
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		
		template.setValueSerializer(jackson2JsonRedisSerializer);
		
		template.afterPropertiesSet();
		return template;
	}
}
