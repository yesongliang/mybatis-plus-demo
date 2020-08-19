package com.kedacom.tz.sh.mapper;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kedacom.tz.sh.entity.DemoUser;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author yesongliang
 * @since 2020-08-18
 */
public interface DemoUserMapper extends BaseMapper<DemoUser> {

	int insertUser(DemoUser user);

	DemoUser selectUserById(@Param("id") long id);
}
