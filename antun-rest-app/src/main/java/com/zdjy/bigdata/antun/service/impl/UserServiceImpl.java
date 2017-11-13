package com.zdjy.bigdata.antun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjy.bigdata.antun.domain.User;
import com.zdjy.bigdata.antun.mapper.UserMapper;
import com.zdjy.bigdata.antun.service.UserService;
import com.zdjy.bigdata.antun.util.TransferUtil;
import com.zdjy.bigdata.antun.web.model.UserAdd;
/**
 * 用户业务类
 * @author david
 * @create 2017年11月13日 上午11:53:31
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	/**
	 * 保存
	 * @param userAdd
	 * @return
	 */
	@Override
	public int saveUser(UserAdd userAdd) {
		User transfer = transfer(userAdd);
		return userMapper.insertSelective(transfer);
	}
	
	/**
	 * 新增类与领域类的转换
	 * 
	 * @param newsAdd
	 * @return
	 */
	public User transfer(UserAdd userAdd) {
		User user = new User();
		TransferUtil.transfer(user, userAdd);
		return user;
	}
}
