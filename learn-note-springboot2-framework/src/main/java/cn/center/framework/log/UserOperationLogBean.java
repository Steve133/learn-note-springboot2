package cn.center.framework.log;

/**
 * @author song
 * @title 用户操作日志实体类
 * @projectName demo
 * @description TODO
 * @date 2019年11月15日 下午3:42:13
 */
public class UserOperationLogBean extends BaseLogBean {

    // 操作类型
    private Integer operateType;

    // 操作模块信息
    private String moduleInfo;

    // 操作参数
    private Object paramsObj;

    public Integer getOperateType() {
        return operateType;
    }

    public void setOperateType(Integer operateType) {
        this.operateType = operateType;
    }

    public String getModuleInfo() {
        return moduleInfo;
    }

    public void setModuleInfo(String moduleInfo) {
        this.moduleInfo = moduleInfo;
    }

    public Object getParamsObj() {
        return paramsObj;
    }

    public void setParamsObj(Object paramsObj) {
        this.paramsObj = paramsObj;
    }
}
