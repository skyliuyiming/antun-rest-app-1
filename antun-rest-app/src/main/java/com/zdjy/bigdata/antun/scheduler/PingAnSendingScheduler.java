package com.zdjy.bigdata.antun.scheduler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zdjy.bigdata.antun.domain.User;
import com.zdjy.bigdata.antun.remote.PingAnSending;
import com.zdjy.bigdata.antun.remote.Response;
import com.zdjy.bigdata.antun.service.UserService;

/**
 * 平安外发调度器
 * 
 * @author david
 * @create 2017年11月14日 下午2:53:58
 */
@Component
public class PingAnSendingScheduler {
	@Autowired
	private UserService userService;
	@Autowired
	private PingAnSending pingAnSending;
	private Logger logger = LoggerFactory.getLogger(PingAnSendingScheduler.class);

	/**
	 * 每分钟扫描数据库外发一次
	 * 
	 * @throws Exception
	 */
	@Scheduled(cron = "0 * * * * ?")
	public void send() throws Exception {
		logger.debug("平安定时任务...");

		// 取出待发送的数据
		List<User> users = userService.findByStatus(0);
		logger.debug(users.size()+"条数据待发送...");
		// 发送平安
		for (User user : users) {
			Response response = pingAnSending.send(user);
			userService.updateSendResponse(user,response);
		}

	}
}
