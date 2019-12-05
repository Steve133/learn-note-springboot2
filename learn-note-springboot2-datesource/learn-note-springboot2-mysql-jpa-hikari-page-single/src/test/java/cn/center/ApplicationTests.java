package cn.center;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.center.domain.CsTest;
import cn.center.domain.TestRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTests {

	@Autowired
	private TestRepository repository;

	@Test
	public void test() throws Exception {

		// 创建10条记录
		repository.save(new CsTest("AAA", 10));
		repository.save(new CsTest("BBB", 20));
		repository.save(new CsTest("CCC", 30));
		repository.save(new CsTest("DDD", 40));
		repository.save(new CsTest("EEE", 50));
		repository.save(new CsTest("FFF", 60));
		repository.save(new CsTest("GGG", 70));
		repository.save(new CsTest("HHH", 80));
		repository.save(new CsTest("III", 90));
		repository.save(new CsTest("JJJ", 100));

		// 测试findAll, 查询所有记录
		Assert.assertEquals(10, repository.findAll().size());

		// 测试findByName, 查询姓名为FFF的User
		Assert.assertEquals(60, repository.findByUserid("FFF").getScore().longValue());

		// 测试findUser, 查询姓名为FFF的User
		Assert.assertEquals(60, repository.findUser("FFF").getScore().longValue());

		// 测试findByNameAndAge, 查询姓名为FFF并且年龄为60的User
		Assert.assertEquals("FFF", repository.findByUseridAndScore("FFF", 60).getUserid());

		// 测试删除姓名为AAA的User
		repository.delete(repository.findByUserid("AAA"));

		// 测试findAll, 查询所有记录, 验证上面的删除是否成功
		Assert.assertEquals(9, repository.findAll().size());

	}


}
