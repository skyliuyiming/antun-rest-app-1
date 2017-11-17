package com.zdjy.bigdata.antun.test;

import org.junit.Test;

import com.zdjy.bigdata.antun.util.EsapiUtil;

public class EsapiUtilTest {
	@Test
	public void test001() {
		String encodeForSQL = EsapiUtil.sql("aa';'delete * from a';'a");
		System.out.println(encodeForSQL);
	}
}
