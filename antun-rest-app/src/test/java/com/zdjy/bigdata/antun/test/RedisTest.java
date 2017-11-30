package com.zdjy.bigdata.antun.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.zdjy.bigdata.antun.domain.Area;
import com.zdjy.bigdata.antun.domain.User;
import com.zdjy.bigdata.antun.redis.RedisService;
import com.zdjy.bigdata.antun.service.AreaService;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.zdjy.bigdata.antun.mapper")
public class RedisTest {
	@Autowired
	private RedisService redisService;
	@Autowired
	private AreaService areaService;
	@Test
	public void test001() {
		redisService.set("aa","aa");
		String string = redisService.get("aa",String.class);
		System.out.println(string);
		User user = new User();
		user.setId(1L);
		redisService.set("aa", user);
		User user2 = redisService.get("aa",User.class);
		System.out.println(user2.getId());
		User user3 = redisService.get("bb",User.class);
		System.out.println(user3);
		
	}
	@Test
	public void test002() {
		Area findArea = areaService.findArea(110000L);
		redisService.set("area_findArea_"+1,findArea,3600*24);
		Area area2 = redisService.get("area_findArea_"+1,Area.class);
		System.out.println(new Gson().toJson(area2));
		List<Area> findByParentId = areaService.findByParentId(0L);
		redisService.set("area_findByParentId_"+0,findByParentId,3600*24);
		@SuppressWarnings("unchecked")
		List<Area> list = redisService.get("area_findByParentId_"+0, List.class);
		System.out.println(new Gson().toJson(list));
	}
	@Test
	public void test003() {
		redisService.set("aa1","aa");
		redisService.set("aa2","aa");
		redisService.set("aa3","aa");
		redisService.set("aa4","aa");
		redisService.set("aa5","aa");
		redisService.delKeys("aa*");
	}
}
