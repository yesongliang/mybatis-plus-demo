package com.kedacom.tz.sh.mapper;

import java.util.ArrayList;
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

		Page<DemoUser> page = new Page<>(5000, 15);
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

	/**
	 * Mysql的分页查询十分简单，但是当数据量大的时候一般的分页就吃不消了。
	 * 
	 * 传统分页查询：SELECT c1,c2,cn... FROM table LIMIT n,m
	 * 
	 * MySQL的limit工作原理就是先读取前面n条记录，然后抛弃前n条，读后面m条想要的，所以n越大，偏移量越大，性能就越差。
	 * 
	 */
	@Test
	public void limitTest() {
		// 1、尽量给出查询的大致范围
		// SELECT c1,c2,cn... FROM table WHERE id>=20000 LIMIT 10;

		// 2、子查询法
		// SELECT c1,c2,cn... FROM table WHERE id>=(SELECT id FROM table LIMIT
		// 20000,1)LIMIT 10;

		// 3、高性能MySQL一书中提到的只读索引方法
		// 优化前SQL:
		// SELECT c1,c2,cn... FROM member ORDER BY last_active LIMIT 50,5
		// 优化后SQL:
		// SELECT c1, c2, cn ... FROM member INNER JOIN (SELECT member_id FROM member
		// ORDER BY last_active LIMIT 50, 5) USING (member_id)
		// 区别在于，优化前的SQL需要更多I/O浪费，因为先读索引，再读数据，然后抛弃无需的行。而优化后的SQL(子查询那条)只读索引(Cover
		// index)就可以了，然后通过member_id读取需要的列

		// 4、第一步用用程序读取出ID，然后再用IN方法读取所需记录
		// SELECT id FROM table LIMIT 20000, 10;
		// SELECT c1, c2, cn ... FROM table WHERE id IN (id1, id2, idn.. .)
		// TODO 碰到带where条件的考虑使用复合索引
		QueryWrapper<DemoUser> wrapper = new QueryWrapper<>();
		wrapper.select("id");
		Page<DemoUser> page = new Page<>(5000, 15);
		demoUserMapper.selectPage(page, wrapper);
		List<DemoUser> records = page.getRecords();
		List<Long> idList = new ArrayList<>();
		records.forEach(record -> idList.add(record.getId()));
		List<DemoUser> selectBatchIds = demoUserMapper.selectBatchIds(idList);
		page.setRecords(selectBatchIds);
		log.info("page:{}", JSON.toJSONString(page));
	}

}
