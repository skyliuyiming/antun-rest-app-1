package com.zdjy.bigdata.antun.service;

import com.zdjy.bigdata.antun.domain.User;
import com.zdjy.bigdata.antun.web.model.UserAdd;

public interface UserService {

	int saveUser(UserAdd userAdd);

	User findByPhone(String phone);

}
