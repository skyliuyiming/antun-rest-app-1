package com.zdjy.bigdata.antun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zdjy.bigdata.antun.domain.User;
import com.zdjy.bigdata.antun.domain.UserExample;
import com.zdjy.bigdata.antun.domain.UserExample.Criteria;
import com.zdjy.bigdata.antun.domain.UserSending;
import com.zdjy.bigdata.antun.mapper.UserMapper;
import com.zdjy.bigdata.antun.remote.PingAnSending.Response;
import com.zdjy.bigdata.antun.service.UserSendingService;
import com.zdjy.bigdata.antun.service.UserService;
import com.zdjy.bigdata.antun.util.CodeGenerateUtils;
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
	/**
	 * 外发成功
	 */
	private static final Integer send_success=1;
	
	/**
	 * 外发失败
	 */
	private static final Integer send_failure=2;
	/**
	 * 外发异常，网络原因
	 */
	private static final Integer send_error=3;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserSendingService userSendingService;

	/**
	 * 保存
	 * @param userAdd
	 * @return
	 */
	@Transactional
	@Override
	public int saveUser(UserAdd userAdd) {
		User transfer = transfer(userAdd);
		transfer.setCode(CodeGenerateUtils.getRandomCode());
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

	/**
	 * 根据状态查询
	 * @param i
	 * @return
	 */
	@Override
	public List<User> findByStatus(Integer status) {
		UserExample userExample = new UserExample();
		Criteria createCriteria = userExample.createCriteria();
		createCriteria.andStatusEqualTo(status);
		return userMapper.selectByExample(userExample);
	}

	/**
	 * 修改外发返回响应信息
	 * @param user
	 * @param response
	 * @return
	 */
	@Transactional
	@Override
	public int updateSendResponse(User user, Response response) {
		User user2 = new User();
		user2.setId(user.getId());
		if(response==null) {
			user2.setStatus(send_error);
		}else if(response.getCode()==200) {
			user2.setStatus(send_success);
		}else {
			user2.setStatus(send_failure);
		}
		UserSending userSending = new UserSending();
		userSending.setUserCode(user.getCode());
		userSending.setResponseCode(response.getCode());
		userSending.setResponseMessage(response.getResult());
		int i=userSendingService.saveUserSending(userSending);
		if(i>0)
			return userMapper.updateByPrimaryKeySelective(user2);
		return 0;
	}
}
