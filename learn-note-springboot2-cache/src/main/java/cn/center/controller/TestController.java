package cn.center.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	@Autowired
	private TestService testService;
	
	@ApiOperation(value = "查询测试", notes = "查询测试")
	@GetMapping("/{id}")
	public String testSelect(@PathVariable String id) throws Exception {
		return testService.testCache(id);
	}
	@ApiOperation(value = "查询测试", notes = "查询测试")
	@GetMapping("/put/{id}")
	public String testCachePut(@PathVariable String id) throws Exception {
		return testService.testCachePut(id);
	}
	@ApiOperation(value = "查询测试", notes = "查询测试")
	@GetMapping("/remove/{id}")
	public void removeCache(@PathVariable String id) throws Exception {
		testService.removeCache(id);
	}
}