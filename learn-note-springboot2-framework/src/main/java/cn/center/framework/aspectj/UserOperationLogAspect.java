package cn.center.framework.aspectj;

import cn.center.framework.aspectj.annotation.UserOperationLog;
import cn.center.framework.log.UserOperationLogBean;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import cn.center.framework.common.ServletUtils;

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
 * @date 2019年11月15日 下午3:35:34
 */
@Aspect
@Component
public class UserOperationLogAspect {

    private static final Logger userLogger = LoggerFactory.getLogger("sys-user");

    @Pointcut("@annotation(cn.center.framework.aspectj.annotation.UserOperationLog)")
    public void UserLogPointCut() {}

    // 返回通知。在方法返回后执行，可以获得方法的返回值或对返回值做一些加工处理
    @AfterReturning(pointcut = "UserLogPointCut()")
    public void doAfterReturning(JoinPoint joinPoint) {
        handleLog(joinPoint);
    }

    /**
     * @description: 处理日志
     * @param joinPoint
     * @author song
     * @date 2019年11月15日 下午3:41:02
     */
    private void handleLog(JoinPoint joinPoint) {
        UserOperationLog log = getAnnotationLog(joinPoint);
        if(log == null) {
            return;
        }

        UserOperationLogBean logBean = new UserOperationLogBean();
        // 1.设置UserOperationLogBean中公共参数
        logBean.setLogId(System.currentTimeMillis());
        logBean.setAppId("DEMO-01");
        logBean.setLogTime(DateUtil.now());

        // 2.设置UserOperationLogBean中私有参数
        logBean.setOperateType(log.operateType().getValue());
        logBean.setModuleInfo(log.moduleInfo());
        logBean.setParamsObj(ServletUtils.getRequest().getParameterMap());

        userLogger.info("aop用户日志："+JSON.toJSONString(logBean));
    }


    /**
     * @description: 取注解
     * @param joinPoint
     * @return
     * @author song
     * @date 2019年11月15日 下午3:41:09
     */
    private UserOperationLog getAnnotationLog(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();

        if (method != null) {
            return method.getAnnotation(UserOperationLog.class);
        }
        return null;
    }
}
