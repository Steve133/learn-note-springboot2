package cn.center.pojo;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class ScheduleJob implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String JOB_PARAM_KEY = "JOB_PARAM_KEY";

	/** 任务ID */
	private Long jobId;
	/** 任务名称 */
	private String jobName;
	/** 任务组名 */
	private String jobGroup;
	/** 实体名 */
	private String beanName;
	/** 任务方法 */
	private String methodName;
	/** 方法参数 */
	private String methodParams;
	/** cron执行表达式 */
	private String cronExpression;
	/** 任务状态 0：正常 1：暂停 */
	private Integer status;
	private String remark;
	private Date createTime;
}
