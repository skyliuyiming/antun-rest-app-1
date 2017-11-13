package com.zdjy.bigdata.antun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjy.bigdata.antun.domain.User;
import com.zdjy.bigdata.antun.domain.UserExample;
import com.zdjy.bigdata.antun.domain.UserExample.Criteria;
import com.zdjy.bigdata.antun.mapper.UserMapper;
import com.zdjy.bigdata.antun.service.UserService;
import com.zdjy.bigdata.antun.util.EsapiUtil;
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

	@Override
	public User findByPhone(String phone) {
		UserExample userExample=new UserExample();
		Criteria createCriteria = userExample.createCriteria();
		createCriteria.andPhoneEqualTo(EsapiUtil.sql(phone));
		userExample.setLimit(1);
		List<User> selectByExample = userMapper.selectByExample(userExample);
		if(!selectByExample.isEmpty())
			return selectByExample.get(0);
		return null;
	}
}
