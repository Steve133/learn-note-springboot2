package cn.center.util;

import java.lang.reflect.Method;
import java.util.Date;

import org.quartz.JobExecutionContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

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

	@Override
	protected void executeInternal(JobExecutionContext context) {
		ScheduleJob jobBean = new ScheduleJob();
		BeanUtils.copyBeanProp(jobBean, context.getMergedJobDataMap().get(ScheduleJob.JOB_PARAM_KEY));

		ScheduleJobLogService scheduleJobLogService = (ScheduleJobLogService) SpringContextUtil.getBean("scheduleJobLogService");
		// 定时器日志记录
		ScheduleJobLog logBean = new ScheduleJobLog();
		logBean.setJobId(jobBean.getJobId());
		logBean.setBeanName(jobBean.getBeanName());
		logBean.setParams(jobBean.getParams());
		logBean.setCreateTime(new Date());
		long beginTime = System.currentTimeMillis();
		try {
			// 加载并执行定时器的 run 方法
			Object target = SpringContextUtil.getBean(jobBean.getBeanName());
			Method method = target.getClass().getDeclaredMethod("run", String.class);
			method.invoke(target, jobBean.getParams());
			long executeTime = System.currentTimeMillis() - beginTime;
			logBean.setTimes((int) executeTime);
			logBean.setStatus(0);
			log.info("定时器 === >> " + jobBean.getJobId() + "执行成功,耗时 === >> " + executeTime);
		} catch (Exception e) {
			// 异常信息
			long executeTime = System.currentTimeMillis() - beginTime;
			logBean.setTimes((int) executeTime);
			logBean.setStatus(1);
			logBean.setError(e.getMessage());
		} finally {
			scheduleJobLogService.insert(logBean);
		}
	}
}
