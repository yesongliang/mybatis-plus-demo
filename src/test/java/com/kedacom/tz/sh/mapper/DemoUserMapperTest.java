package com.kedacom.tz.sh.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.kedacom.tz.sh.MybatisplusApplicationTest;
import com.kedacom.tz.sh.entity.DemoUser;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { MybatisplusApplicationTest.class }, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Slf4j
public class DemoUserMapperTest {

	@Autowired
	private DemoUserMapper demoUserMapper;

	@Test
	// 运行结束后会删除插入的数据
//	@Transactional
	public void insertUserTest() {
		DemoUser user = new DemoUser();
		user.setAge(27);
		user.setEmail("15570351952@163.com");
		user.setName("yesongliang");
		user.setPassword("123456");
//		int insert = demoUserMapper.insertUser(user);
		int insert = demoUserMapper.insert(user);
		log.info("insertUserTest = {}", insert);
	}

	@Test
	public void selectTest() {
//		DemoUser user = demoUserMapper.selectById(2);
		DemoUser user = demoUserMapper.selectUserById(2);
		log.info("selectTest = {}", user);
	}

	@Test
	public void deleteTest() {
		int deleteById = demoUserMapper.deleteById(1);
		log.info("deleteTest = {}", deleteById);
	}

	@Test
	public void queryTest() {
		QueryWrapper<DemoUser> queryWrapper = new QueryWrapper<>();
		queryWrapper.gt("age", 25);
		queryWrapper.orderByDesc("id");
		queryWrapper.last("limit 1");
		DemoUser selectOne = demoUserMapper.selectOne(queryWrapper);
		log.info("queryTest = {}", selectOne);
	}

	@Test
	public void pageTest() {
		QueryWrapper<DemoUser> wrapper = new QueryWrapper<>();
		wrapper.like("name", "ye").lt("age", 40);

		Page<DemoUser> page = new Page<>(1, 2);
		page.addOrder(OrderItem.desc("age"));
		demoUserMapper.selectPage(page, wrapper);

		// 因使用了typeHandler，从resultSet解析为entity会报错
//		IPage<Map<String, Object>> mapIPage = demoUserMapper.selectMapsPage(page, wrapper);
		log.info("page:{}", JSON.toJSONString(page));
		// {"current":1,"orders":[],"pages":2,"records":[{"age":27,"email":"15570351952@163.com","id":2,"name":"ysl","password":"123456"},{"age":27,"email":"15570351952@163.com","id":3,"name":"yesongliang","password":"123456"}],"searchCount":true,"size":2,"total":4}
		// 无法获取到信息
//		log.info("总页数:", page.getSize());
//		log.info("总记录数:", page.getTotal());
		List<DemoUser> records = page.getRecords();
		records.forEach(System.out::println);

	}

}
