package com.zdjy.bigdata.antun.service;

import java.util.List;
import java.util.Map;

import com.zdjy.bigdata.antun.domain.Statistic;

public interface StatisticService {

	List<Map<String, Object>> findStatistic(String dimension);
	int statictic();
	List<Statistic> findStatisticLastMonth(String dimension);
}
