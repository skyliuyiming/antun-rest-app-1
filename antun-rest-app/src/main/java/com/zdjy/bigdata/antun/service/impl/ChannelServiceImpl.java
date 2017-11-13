package com.zdjy.bigdata.antun.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjy.bigdata.antun.domain.Channel;
import com.zdjy.bigdata.antun.domain.ChannelExample;
import com.zdjy.bigdata.antun.domain.ChannelExample.Criteria;
import com.zdjy.bigdata.antun.mapper.ChannelMapper;
import com.zdjy.bigdata.antun.service.ChannelService;
import com.zdjy.bigdata.antun.util.EsapiUtil;
/**
 * 渠道业务类
 * @author david
 * @create 2017年11月13日 下午2:48:27
 */
@Service
public class ChannelServiceImpl implements ChannelService {
	@Autowired
	private ChannelMapper channelMapper;

	/**
	 * 渠道码查询
	 * @param channelCode
	 * @return
	 */
	@Override
	public Channel findByCode(String channelCode) {
		ChannelExample channelExample = new ChannelExample();
		Criteria createCriteria = channelExample.createCriteria();
		createCriteria.andCodeEqualTo(EsapiUtil.sql(channelCode));
		channelExample.setLimit(1);
		List<Channel> selectByExample = channelMapper.selectByExample(channelExample);
		if(!selectByExample.isEmpty())
			return selectByExample.get(0);
		return null;
	}
}
