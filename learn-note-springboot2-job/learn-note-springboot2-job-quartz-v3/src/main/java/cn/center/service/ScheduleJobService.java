package cn.center.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import cn.center.pojo.ScheduleJob;

/**
 * @author song
 * @title 服务类
 * @projectName demo
 * @description TODO
 * @date 2019年11月19日 上午9:33:52
 */
public interface ScheduleJobService extends IService<ScheduleJob> {
	// 主键查询
	ScheduleJob selectByPrimaryKey(Long jobId);

	// 列表查询
	List<ScheduleJob> selectByExample(ScheduleJob entity);

	// 保存
	int insert(ScheduleJob entity);

	// 更新
	int updateByPrimaryKeySelective(ScheduleJob entity);

	// 停止
	void pauseJob(Long jobId);

	// 恢复
	void resumeJob(Long jobId);

	// 执行
	void run(Long jobId);

	// 删除
	void delete(Long jobId);
}
