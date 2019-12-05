package cn.center.service;

import cn.center.pojo.CsTest;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author cjs
 * @since 2019-11-30
 */
public interface CsTestService extends IService<CsTest> {

	void getPage(CsTest csTest, int page, int limit);

}
