package cn.center.framework.exception;

import cn.center.framework.common.RestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author song
 * @title 全局异常拦截器
 * @projectName demo
 * @description TODO
 * @date 2019年11月15日 下午3:42:31
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * @description: 拦截运行时异常
     * @param [e]
     * @return com.run.framework.common.RestResult
     * @author YangFan
     * @date 2019/5/21 11:00
     */
    @ExceptionHandler(RuntimeException.class)
    public RestResult notFount(RuntimeException e) {
        log.error(e.getMessage(), e);
        return RestResult.error(e.getMessage());
    }


    /**
     * @description: 拦截其它异常
     * @param [e]
     * @return com.run.framework.common.RestResult
     * @author YangFan
     * @date 2019/5/21 10:59
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RestResult handleException(Exception e) {
        log.error(e.getMessage(), e);
        return RestResult.error(e.getMessage());
    }

}
