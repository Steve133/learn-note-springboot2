package cn.center.config;

import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.center.entity.ScheduleJob;
import cn.center.task.ProfitTask;

@Configuration
public class QuartzConfig {
	
	@Bean
    public ScheduleJob scheduleJob(){
		ScheduleJob scheduleJob = new ScheduleJob();
		scheduleJob.setJobId(1L);
		scheduleJob.setBeanName("getTimeTask");
		// 每分钟执行一次
		scheduleJob.setCronExpression("0 0/1 * * * ?");
		scheduleJob.setParams("Hello,Quart-Job");
		scheduleJob.setStatus(0);
		scheduleJob.setRemark("获取时间定时器");
		scheduleJob.setCreateTime(new Date());
		return scheduleJob;
	}
	
	
    @Bean
    public JobDetail quartzDetail(){
    	ScheduleJob scheduleJob = scheduleJob();
    	JobKey jobKey = JobKey.jobKey(String.valueOf(scheduleJob.getJobId()));
    	
    	JobDetail jobDetail = JobBuilder
				.newJob(ProfitTask.class)
				.withIdentity(jobKey)
				.storeDurably()
				.build();
    	
    	jobDetail.getJobDataMap()
				.put(ScheduleJob.JOB_PARAM_KEY, scheduleJob);
        return jobDetail;
    }
    
    @Bean
    public Trigger quartzTrigger(){
    	ScheduleJob scheduleJob = scheduleJob();
    	
    	TriggerKey triggerKey = TriggerKey.triggerKey(String.valueOf(scheduleJob.getJobId()));
    	
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder
        		.simpleSchedule()
                .withIntervalInSeconds(1)  //设置时间周期单位秒
                .repeatForever();
        
        return TriggerBuilder.newTrigger()
        		.forJob(quartzDetail())
                .withIdentity(triggerKey)
                .withSchedule(scheduleBuilder)
                .build();
    }
}
