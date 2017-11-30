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
		if(selectByExample.isEmpty())
			return null;
		Channel channel = selectByExample.get(0);
		return channel;
	}

	@Override
	public List<Channel> findAll() {
		ChannelExample channelExample = new ChannelExample();
		channelExample.setOrderByClause("id desc");
		List<Channel> selectByExample = channelMapper.selectByExample(channelExample);
		return selectByExample;
	}
	
	/**
	 * id查询
	 * @param id
	 * @return
	 */
	@Override
	public Channel getChannel(Long id) {
		Channel channel=channelMapper.selectByPrimaryKey(id);
		return channel;
	}

	/**
	 * 状态查询
	 * @param status
	 * @return
	 */
	@Override
	public List<Channel> findByStatus(Integer status) {
		ChannelExample channelExample = new ChannelExample();
		Criteria createCriteria = channelExample.createCriteria();
		createCriteria.andStatusEqualTo(status);
		channelExample.setOrderByClause("id desc");
		List<Channel> selectByExample = channelMapper.selectByExample(channelExample);
		return selectByExample;
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
		int updateByPrimaryKeySelective = channelMapper.updateByPrimaryKeySelective(channel);
		return updateByPrimaryKeySelective;
	}

	/**
	 * 保存渠道
	 * @param channelAdd
	 * @return
	 */
	@Override
	public int saveChannel(ChannelAdd channelAdd) {
		Channel transfer = TransferUtil.transfer(channelAdd,Channel.class);
		int insertSelective = channelMapper.insertSelective(transfer);
		return insertSelective;
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@Override
	public int deleteChannel(Long id) {
		int deleteByPrimaryKey = channelMapper.deleteByPrimaryKey(id);
		return deleteByPrimaryKey;
	}

	/**
	 * 修改
	 * @param id
	 * @param channelUpdate
	 * @return
	 */
	@Override
	public int updateChannel(Long id, ChannelUpdate channelUpdate) {
		Channel transfer = TransferUtil.transfer(channelUpdate,Channel.class);
		transfer.setId(id);
		int updateByPrimaryKeySelective = channelMapper.updateByPrimaryKeySelective(transfer);
		return updateByPrimaryKeySelective;
	}

	
}
