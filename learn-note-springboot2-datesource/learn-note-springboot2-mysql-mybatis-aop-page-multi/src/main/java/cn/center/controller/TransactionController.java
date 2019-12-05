package cn.center.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.center.pojo.CsTeacher;
import cn.center.pojo.CsTest;
import cn.center.service.TransactionTestService1;
import cn.center.service.TransactionTestService2;

@RestController
public class TransactionController {

	@Autowired
	private TransactionTestService1 ts1;
	@Autowired
	private TransactionTestService2 ts2;
	
	
	@RequestMapping("/test")
	public String savetest() {
		CsTest tb = new CsTest();
		ts1.save(tb);
		CsTeacher tb2 = new CsTeacher();
		ts2.save(tb2);
		return "success";
	}
}
