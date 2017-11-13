package com.zdjy.bigdata.antun.web.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.zdjy.bigdata.antun.web.model.HelloAdd;
import com.zdjy.bigdata.antun.web.model.HelloUpdate;

/**
 * hello验证类
 * @author david
 * @create 2017年11月13日 上午10:27:18
 */
@Component
public class HelloValidation extends BaseValidation{

	/**
	 * 保存验证
	 * @param helloAdd
	 * @return
	 */
	public String saveHelloValidation(HelloAdd helloAdd) {
		if(StringUtils.isBlank(helloAdd.getName()))
			return empty("name");
		return null;
	}

	/**
	 * 修改验证
	 * @param helloUpdate
	 * @return
	 */
	public String updateHelloValidation(HelloUpdate helloUpdate) {
		if(StringUtils.isBlank(helloUpdate.getName()))
			return empty("name");
		return null;
	}
	
}
