package cn.center.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author cjs
 * @since 2019-11-30
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("cs_test")
@ApiModel(value = "CsTest对象", description = "")
public class CsTest implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "编号")
	@TableId(value = "id", type = IdType.NONE)
	private String id;

	@ApiModelProperty(value = "用户id")
	private String userid;

	@ApiModelProperty(value = "分数")
	private Integer score;

	@ApiModelProperty(value = "班级")
	private String classid;

}
