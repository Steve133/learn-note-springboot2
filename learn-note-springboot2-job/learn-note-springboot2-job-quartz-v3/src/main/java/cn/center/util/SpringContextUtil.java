package cn.center.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author song
 * @title 获取spring bean工厂
 * 
 * @date 2019年11月19日 上午9:32:31
 */
@Component
public final class SpringContextUtil implements BeanFactoryPostProcessor {

	/** Spring应用上下文环境 */
	private static ConfigurableListableBeanFactory beanFactory;

	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		SpringContextUtil.beanFactory = beanFactory;
	}

	/**
	 * @description 获取对象
	 * @param name 一个以所给名字注册的bean的实例
	 * @return
	 * @throws BeansException
	 * @author song
	 * @date 2019年12月7日 上午1:27:23
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) throws BeansException {
		return (T) beanFactory.getBean(name);
	}

	public static <T> T getBean(Class<T> clazz) {
		return beanFactory.getBean(clazz);
	}

	/**
	 * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
	 *
	 * @param name
	 * @return boolean
	 */
	public static boolean containsBean(String name) {
		return beanFactory.containsBean(name);
	}

	/**
	 * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。
	 * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
	 *
	 * @param name
	 * @return boolean
	 * @throws org.springframework.beans.factory.NoSuchBeanDefinitionException
	 *
	 */
	public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
		return beanFactory.isSingleton(name);
	}

	/**
	 * @param name
	 * @return Class 注册对象的类型
	 * @throws org.springframework.beans.factory.NoSuchBeanDefinitionException
	 *
	 */
	public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
		return beanFactory.getType(name);
	}

	/**
	 * 如果给定的bean名字在bean定义中有别名，则返回这些别名
	 *
	 * @param name
	 * @return
	 * @throws org.springframework.beans.factory.NoSuchBeanDefinitionException
	 *
	 */
	public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
		return beanFactory.getAliases(name);
	}
}