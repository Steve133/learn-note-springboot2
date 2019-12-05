package cn.center.framework.log;

import java.io.Serializable;

/**
 * @author song
 * @title 日志实体类基类-存放公共字段
 * @projectName demo
 * @description TODO
 * @date 2019年11月15日 下午3:41:59
 */
public abstract class BaseLogBean implements Serializable {
    // 日志流水号
    private Long logId;
    // 日志记录时间
    private String logTime;
    // 用户ID
    private String userId;
    // 用户名
    private String userName;
    // 用户所属组织机构
    private String organization;
    // 终端ID
    private String terminalId;
    // 来访应用ID
    private String appId;
    // 来访应用版本
    private String appVersion;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }
}
