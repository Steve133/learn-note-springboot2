package cn.center.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.center.mapper.datasource1.CsTestMapper;
import cn.center.pojo.CsTeacher;
import cn.center.pojo.CsTest;
import cn.center.service.TransactionTestService1;
import cn.center.service.TransactionTestService2;

@Service("transactionTestService1")
public class TransactionTestService1Impl implements TransactionTestService1 {

	@Autowired
	CsTestMapper mapper;
	@Autowired
	TransactionTestService2 transactionTestService2;
	
	@Transactional
	@Override
	public void save(CsTest tb) {
		CsTeacher tb2 = new CsTeacher();
		transactionTestService2.save(tb2);
		
		tb.setId(null);
		mapper.insert(tb);
	}

}
