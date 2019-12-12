package cn.center.framework.log;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 * @author song
 * @title 日志实体类基类-存放公共字段
 * @projectName demo
 * @description TODO
 * @date 2019年11月15日 下午3:41:59
 */
@Data
@ToString
public abstract class BaseLogBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
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
}
