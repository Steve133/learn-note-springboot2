package cn.center.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.center.domain.CsTest;
import cn.center.service.TransactionTestService1;

@Service("transactionTestService1")
public class TransactionTestService1Impl implements TransactionTestService1 {

	@Autowired
	JdbcTemplate primaryJdbcTemplate;

	@Transactional
	@Override
	public void save(CsTest tb) {
		tb.setId(null);
		Integer queryForObject = primaryJdbcTemplate.queryForObject("select count(*) from cs_test", Integer.class);
		System.out.println(queryForObject);
	}


}
