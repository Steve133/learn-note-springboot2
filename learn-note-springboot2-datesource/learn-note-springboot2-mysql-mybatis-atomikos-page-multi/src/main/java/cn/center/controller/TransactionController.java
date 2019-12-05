package cn.center.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.center.pojo.CsTest;
import cn.center.service.TransactionTestService1;

@RestController
public class TransactionController {

	@Autowired
	private TransactionTestService1 ts1;
	
	
	@RequestMapping("/test")
	public String savetest() {
		CsTest tb = new CsTest();
		tb.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		tb.setClassid("1");
		tb.setScore(99);
		tb.setUserid("1111111");
		ts1.save(tb);
		return "success";
	}
}
