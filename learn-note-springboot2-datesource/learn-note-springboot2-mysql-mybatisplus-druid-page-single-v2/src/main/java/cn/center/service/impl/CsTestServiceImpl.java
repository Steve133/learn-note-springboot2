package cn.center.service.impl;

import cn.center.pojo.CsTest;
import cn.center.mapper.CsTestMapper;
import cn.center.service.CsTestService;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cjs
 * @since 2019-11-30
 */
@Service
public class CsTestServiceImpl extends ServiceImpl<CsTestMapper, CsTest> implements CsTestService {

	@Autowired
	CsTestMapper csTestMapper;
	
	@Override
	public void getPage(CsTest csTest ,int page , int limit) {
		IPage<CsTest> iPage = this.page(new Page(page, limit), new QueryWrapper<>(csTest));
		
//		IPage<CsTest> selectPage = csTestMapper.selectPage(new Page<>(page, limit), new QueryWrapper<>(csTest));
		
		System.out.println(iPage);
		List<CsTest> records = iPage.getRecords();
		for(CsTest test : records) {
			System.out.println(test.toString());
		}
		System.out.println(iPage.getTotal());
		
	}
}
