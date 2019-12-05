package cn.center.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.center.mapper.datasource2.CsTeacherMapper;
import cn.center.pojo.CsTeacher;
import cn.center.service.TransactionTestService2;

@Service("transactionTestService2")
public class TransactionTestService2Impl implements TransactionTestService2 {

	@Autowired
	CsTeacherMapper mapper;
	
	@Transactional
	@Override
	public void save(CsTeacher tb) {
		tb.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		tb.setClassid("1");
		tb.setTeachername("张三");
		mapper.insert(tb);
	}

}
