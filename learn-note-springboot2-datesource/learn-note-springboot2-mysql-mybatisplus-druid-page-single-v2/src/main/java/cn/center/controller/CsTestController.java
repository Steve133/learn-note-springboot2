package cn.center.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import cn.center.mapper.CsTestMapper;
import cn.center.pojo.CsTest;
import cn.center.service.CsTestService;
import cn.hutool.core.lang.UUID;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author cjs
 * @since 2019-11-30
 */
@RestController
@RequestMapping("/cs-test")
public class CsTestController {

	@Autowired
	CsTestService csTestService;
	@Autowired
	CsTestMapper csTestMapper;
	
	@RequestMapping("/queryPage")
    public void queryPage (){
		CsTest csTest = new CsTest();
		csTest.setId("11e675a9fed74ae88a0735d533ef1ca3");
		csTest.setClassid("1");
		csTest.setScore(99);
		csTest.setUserid("1111111");
		int page = 0;
		int limit = 3;
        csTestService.getPage(csTest, page, limit);
    }
	
	@RequestMapping("/select")
	public void select (){
		CsTest csTest = new CsTest();
		csTest.setId("11e675a9fed74ae88a0735d533ef1ca3");
		csTest.setClassid("1");
		csTest.setScore(99);
		csTest.setUserid("1111111");
		CsTest selectOne = csTestMapper.selectOne(new QueryWrapper<CsTest>(csTest));
		System.out.println(selectOne.toString());
	}
	
	@RequestMapping("/insert")
	public void insert(){
		CsTest csTest = new CsTest();
		csTest.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		csTest.setClassid("1");
		csTest.setScore(99);
		csTest.setUserid("99999");
		int insert = csTestMapper.insert(csTest);
		System.out.println(insert);
	}
	
	@RequestMapping("/delete")
	public void delete (){
		int deleteById = csTestMapper.deleteById("17839b4f9d7b4a5fa21e3a2e3cad724a");
		System.out.println(deleteById);
	}
	
	@RequestMapping("/update")
	public void update (){
		CsTest csTest = new CsTest();
		csTest.setId("11e675a9fed74ae88a0735d533ef1ca3");
		csTest.setClassid("1");
		csTest.setScore(99);
		csTest.setUserid("99999");
		int updateById = csTestMapper.updateById(csTest);
		System.out.println(updateById);
	}
}
