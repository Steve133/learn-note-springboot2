package cn.center.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.center.pojo.ScheduleJob;
import cn.center.service.ScheduleJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author song
 * @title 前端控制器
 * 
 * @date 2019年12月6日 下午11:31:22
 */
@Api(value = "/job", tags = "job管控")
@RestController
@RequestMapping("/job")
public class ScheduleJobController {

	@Autowired
	private ScheduleJobService scheduleJobService;

	@GetMapping(value = "/hello")
	public String helloSpringBoot() {
		return "Hello quartz!";
	}
	/**
	 * @description 添加定时器
	 * @return
	 * @author song
	 * @date 2019年12月6日 下午11:32:48
	 */
	@ApiOperation(value = "添加定时器", notes = "添加定时器")
	@GetMapping("/add")
	public String add() {
		ScheduleJob scheduleJob = new ScheduleJob();
		scheduleJob.setJobId(1L);
		scheduleJob.setJobGroup("我的任务组");
		scheduleJob.setJobName("我的任务");
		scheduleJob.setBeanName("getTimeTask");
		scheduleJob.setMethodName("run");
		scheduleJob.setMethodParams("Hello,Quart-Job");
		// 每分钟执行一次
		scheduleJob.setCronExpression("0 0/1 * * * ?");
		scheduleJob.setStatus(0);
		scheduleJob.setRemark("获取时间定时器");
		scheduleJob.setCreateTime(new Date());
		scheduleJobService.insert(scheduleJob);
		return "新增定时任务成功<br>"+scheduleJob.toString();
	}
	/**
	 * @description 执行一次定时器
	 * @return
	 * @author song
	 * @date 2019年12月6日 下午11:43:48
	 */
	@ApiOperation(value = "执行一次定时器", notes = "执行一次定时器")
	@GetMapping("/start/{id}")
	public String start(@PathVariable("id") Long jobId) {
		scheduleJobService.run(jobId);
		return "启动定时任务成功";
	}
	/**
	 * @description 更新定时器
	 * @return
	 * @author song
	 * @date 2019年12月6日 下午11:45:26
	 */
	@ApiOperation(value = "更新定时器", notes = "更新定时器")
	@GetMapping("/updateJob/{id}")
	public String updateJob(@PathVariable("id") Long jobId) {
		ScheduleJob ScheduleJob = scheduleJobService.selectByPrimaryKey(jobId);
		ScheduleJob.setMethodParams("Hello,Job_Quart");
		scheduleJobService.updateByPrimaryKeySelective(ScheduleJob);
		return "更新定时任务成功";
	}
	/**
	 * @description 停止定时器
	 * @return
	 * @author song
	 * @date 2019年12月6日 下午11:46:23
	 */
	@ApiOperation(value = "停止定时器", notes = "停止定时器")
	@GetMapping("/pauseJob/{id}")
	public String pauseJob(@PathVariable("id") Long jobId) {
		scheduleJobService.pauseJob(jobId);
		return "停止定时任务成功";
	}
	/**
	 * @description 恢复定时器
	 * @return
	 * @author song
	 * @date 2019年12月6日 下午11:47:12
	 */
	@ApiOperation(value = "恢复定时器", notes = "恢复定时器")
	@GetMapping("/resumeJob/{id}")
	public String resumeJob(@PathVariable("id") Long jobId) {
		scheduleJobService.resumeJob(jobId);
		return "恢复定时任务成功";
	}
	/**
	 * @description 删除定时器
	 * @return
	 * @author song
	 * @date 2019年12月6日 下午11:47:54
	 */
	@ApiOperation(value = "删除定时器", notes = "删除定时器")
	@GetMapping("/deleteJob/{id}")
	public String deleteJob(@PathVariable("id") Long jobId) {
		scheduleJobService.delete(jobId);
		return "删除定时任务成功";
	}
}
