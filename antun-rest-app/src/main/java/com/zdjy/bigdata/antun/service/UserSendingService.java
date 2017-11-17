package com.zdjy.bigdata.antun.service;

import com.zdjy.bigdata.antun.domain.UserSending;

public interface UserSendingService {

	int saveUserSending(UserSending userSending);

	UserSending findByUserCode(String userCode);

}
