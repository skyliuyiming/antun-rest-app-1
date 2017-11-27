package com.zdjy.bigdata.antun.test;

import org.junit.Test;

import com.zdjy.bigdata.antun.util.DateUtil;

public class DateUtilTest {
	@Test
	public void test001() {
		System.out.println(DateUtil.getLastDay());
		System.out.println(DateUtil.getLastWeek());
		System.out.println(DateUtil.getLastMonth());
	}
}
