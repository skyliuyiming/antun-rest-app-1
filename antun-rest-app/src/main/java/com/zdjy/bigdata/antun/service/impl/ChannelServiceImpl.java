package com.zdjy.bigdata.antun.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zdjy.bigdata.antun.mapper.ChannelMapper;
import com.zdjy.bigdata.antun.service.ChannelService;
/**
 * 渠道业务类
 * @author david
 * @create 2017年11月13日 下午2:48:27
 */
@Service
public class ChannelServiceImpl implements ChannelService {
	@Autowired
	private ChannelMapper channelMapper;
}
