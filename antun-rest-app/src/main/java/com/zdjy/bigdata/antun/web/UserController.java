package com.zdjy.bigdata.antun.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zdjy.bigdata.antun.service.UserService;
import com.zdjy.bigdata.antun.web.model.UserAdd;
import com.zdjy.bigdata.antun.web.response.BaseResponse;
import com.zdjy.bigdata.antun.web.validation.UserValidation;

/**
 * 用户接口
 * @author david
 * @create 2017年11月13日 上午11:54:19
 */
@RestController
@RequestMapping("/users")
public class UserController extends BaseResponse{
	@Autowired
	private UserService userService;
	@Autowired
	private UserValidation userValidation;
	@RequestMapping(value="",method=RequestMethod.POST)
	public BaseResponse saveUser(UserAdd userAdd) {
		String msg=userValidation.saveUserValidation(userAdd);
		if(msg!=null)
			return errorModel(msg);
		int i=userService.saveUser(userAdd);
		return successModel("入库成功，数量："+i);
	}
}
