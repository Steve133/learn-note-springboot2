package cn.center;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.center.service.RedisService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ApplicationTests {
	private static org.slf4j.Logger logger = LoggerFactory.getLogger(ApplicationTests.class);

	@Autowired
	RedisService redisService;

//	public static void main(String[] args) {
//		User user = new User(1, "张三", 18);
//		Object value = JSON.toJSON(user);
//		
//		JSON json = JSONObject.parseObject(value.toString());
//		User javaObject = JSON.toJavaObject(json , User.class);
//		logger.info("get key1 value:" + javaObject.getName());
//	}
	
	@Test
	public void test() throws Exception {
		User user = new User(1, "张三", 18);
		redisService.setValue("key1", JSON.toJSON(user).toString(),60);
		Object value = redisService.getValue("key1");
		JSON json = JSONObject.parseObject(value.toString());
		User javaObject = JSON.toJavaObject(json , User.class);
		logger.info("get key1 value:" + javaObject.getName());
	}

	static class User {
		private int id;
		private String name;
		private int age;

		public User() {
			super();
		}

		public User(int id, String name, int age) {
			super();
			this.id = id;
			this.name = name;
			this.age = age;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

	}
}
