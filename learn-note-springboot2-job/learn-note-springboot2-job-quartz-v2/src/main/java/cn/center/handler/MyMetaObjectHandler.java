package cn.center.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * @author song
 * @title 自定义填充处理器<br>
 *        mybatisPlus是在2.0.6版本才支持
 * @projectName demo
 * @description TODO
 * @date 2019年11月19日 上午9:39:02
 */
public class MyMetaObjectHandler implements MetaObjectHandler {

	// 新增填充
	@Override
	public void insertFill(MetaObject metaObject) {
		System.out.println("新增方法实体填充");
		LocalDateTime now = LocalDateTime.now();
		this.setFieldValByName("creatorId", 1, metaObject);
		this.setFieldValByName("creatorName", "admin", metaObject);
		this.setFieldValByName("createdTime", now, metaObject);
		this.setFieldValByName("deleteFlag", false, metaObject);
	}

	// 更新填充
	@Override
	public void updateFill(MetaObject metaObject) {
		System.out.println("更新方法实体填充");
		this.setFieldValByName("updatedTime", LocalDateTime.now(), metaObject);
	}
}
