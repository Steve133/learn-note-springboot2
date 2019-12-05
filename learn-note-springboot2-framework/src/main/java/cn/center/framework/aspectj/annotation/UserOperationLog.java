package cn.center.framework.aspectj.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import cn.center.framework.enums.OperateType;

/**
 * @author song
 * @title 用户操作日志
 * @projectName demo
 * @description TODO
 * @date 2019年11月15日 下午3:41:28
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UserOperationLog {

    // 操作类型
    public OperateType operateType();

    // 操作模块
    public String moduleInfo();

}
