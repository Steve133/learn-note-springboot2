package cn.center.task;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import cn.center.entity.ScheduleJob;

public class ProfitTask extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        
    	ScheduleJob scheduleJob = (ScheduleJob) context.getMergedJobDataMap().get(ScheduleJob.JOB_PARAM_KEY);
    	
        System.out.println("--------hello--------");
        System.out.println(scheduleJob.getJobId());
        System.out.println(scheduleJob.getBeanName());
        System.out.println(scheduleJob.getParams());
        
    }

}