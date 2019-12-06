package cn.center.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.center.mapper.ScheduleJobLogMapper;
import cn.center.pojo.ScheduleJobLog;
import cn.center.service.ScheduleJobLogService;

import javax.annotation.Resource;

@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogMapper, ScheduleJobLog> implements ScheduleJobLogService {

	@Resource
	private ScheduleJobLogMapper scheduleJobLogMapper;

	@Override
	public int insert(ScheduleJobLog entity) {
		return scheduleJobLogMapper.insert(entity);
	}
}
