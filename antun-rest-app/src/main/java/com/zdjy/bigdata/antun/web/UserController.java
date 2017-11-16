package com.zdjy.bigdata.antun.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private UserValidation userValidation;
	/**
	 * 保存数据
	 * @param userAdd
	 * @return
	 */
	@RequestMapping(value="",method=RequestMethod.POST)
	public BaseResponse saveUser(UserAdd userAdd) {
		logger.debug("【user_add】"+userAdd.toString());
		String msg=userValidation.saveUserValidation(userAdd);
		if(msg!=null)
			return errorModel(msg);
		int i=userService.saveUser(userAdd);
		return successModel("入库成功，数量："+i);
	}
	/**
	 * 保存页面过来的数据
	 * @param userAdd
	 * @param linkCode
	 * @return
	 */
	@RequestMapping(value="/fromLink",method=RequestMethod.POST)
	public BaseResponse saveUser(UserAdd userAdd,String linkCode) {
		logger.debug("【user_add】"+userAdd.toString());
		//验证补全信息
		String msg=userValidation.saveUserValidation(userAdd,linkCode);
		if(msg!=null)
			return errorModel(msg);
		int i=userService.saveUser(userAdd);
		return successModel("入库成功，数量："+i);
	}
}
