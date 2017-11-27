package com.zdjy.bigdata.antun.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.zdjy.bigdata.antun.service.StatisticService;

/**
 * 定时统计调度器
 * @author david
 * @create 2017年11月27日 下午7:33:10
 */
@Component
public class StatisticScheduler {
	@Autowired
	private StatisticService statisticService;
	private Logger logger = LoggerFactory.getLogger(StatisticScheduler.class);
	@Scheduled(cron="* * * * * ?")
	public void statistic() {
		logger.debug("统计任务...");
		//查询数据
		int statictic = statisticService.statictic();
		logger.debug("统计出"+statictic+"条数据");
	}
}
