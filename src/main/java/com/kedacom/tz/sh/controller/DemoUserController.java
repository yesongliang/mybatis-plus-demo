package com.kedacom.tz.sh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kedacom.tz.sh.datasource.more.ds1.dao.Ds1UserMapper;
import com.kedacom.tz.sh.datasource.more.ds2.dao.Ds2UserMapper;
import com.kedacom.tz.sh.entity.DemoUser;
import com.kedacom.tz.sh.mapper.DemoUserMapper;
import com.kedacom.tz.sh.service.DemoUserService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yesongliang
 * @since 2020-08-18
 */
@RestController
@RequestMapping("/demo-user")
@Slf4j
public class DemoUserController {

	@Autowired
	private DemoUserService demoUserService;

//	@Autowired
//	private DemoUserMapper demoUserMapper;

//	@Autowired
//	private Ds1UserMapper ds1UserMapper;
//
//	@Autowired
//	private Ds2UserMapper ds2UserMapper;

	@RequestMapping("/queryById/{id}")
	public DemoUser queryById(@PathVariable("id") Integer id) {
		log.info("使用mybatisplus默认数据源");
		return demoUserService.getById(id);
	}

//	@RequestMapping("/query1ById/{id}")
//	public DemoUser query1ById(@PathVariable("id") Integer id) {
//		log.info("使用mybatisplus自定义数据源1");
//		return ds1UserMapper.selectUserById(id);
//	}
//
//	@RequestMapping("/query2ById/{id}")
//	public DemoUser query2ById(@PathVariable("id") Integer id) {
//		log.info("使用mybatisplus自定义数据源2");
//		return ds2UserMapper.selectUserById(id);
//	}

//	@RequestMapping("/query_ById/{id}")
//	public DemoUser query_ById(@PathVariable("id") Integer id) {
//		log.info("使用mybatisplus主从数据源");
//		return demoUserMapper.selectUserById(id);
//	}

}
