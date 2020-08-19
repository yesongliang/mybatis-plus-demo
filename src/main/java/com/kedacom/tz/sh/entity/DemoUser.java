package com.kedacom.tz.sh.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.kedacom.tz.sh.typeHandlers.PasswordTypeHandler;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author yesongliang
 * @since 2020-08-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "DemoUser对象", description = "")
@TableName(autoResultMap = true)
public class DemoUser extends Model<DemoUser> {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键ID")
	@TableId(value = "id", type = IdType.AUTO)
	private Long id;

	@ApiModelProperty(value = "姓名")
	private String name;

	@ApiModelProperty(value = "年龄")
	private Integer age;

	@ApiModelProperty(value = "邮箱")
	private String email;

	@TableField(typeHandler = PasswordTypeHandler.class)
	@ApiModelProperty(value = "密码")
	private String password;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
