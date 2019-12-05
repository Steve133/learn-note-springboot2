package cn.center.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.center.domain.CsTest;
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
	
	@RequestMapping(value = "/test/page", method = RequestMethod.GET)
	public List<CsTest> searchCity(@RequestParam(value = "pageNumber") Integer pageNumber, @RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestBody CsTest csTest) {
		return ts1.getTestPage(pageNumber, pageSize, csTest);
	}
	
}
