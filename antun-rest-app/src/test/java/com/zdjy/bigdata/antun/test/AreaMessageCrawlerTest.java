package com.zdjy.bigdata.antun.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * 平安地区信息爬取测试
 * @author david
 * @create 2017年11月13日 下午4:13:09
 */
public class AreaMessageCrawlerTest {
	@Test
	public void test001() {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<AreaMessage[]> areaMessages=restTemplate.getForEntity("http://47.94.250.65/pinganjiekou/area/0",AreaMessage[].class);
		System.out.println(areaMessages.getBody().length);
		for(AreaMessage areaMessage:areaMessages.getBody()) {
			System.out.println(areaMessage);
		}
	}
	public List<AreaMessage> test002(Integer parentAreaId) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<AreaMessage[]> areaMessages=restTemplate.getForEntity("http://47.94.250.65/pinganjiekou/area/"+parentAreaId,AreaMessage[].class);
		return Arrays.asList(areaMessages.getBody());
	}
	@Test
	public void test003() {
		List<AreaMessage> provinces = test002(0);
		for(AreaMessage province:provinces) {
			System.out.println(province);
			List<AreaMessage> citys = test002(province.getAreaId());
			for(AreaMessage city:citys) {
				System.out.println(city);
				List<AreaMessage> towns = test002(city.getAreaId());
				for(AreaMessage town:towns) {
					System.out.println(town);
				}
			}
		}
	}
	@Test
	public void test004() throws Exception {
		BufferedWriter writer=new BufferedWriter(new FileWriter("doc/text/area.txt"));
		List<AreaMessage> provinces = test002(0);
		for(AreaMessage province:provinces) {
			writer.write(province.toString());
			writer.newLine();
			List<AreaMessage> citys = test002(province.getAreaId());
			for(AreaMessage city:citys) {
				writer.write(city.toString());
				writer.newLine();
				List<AreaMessage> towns = test002(city.getAreaId());
				for(AreaMessage town:towns) {
					writer.write(town.toString());
					writer.newLine();
				}
			}
			writer.flush();
		}
		writer.close();
	}
	
}
