package cn.center.service.impl;

import java.util.List;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.center.mapper.one.CsTestMapper;
import cn.center.pojo.CsTest;
import cn.center.pojo.CsTestExample;
import cn.center.service.TestService2;

@Service
public class TestServiceImpl2 implements TestService2 {
	private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CsTestMapper mapper;

	@Override
	public void testSelectPage() throws Exception {
		logger.info("test_page...");
		PageHelper.startPage(1, 3);
		CsTestExample example = new CsTestExample();
		List<CsTest> ret = mapper.selectByExample(example);
		for (CsTest csTest : ret) {
			logger.info(csTest.getId());
		}
		
		// 取分页结果
		PageInfo<CsTest> pageInfo = new PageInfo<>(ret);
		// 取总记录数
		long total = pageInfo.getTotal();
		logger.info("总记录数:"+total);
	}
	
	@Transactional
	@Override
	public void testRoll() throws Exception {
		logger.info("test_roll...");
		CsTest tb = new CsTest();
		String id = UUID.randomUUID().toString().replaceAll("-", "");
		logger.info("插入id："+id);
		tb.setId(id);
		tb.setClassid("1");
		tb.setScore(99);
		tb.setUserid("1111111");
		mapper.insert(tb);
		int i = 1/0;
	}
	
	@Override
	public void testSelect() throws Exception {
		logger.info("test_query...");
		CsTestExample example = new CsTestExample();
		List<CsTest> ret = mapper.selectByExample(example);
		for (CsTest csTest : ret) {
			logger.info(csTest.getId());
		}
		return;
	}
}
