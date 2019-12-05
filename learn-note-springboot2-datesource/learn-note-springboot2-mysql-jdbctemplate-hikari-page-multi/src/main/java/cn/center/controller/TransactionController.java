package cn.center.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.center.domain.CsTest;
import cn.center.service.TransactionTestService1;

@RestController
public class TransactionController {

	@Autowired
	private TransactionTestService1 ts1;
	
	
	@RequestMapping("/test")
	public String savetest() {
		
		ts1.save(new CsTest());
		return "success";
	}
	
}
