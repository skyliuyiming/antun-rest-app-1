package com.zdjy.bigdata.antun.web.validation;

import org.apache.commons.lang3.StringUtils;

public class BaseValidation {
	public static final String EMPTY="%s为空"; 
	public static final String EXIST="%s已存在"; 
	public static final String NOT_EXIST="%s不存在"; 
	public String empty(String param) {
		return String.format(EMPTY, param);
	}
	public String exist(String param) {
		return String.format(EXIST,param);
	}
	public String notExist(String param) {
		return String.format(NOT_EXIST,param);
	}
	/**
	 * 修改状态验证
	 * @param status
	 * @return
	 */
	public String updateStatusValidation(Integer status) {
		if(status==null)
			return empty("状态");
		if(status!=1&& status!=0)
			return "状态异常";
		return null;
	}
	public String codeValidation(String code) {
		if(StringUtils.isBlank(code))
			return empty("编码");
		return null;
	}
}
