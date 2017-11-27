package com.zdjy.bigdata.antun.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zdjy.bigdata.antun.domain.Statistic;
import com.zdjy.bigdata.antun.service.StatisticService;
import com.zdjy.bigdata.antun.web.response.BaseResponse;

@RestController
@RequestMapping("/statistics")
public class StatisticController extends BaseResponse{
	@Autowired
	private StatisticService statisticService;
	@RequestMapping(value="",method=RequestMethod.GET)
	public BaseResponse findStatistic(String dimension) {
		List<Map<String, Object>> findStatistic = statisticService.findStatistic(dimension);
		return successModel("查询成功").data(findStatistic);
	}
	@RequestMapping(value="/lastMonth",method=RequestMethod.GET)
	public BaseResponse findStatisticLastMonth(String dimension) {
		List<Statistic> statistics=statisticService.findStatisticLastMonth(dimension);
		return successModel("查询成功").data(statistics);
	}
}
