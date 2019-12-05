package cn.center.framework.aspectj;

import cn.center.framework.aspectj.annotation.ServiceAccessLog;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import cn.center.framework.common.ServletUtils;
import cn.center.framework.log.ServiceAccessLogBean;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author song
 * @title 日志切面
 * @projectName demo
 * @description TODO
 * @date 2019年11月15日 下午3:36:02
 */
@Aspect
@Component
public class ServiceAccessLogAspect {

    private static final Logger serviceLogger = LoggerFactory.getLogger("sys-service");

    //  定义一个切入点，可以是一个规则表达式，比如下例中某个package下的所有函数，也可以是一个注解等
    @Pointcut("@annotation(cn.center.framework.aspectj.annotation.ServiceAccessLog)")
    public void ServiceLogPointCut() {}

    // 返回通知。在方法返回后执行，可以获得方法的返回值或对返回值做一些加工处理
    @AfterReturning(pointcut = "ServiceLogPointCut()")
    public void doAfterReturning(JoinPoint joinPoint) {
        handleLog(joinPoint);
    }

    /**
     * @description: 处理日志
     * @param joinPoint
     * @author song
     * @date 2019年11月15日 下午3:39:50
     */
    private void handleLog(JoinPoint joinPoint) {
        ServiceAccessLog log = getAnnotationLog(joinPoint);
        if(log == null) {
            return;
        }

        ServiceAccessLogBean logBean = new ServiceAccessLogBean();
        // 1.设置ServiceAccessLogBean中公共参数
        logBean.setLogId(System.currentTimeMillis());
        logBean.setAppId("DEMO-01");
        logBean.setLogTime(DateUtil.now());

        // 2.设置ServiceAccessLogBean中私有参数
        logBean.setDescription(log.description());
        logBean.setUrl(ServletUtils.getRequest().getRequestURL().toString());
        logBean.setMethod(ServletUtils.getRequest().getMethod());
        logBean.setRequestParam(ServletUtils.getRequest().getParameterMap());

        serviceLogger.info("aop服务日志："+JSON.toJSONString(logBean));
    }


    /**
     * @description: 取注解
     * @param joinPoint
     * @return
     * @author song
     * @date 2019年11月15日 下午3:40:47
     */
    private ServiceAccessLog getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(ServiceAccessLog.class);
        }
        return null;
    }
}
