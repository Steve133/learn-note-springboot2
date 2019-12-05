package cn.center.framework.aspectj.annotation;

import java.lang.annotation.*;

/**
 * @author song
 * @title 接口服务日志
 * @projectName demo
 * @description TODO
 * @date 2019年11月15日 下午3:41:18
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ServiceAccessLog {

    // 服务描述信息
    public String description();
}
