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
import com.zdjy.bigdata.antun.util.TransferUtil;
import com.zdjy.bigdata.antun.web.model.ChannelAdd;
import com.zdjy.bigdata.antun.web.model.ChannelUpdate;
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

	@Override
	public List<Channel> findAll() {
		ChannelExample channelExample = new ChannelExample();
		channelExample.setOrderByClause("status desc,id desc");
		return channelMapper.selectByExample(channelExample);
	}

	/**
	 * 修改状态
	 * @param id
	 * @param status
	 * @return
	 */
	@Override
	public int updateStatus(Long id, Integer status) {
		Channel channel = new Channel();
		channel.setId(id);
		channel.setStatus(status);
		return channelMapper.updateByPrimaryKeySelective(channel);
	}

	/**
	 * 保存渠道
	 * @param channelAdd
	 * @return
	 */
	@Override
	public int saveChannel(ChannelAdd channelAdd) {
		Channel transfer = transfer(channelAdd);
		return channelMapper.insertSelective(transfer);
	}
	
	
	/**
	 * 新增类与领域类的转换
	 * 
	 * @param channelAdd
	 * @return
	 */
	public Channel transfer(ChannelAdd channelAdd) {
		Channel channel = new Channel();
		TransferUtil.transfer(channel, channelAdd);
		return channel;
	}
	
	/**
	 * 新增类与领域类的转换
	 * 
	 * @param channelUpdate
	 * @return
	 */
	public Channel transfer(ChannelUpdate channelUpdate) {
		Channel channel = new Channel();
		TransferUtil.transfer(channel, channelUpdate);
		return channel;
	}

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@Override
	public int deleteChannel(Long id) {
		return channelMapper.deleteByPrimaryKey(id);
	}

	/**
	 * 修改
	 * @param id
	 * @param channelUpdate
	 * @return
	 */
	@Override
	public int updateChannel(Long id, ChannelUpdate channelUpdate) {
		Channel transfer = transfer(channelUpdate);
		transfer.setId(id);
		return channelMapper.updateByPrimaryKeySelective(transfer);
	}

	/**
	 * id查询
	 * @param id
	 * @return
	 */
	@Override
	public Channel getChannel(Long id) {
		return channelMapper.selectByPrimaryKey(id);
	}
}
