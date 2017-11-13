package com.zdjy.bigdata.antun.web.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.zdjy.bigdata.antun.web.model.UserAdd;

/**
 * 用户验证类
 * @author david
 * @create 2017年11月13日 上午11:56:08
 */
@Component
public class UserValidation extends BaseValidation{
	/**
	 * 保存验证
	 * @param userAdd
	 * @return
	 */
	public String saveUserValidation(UserAdd userAdd) {
		//验证参数格式
		if(StringUtils.isBlank(userAdd.getName()))
			return empty("姓名");
		if(!isName(userAdd.getName()))
			return "名字格式不正确，只能是2-5位中文";
		if(StringUtils.isBlank(userAdd.getPhone()))
			return empty("手机号");
		if(!isPhone(userAdd.getPhone()))
			return empty("手机号格式不正确，只能是11位数字");
		if(userAdd.getSex()==null)
			return empty("性别");
		if(userAdd.getSex()!=1&&userAdd.getSex()!=0)
			return "性别格式不正确，只能是0或者1";
		if(StringUtils.isBlank(userAdd.getBirth()))
			return empty("生日");
		if(isIdNo(userAdd.getIdNo()))
			return empty("身份证号格式不正确，前17位只能是数字，最后一位可以是数字和x和X");
		if(userAdd.getProvince()==null)
			return empty("省份");
		if(userAdd.getCity()==null)
			return empty("城市");
		if(userAdd.getTown()==null)
			return empty("区县");
		if(StringUtils.isBlank(userAdd.getChannelCode()))
			return empty("渠道码");
		if(StringUtils.isBlank(userAdd.getProductCode()))
			return empty("产品码");
		//验证省市县
		
		//验证渠道
		
		//验证产品
		
		//去重
				
		return null;
	}
	
	private static final String NAME_PATTERN="^[u4e00-u9fa5]{2-5}$";
	/**
	 * 验证是否是姓名
	 * @param name
	 * @return
	 */
	public boolean isName(String name) {
		return name.matches(NAME_PATTERN);
	}
	private static final String PHONE_PATTERN="^\\d{11}$";
	/**
	 * 验证是否是手机号
	 * @param phone
	 * @return
	 */
	public boolean isPhone(String phone) {
		return phone.matches(PHONE_PATTERN);
	}
	private static final String IDNO_PATTERN="^\\d{17}[0-9xX]$";
	/**
	 * 验证是否是身份证号
	 * @param idNo
	 * @return
	 */
	public boolean isIdNo(String idNo) {
		return idNo.matches(IDNO_PATTERN);
	}
}
