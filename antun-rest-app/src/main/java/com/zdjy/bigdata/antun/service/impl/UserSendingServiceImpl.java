package com.zdjy.bigdata.antun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjy.bigdata.antun.domain.UserSending;
import com.zdjy.bigdata.antun.mapper.UserSendingMapper;
import com.zdjy.bigdata.antun.service.UserSendingService;

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
}
