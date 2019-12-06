package cn.center.pojo;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.ToString;

/**
 * @author song
 * @title 定时任务日志
 * 
 * @date 2019年12月7日 上午12:32:25
 */
@Data
@ToString
public class ScheduleJobLog implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long logId;
	private Long jobId;
	private String beanName;
	private String params;
	private Integer status;
	private String error;
	private Integer times;
	private Date createTime;
}
