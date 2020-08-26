package com.kedacom.tz.sh.datasource.more.ds2.dao;

import org.apache.ibatis.annotations.Param;

import com.kedacom.tz.sh.entity.DemoUser;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author yesongliang
 * @since 2020-08-18
 */
public interface Ds2UserMapper {

	int insertUser(DemoUser user);

	DemoUser selectUserById(@Param("id") long id);
}
