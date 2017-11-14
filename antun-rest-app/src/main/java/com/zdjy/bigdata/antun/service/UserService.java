package com.zdjy.bigdata.antun.service;

import java.util.List;

import com.zdjy.bigdata.antun.domain.User;
import com.zdjy.bigdata.antun.remote.PingAnSending.Response;
import com.zdjy.bigdata.antun.web.model.UserAdd;

public interface UserService {

	int saveUser(UserAdd userAdd);

	User findByPhone(String phone);

	List<User> findByStatus(Integer status);

	int updateSendResponse(User user, Response response);

}
