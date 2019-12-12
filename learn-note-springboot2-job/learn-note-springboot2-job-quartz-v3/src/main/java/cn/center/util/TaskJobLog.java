package cn.center.util;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.Future;

import org.quartz.JobExecutionContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.QuartzJobBean;

import cn.center.enums.JobStatusEnum;
import cn.center.pojo.ScheduleJob;
import cn.center.pojo.ScheduleJobLog;
import cn.center.service.ScheduleJobLogService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author song
 * @title 定时器执行日志记录
 * 
 * @date 2019年12月7日 上午12:16:36
 */
@Slf4j
public class TaskJobLog extends QuartzJobBean {

	private ThreadPoolTaskExecutor executor = SpringContextUtil.getBean("threadPoolTaskExecutor");
	private final static ScheduleJobLogService JobLogService = SpringContextUtil.getBean(ScheduleJobLogService.class);
	
	@Override
	protected void executeInternal(JobExecutionContext context) {
		ScheduleJob scheduleJob = new ScheduleJob();
		BeanUtils.copyBeanProp(scheduleJob, context.getMergedJobDataMap().get(ScheduleJob.JOB_PARAM_KEY));

		// 定时器日志记录
		ScheduleJobLog jobLog = new ScheduleJobLog();
		jobLog.setJobId(scheduleJob.getJobId());
		jobLog.setBeanName(scheduleJob.getBeanName());
		jobLog.setParams(scheduleJob.getMethodParams());
		jobLog.setCreateTime(new Date());
		
		long beginTime = System.currentTimeMillis();
		
		try {
			// 加载并执行定时器的 run 方法
			log.info("任务开始执行 - 名称：{} 方法：{}", scheduleJob.getBeanName(), scheduleJob.getMethodName());
			ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(), scheduleJob.getMethodName(), scheduleJob.getMethodParams());
			Future<?> future = executor.submit(task);
			future.get();
			
			long times = System.currentTimeMillis() - beginTime;
			// 任务状态 0：成功 1：失败
			jobLog.setStatus(JobStatusEnum.SUCCESS.getValue());
			jobLog.setTimes((int) times);
			log.info("定时器 === >> " + scheduleJob.getJobId() + "执行成功,耗时 === >> " + times);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			// 异常信息
			long executeTime = System.currentTimeMillis() - beginTime;
			jobLog.setTimes((int) executeTime);
			jobLog.setStatus(JobStatusEnum.FAIL.getValue());
			jobLog.setError(e.getMessage());
		} finally {
			JobLogService.insert(jobLog);
		}
	}
}
