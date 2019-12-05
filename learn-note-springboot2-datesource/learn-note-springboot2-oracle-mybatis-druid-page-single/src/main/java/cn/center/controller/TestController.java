package cn.center.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	@ApiOperation(value = "查询测试", notes = "查询测试")
	@GetMapping("/")
	public String testSelect() throws Exception {
		testService.testSelect();
		return "success";
	}
	@ApiOperation(value = "插入测试", notes = "插入测试")
	@GetMapping("/insert")
	public String testInsert() throws Exception {
		testService.testInsert();
		return "success";
	}
	
	@ApiOperation(value = "分页查询测试", notes = "分页查询测试")
	@GetMapping("/page")
	public String testSelectPage() throws Exception {
		testService.testSelectPage();
		return "success";
	}
	
	@ApiOperation(value = "回滚查询测试", notes = "回滚查询测试")
	@GetMapping("/roll")
	public String roll() throws Exception {
		testService.testRoll();
		return "success";
	}
}