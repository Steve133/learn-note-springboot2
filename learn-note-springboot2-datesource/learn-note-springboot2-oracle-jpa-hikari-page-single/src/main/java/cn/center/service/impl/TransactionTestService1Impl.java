package cn.center.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.center.domain.CsTest;
import cn.center.domain.TestRepository;
import cn.center.service.TransactionTestService1;

@Service("transactionTestService1")
public class TransactionTestService1Impl implements TransactionTestService1 {

	@Autowired
	TestRepository repository;

	@Transactional
	@Override
	public void save(CsTest tb) {
		tb.setId(null);
		repository.save(tb);
	}

	@Override
	public List<CsTest> getTestPage(Integer pageNumber, Integer pageSize, CsTest csTest) {
		// 分页参数
        Pageable pageable = new PageRequest(pageNumber, pageSize);
        ExampleMatcher matcher = ExampleMatcher.matching();
		Example<CsTest> example = Example.of(csTest, matcher);
		repository.findAll(example, pageable);
		return null;
	}

}
