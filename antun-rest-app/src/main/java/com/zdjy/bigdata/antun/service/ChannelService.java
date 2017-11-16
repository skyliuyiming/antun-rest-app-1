package com.zdjy.bigdata.antun.service;

import java.util.List;

import com.zdjy.bigdata.antun.domain.Channel;
import com.zdjy.bigdata.antun.web.model.ChannelAdd;
import com.zdjy.bigdata.antun.web.model.ChannelUpdate;

public interface ChannelService {

	Channel findByCode(String channelCode);

	List<Channel> findAll();

	int updateStatus(Long id, Integer status);

	int saveChannel(ChannelAdd channelAdd);

	int deleteChannel(Long id);

	int updateChannel(Long id, ChannelUpdate channelUpdate);

	Channel getChannel(Long id);

	List<Channel> findByStatus(Integer status);

}
