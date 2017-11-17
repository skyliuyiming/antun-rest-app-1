package com.zdjy.bigdata.antun.service;

import java.util.List;
import java.util.Map;

import com.zdjy.bigdata.antun.domain.User;
import com.zdjy.bigdata.antun.remote.Response;
import com.zdjy.bigdata.antun.web.model.UserAdd;
import com.zdjy.bigdata.antun.web.response.PageMap;

public interface UserService {

	int saveUser(UserAdd userAdd);

	User findByPhone(String phone);

	List<User> findByStatus(Integer status);

	int updateSendResponse(User user, Response response);

	PageMap findByPage(Integer offset, Integer limit, String phone, String name);

	Map<String,Object> findWithSendingByCode(String code);

}
