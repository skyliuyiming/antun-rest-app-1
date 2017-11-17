package com.zdjy.bigdata.antun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjy.bigdata.antun.domain.UserSending;
import com.zdjy.bigdata.antun.domain.UserSendingExample;
import com.zdjy.bigdata.antun.domain.UserSendingExample.Criteria;
import com.zdjy.bigdata.antun.mapper.UserSendingMapper;
import com.zdjy.bigdata.antun.service.UserSendingService;
import com.zdjy.bigdata.antun.util.EsapiUtil;

/**
 * 数据发送业务类
 * @author david
 * @create 2017年11月14日 下午3:15:33
 */
@Service
public class UserSendingServiceImpl implements UserSendingService {
	@Autowired
	private UserSendingMapper userSendingMapper;

	/**
	 * 保存
	 * @param userSending
	 * @return
	 */
	@Override
	public int saveUserSending(UserSending userSending) {
		return userSendingMapper.insertSelective(userSending);
	}

	/**
	 * 用户编码查询
	 * @param userCode
	 * @return
	 */
	@Override
	public UserSending findByUserCode(String userCode) {
		UserSendingExample userSendingExample=new UserSendingExample();
		Criteria createCriteria = userSendingExample.createCriteria();
		createCriteria.andUserCodeEqualTo(EsapiUtil.sql(userCode));
		userSendingExample.setLimit(1);
		List<UserSending> selectByExample = userSendingMapper.selectByExample(userSendingExample);
		if(!selectByExample.isEmpty())
			return selectByExample.get(0);
		return null;
	}
}
