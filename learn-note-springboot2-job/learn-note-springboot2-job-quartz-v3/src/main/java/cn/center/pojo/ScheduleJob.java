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
	
	private Long jobId;
	private String beanName;
	private String params;
	private String cronExpression;
	private Integer status;
	private String remark;
	private Date createTime;
}
