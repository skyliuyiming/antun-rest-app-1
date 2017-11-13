package com.zdjy.bigdata.antun.test;

import org.junit.Test;

import com.zdjy.bigdata.antun.web.validation.UserValidation;

public class UserValidationTest {
	@Test
	public void test001() {
		String name="杨力谋";
		boolean isName = new UserValidation().isName(name);
		System.out.println(isName);
	}
}
