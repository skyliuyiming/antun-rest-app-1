package com.zdjy.bigdata.antun.web.validation;

public class BaseValidation {
	public static final String EMPTY="%s为空"; 
	public static final String EXIST="%s已存在"; 
	public String empty(String param) {
		return String.format(EMPTY, param);
	}
	public String exist(String param) {
		return String.format(EXIST,param);
	}
}
