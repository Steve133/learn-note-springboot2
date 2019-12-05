package cn.center.framework.log;

/**
 * @author song
 * @title 接口服务日志实体类
 * @projectName demo
 * @description TODO
 * @date 2019年11月15日 下午3:42:06
 */
public class ServiceAccessLogBean extends BaseLogBean {

    // 服务描述信息
    private String description;

    // 访问url
    private String url;

    // 请求方法
    private String method;

    // http请求参数
    private Object requestParam;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(Object requestParam) {
        this.requestParam = requestParam;
    }
}
