package cn.center.service1.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.center.mapper.datasource1.CsTestMapper;
import cn.center.pojo.CsTest;
import cn.center.service.TransactionTestService1;

@Service("transactionTestService1")
public class TransactionTestService1Impl implements TransactionTestService1 {

	@Autowired
	CsTestMapper mapper;
	
	@Transactional
	@Override
	public void save(CsTest tb) {
		tb.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		tb.setClassid("1");
		tb.setScore(99);
		tb.setUserid("1111111");
		mapper.insert(tb);
	}

}
