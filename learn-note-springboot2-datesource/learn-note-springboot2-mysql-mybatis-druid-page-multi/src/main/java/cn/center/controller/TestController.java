package cn.center.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.center.service.TeacherService;
import cn.center.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author song
 * @title 后台管理登陆
 * @projectName demo
 * @description TODO
 * @date 2019年11月20日 下午7:27:06
 */
@Api(value="", tags="测试")
@RestController
public class TestController {
	private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TestService testService;
	@Autowired
	private TeacherService teacherService;
	
	@ApiOperation(value = "查询测试", notes = "查询测试")
	@GetMapping("/")
	public String testSelect() throws Exception {
		testService.testSelect();
		return "success";
	}
	
	@ApiOperation(value = "分页查询测试", notes = "分页查询测试")
	@GetMapping("/page")
	public String testSelectPage() throws Exception {
		testService.testSelectPage();
		return "success";
	}
	@ApiOperation(value = "分页查询测试2", notes = "分页查询测试2")
	@GetMapping("/page2")
	public String testSelectPage2() throws Exception {
		teacherService.testSelectPage();
		return "success";
	}
	
	@ApiOperation(value = "单个数据库插入回滚测试", notes = "单个数据库插入回滚测试")
	@GetMapping("/rollself")
	public String roll() throws Exception {
		testService.testRollSelf();
		return "success";
	}
	
	@ApiOperation(value = "多个数据库插入回滚测试", notes = "多个数据库插入回滚测试")
	@GetMapping("/rollmulti")
	public String rollMulti() throws Exception {
		testService.testRollMulti();
		return "success";
	}
}