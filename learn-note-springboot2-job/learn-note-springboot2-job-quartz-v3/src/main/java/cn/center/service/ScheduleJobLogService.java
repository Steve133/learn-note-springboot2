package cn.center.service;

import com.baomidou.mybatisplus.extension.service.IService;

import cn.center.pojo.ScheduleJobLog;

public interface ScheduleJobLogService extends IService<ScheduleJobLog> {

	int insert(ScheduleJobLog entity);
}
