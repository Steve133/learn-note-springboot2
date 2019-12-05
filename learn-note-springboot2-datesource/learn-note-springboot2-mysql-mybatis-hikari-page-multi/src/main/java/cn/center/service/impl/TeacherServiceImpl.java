package cn.center.service.impl;

import java.util.List;
import java.util.UUID;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.center.mapper.two.CsTeacherMapper;
import cn.center.pojo.CsTeacher;
import cn.center.pojo.CsTeacherExample;
import cn.center.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {
	private org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CsTeacherMapper mapper;

	@Override
	public void testSelectPage() throws Exception {
		logger.info("test_page...");
		PageHelper.startPage(1, 3);
		CsTeacherExample example = new CsTeacherExample();
		List<CsTeacher> ret = mapper.selectByExample(example);
		for (CsTeacher CsTeacher : ret) {
			logger.info(CsTeacher.getId());
		}
		
		// 取分页结果
		PageInfo<CsTeacher> pageInfo = new PageInfo<>(ret);
		// 取总记录数
		long total = pageInfo.getTotal();
		logger.info("总记录数:"+total);
	}
	
	@Transactional("transactionManagerTwo")
	@Override
	public void testRoll() throws Exception {
		logger.info("test_roll...");
		CsTeacher tb = new CsTeacher();
		String id = UUID.randomUUID().toString().replaceAll("-", "");
		logger.info("插入id："+id);
		tb.setId(id);
		tb.setClassid("1");
		tb.setTeachername("张三");
		mapper.insert(tb);
		int i = 1/0;
	}
	@Override
	public void testSelect() throws Exception {
		logger.info("test_query...");
		CsTeacherExample example = new CsTeacherExample();
		List<CsTeacher> ret = mapper.selectByExample(example);
		for (CsTeacher CsTeacher : ret) {
			logger.info(CsTeacher.getId());
		}
		return;
	}
}
