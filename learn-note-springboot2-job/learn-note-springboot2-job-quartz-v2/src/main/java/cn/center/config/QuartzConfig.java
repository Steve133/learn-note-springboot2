package cn.center.config;

import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author song
 * @title 任务调度配置
 * @projectName demo
 * @description TODO
 * @date 2019年11月19日 下午11:08:44
 */
@Configuration
public class QuartzConfig {

	/**
	 * @description: 定时器工厂配置
	 * @return
	 * @author song
	 * @date 2019年11月20日 上午12:06:09
	 */
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setOverwriteExistingJobs(true);//覆盖已存在的任务
        //schedulerFactoryBean.setStartupDelay(60);//延时60秒启动定时任务，避免系统未完全启动却开始执行定时任务的情况
        return schedulerFactoryBean;
    }

    /**
     * @description: 创建调度器schedule
     * @return
     * @author song
     * @date 2019年11月19日 下午11:48:09
     */
    @Bean(name = "scheduler")
    public Scheduler scheduler() {
        return schedulerFactoryBean().getScheduler();
    }
}
