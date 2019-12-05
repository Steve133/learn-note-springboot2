package cn.center.service;

import org.quartz.SchedulerException;

import cn.center.enums.JobOperateEnum;
import cn.center.pojo.ScheduleJob;

public interface QuartzService {

    /**
     * @description: 服务器启动执行定时任务
     * 
     * @author song
     * @date 2019年11月19日 下午11:46:02
     */
    void timingTask();

    /**
     * @description: 新增定时任务
     * @param job
     * @author song
     * @date 2019年11月19日 下午11:46:11
     */
    void addJob(ScheduleJob job);

    /**
     * @description: 操作定时任务
     * @param jobOperateEnum
     * @param job
     * @throws SchedulerException
     * @author song
     * @date 2019年11月19日 下午11:46:17
     */
    void operateJob(JobOperateEnum jobOperateEnum, ScheduleJob job) throws SchedulerException;

    /**
     * @description: 启动所有任务
     * @throws SchedulerException
     * @author song
     * @date 2019年11月19日 下午11:46:24
     */
    void startAllJob() throws SchedulerException;

    /**
     * @description: 暂停所有任务
     * @throws SchedulerException
     * @author song
     * @date 2019年11月19日 下午11:46:34
     */
    void pauseAllJob() throws SchedulerException;
}
