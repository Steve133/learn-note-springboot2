package cn.center.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.center.mapper.ScheduleJobMapper;
import cn.center.pojo.ScheduleJob;
import cn.center.service.ScheduleJobService;
import cn.center.util.ScheduleUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author song
 * @title 服务实现类
 * 
 * @date 2019年12月6日 下午11:53:02
 */
@Slf4j
@Service
@Transactional
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobMapper, ScheduleJob> implements ScheduleJobService {

	@Resource
	private Scheduler scheduler;
	@Resource
	private ScheduleJobMapper scheduleJobMapper;
	
    /**
     * @description 定时器初始化<br>
     * java的@PostConstruct注解<br>
     * 该注解被用来修饰一个非静态的void（）方法<br>
     * 被@PostConstruct修饰的方法会在服务器加载Servlet的时候运行，并且只会被服务器执行一次<br>
     * 
     * @author song
     * @date 2019年12月6日 下午11:56:49
     */
    @PostConstruct
	public void init() {
		List<ScheduleJob> ScheduleJobList = scheduleJobMapper.selectList(new QueryWrapper<>());
		for (ScheduleJob ScheduleJob : ScheduleJobList) {
			CronTrigger cronTrigger = ScheduleUtil.getCronTrigger(scheduler, ScheduleJob.getJobId());
			if (cronTrigger == null) {
				log.info("找到job:  "+ScheduleJob.getBeanName()+",创建任务");
				ScheduleUtil.createJob(scheduler, ScheduleJob);
			} else {
				log.info("找到job:  "+ScheduleJob.getBeanName()+",更新任务");
				ScheduleUtil.updateJob(scheduler, ScheduleJob);
			}
		}
	}
    
    @Override
	public ScheduleJob selectByPrimaryKey(Long jobId) {
		return scheduleJobMapper.selectById(jobId);
	}

	@Override
	public List<ScheduleJob> selectByExample(ScheduleJob entity) {
		return scheduleJobMapper.selectList(new QueryWrapper<ScheduleJob>(entity));
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insert(ScheduleJob entity) {
		ScheduleUtil.createJob(scheduler, entity);
		return scheduleJobMapper.insert(entity);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int updateByPrimaryKeySelective(ScheduleJob entity) {
		ScheduleUtil.updateJob(scheduler, entity);
		return scheduleJobMapper.updateById(entity);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void pauseJob(Long jobId) {
		ScheduleJob ScheduleJob = scheduleJobMapper.selectById(jobId);
		ScheduleUtil.pauseJob(scheduler, jobId);
		ScheduleJob.setStatus(1);
		scheduleJobMapper.updateById(ScheduleJob);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void resumeJob(Long jobId) {
		ScheduleJob ScheduleJob = scheduleJobMapper.selectById(jobId);
		ScheduleUtil.resumeJob(scheduler, jobId);
		ScheduleJob.setStatus(0);
		scheduleJobMapper.updateById(ScheduleJob);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void run(Long jobId) {
		ScheduleJob ScheduleJob = scheduleJobMapper.selectById(jobId);
		ScheduleUtil.run(scheduler, ScheduleJob);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void delete(Long jobId) {
		ScheduleUtil.deleteJob(scheduler, jobId);
		scheduleJobMapper.deleteById(jobId);
	}
}
